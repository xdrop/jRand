package me.xdrop.jrand.annotation.processing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.utils.SourceRoot;
import com.squareup.javapoet.*;

import javax.annotation.Generated;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Types;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class ForkClassGenerator {

    private static String GENERATOR_ID = "me.xdrop.jrand.annotation.processing.ForkClassGenerator";


    private final Path root;
    private ProcessingEnvironment processingEnv;
    private SourceRoot sourceRoot;

    public ForkClassGenerator(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        root = Paths.get("jrand-core", "src", "main", "java");
        sourceRoot = new SourceRoot(root);
    }

    public MethodSpec buildMethod(TypeElement generator, List<VariableElement> variableElements) {

        AnnotationSpec generated = AnnotationSpec.builder(Generated.class)
                .addMember("value", "$S", GENERATOR_ID)
                .build();

        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("fork")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(generated)
                .returns(ClassName.get(generator));

        StringBuilder fields = new StringBuilder();
        fields.append("\n");
        TypeMirror listElement = processingEnv.getElementUtils().getTypeElement("java.util.List").asType();
        TypeMirror mapElement = processingEnv.getElementUtils().getTypeElement("java.util.Map").asType();
        TypeMirror generatorSuper = generator.getSuperclass();
        Types typeUtils = processingEnv.getTypeUtils();

        for (VariableElement variable : variableElements) {

            String varName = variable.getSimpleName().toString();
            if (!variable.getModifiers().contains(Modifier.FINAL)) {
                if (typeUtils.isSubtype(variable.asType(), typeUtils.erasure(listElement))) {
                    fields.append("new java.util.ArrayList<>(");
                    fields.append(varName);
                    fields.append(")");
                } else if (typeUtils.isAssignable(variable.asType(), typeUtils.erasure(mapElement))){
                    fields.append("new java.util.HashMap<>(");
                    fields.append(varName);
                    fields.append(")");
                } else if (typeUtils.isAssignable(variable.asType(), typeUtils.erasure(generatorSuper))) {
                    fields.append(varName);
                    fields.append(".fork()");
                } else {
                    fields.append(varName);
                }

                fields.append(",\n");
            }

        }

        String fieldString = "";
        if (fields.length() > 1) {
            fieldString = fields.substring(0, fields.length() - 2);
        }
        methodBuilder.addStatement("return new $T($L)", generator, fieldString);

        return methodBuilder.build();
    }

    private MethodSpec buildConstructor(TypeElement generator, List<VariableElement> variableElements) {
        AnnotationSpec generated = AnnotationSpec.builder(Generated.class)
                .addMember("value", "$S", GENERATOR_ID)
                .build();

        MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .addAnnotation(generated);

        variableElements.forEach(varEl -> {
            if (!varEl.getModifiers().contains(Modifier.FINAL)){
                String identifier = varEl.getSimpleName().toString();
                constructor.addParameter(ClassName.get(varEl.asType()), identifier);
                constructor.addStatement("this.$N = $N", identifier, identifier);
            }
        });

        return constructor.build();
    }

    private CompilationUnit buildForkedClass(TypeElement generator) {
        List<VariableElement> variableElements = ElementFilter.fieldsIn(generator.getEnclosedElements());
        String pkg = processingEnv.getElementUtils().getPackageOf(generator).getQualifiedName().toString();

        String filename = generator.getSimpleName().toString() + ".java";
        final CompilationUnit forkedClass = LexicalPreservingPrinter.setup(sourceRoot.parse(pkg, filename));

        Optional<ClassOrInterfaceDeclaration> optClassDecl = forkedClass.getClassByName(generator.getSimpleName().toString());
        Optional<PackageDeclaration> optPackageDecl = forkedClass.findFirst(PackageDeclaration.class);

        if (optClassDecl.isPresent() && optPackageDecl.isPresent()) {
            ClassOrInterfaceDeclaration classDecl = optClassDecl.get();
            PackageDeclaration packageDecl = optPackageDecl.get();

            MethodSpec forkMethod = buildMethod(generator, variableElements);
            MethodSpec copyConstructor = buildConstructor(generator, variableElements);

            TypeSpec clazz = TypeSpec.classBuilder(ClassName.get(generator))
                    .addOriginatingElement(generator)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(forkMethod)
                    .addMethod(copyConstructor)
                    .build();

            JavaFile withFork = JavaFile.builder(pkg, clazz).indent("    ").build();

            CompilationUnit generated = LexicalPreservingPrinter.setup(JavaParser.parse(withFork.toString()));
            MethodDeclaration parsedFork = generated.findFirst(MethodDeclaration.class).get();
            ConstructorDeclaration parsedCopyConstructor = generated.findFirst(ConstructorDeclaration.class).get();

            Name javaxImport = new Name(new Name("javax.annotation"), "Generated");
            ImportDeclaration generatedImport = new ImportDeclaration(javaxImport, false, false);


            ImportDeclaration parse = LexicalPreservingPrinter.setup(JavaParser.parseImport("import javax.annotation.Generated;\n"));
            parse.getData(LexicalPreservingPrinter.NODE_TEXT_DATA).addToken(0, 3, "\n");
            forkedClass.addImport(parse);
            classDecl.addMember(parsedFork);
            classDecl.addMember(parsedCopyConstructor);
            return forkedClass;
        }

        return null;
    }

    public void writeForkedClass(TypeElement generator, Writer writer) throws IOException {
        CompilationUnit cu = buildForkedClass(generator);
        if (cu != null) {
            LexicalPreservingPrinter.print(cu, writer);
        }
    }

}
