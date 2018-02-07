package me.xdrop.jrand.annotation.processing;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;

import javax.annotation.Generated;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Types;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ForkClassGenerator {

    private static String GENERATOR_ID = "me.xdrop.jrand.annotation.processing.ForkClassGenerator";

    private final Path root;
    private ProcessingEnvironment processingEnv;
    private SourceRoot sourceRoot;
    private ProcessorRepository repository;

    public ForkClassGenerator(ProcessingEnvironment processingEnv, ProcessorRepository repository) {
        this.processingEnv = processingEnv;
        root = Paths.get("jrand-core", "src", "main", "java");
        sourceRoot = new SourceRoot(root);
        this.repository = repository;
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
            if (!variable.getModifiers().contains(Modifier.FINAL) || variable.getConstantValue() == null) {
                if (typeUtils.isSubtype(variable.asType(), typeUtils.erasure(listElement))) {
                    fields.append("new java.util.ArrayList<>(");
                    fields.append(varName);
                    fields.append(")");
                } else if (typeUtils.isAssignable(variable.asType(), typeUtils.erasure(mapElement))) {
                    fields.append("new java.util.HashMap<>(");
                    fields.append(varName);
                    fields.append(")");
                } else if (variable.asType().getKind().isPrimitive()) {
                    fields.append(varName);
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
            if (!varEl.getModifiers().contains(Modifier.FINAL) || varEl.getConstantValue() == null) {
                String identifier = varEl.getSimpleName().toString();
                constructor.addParameter(ClassName.get(varEl.asType()), identifier);
                constructor.addStatement("this.$N = $N", identifier, identifier);
            }
        });

        return constructor.build();
    }

    public CompilationUnit buildForkedClass(TypeElement generator, String className, CompilationUnit source) {
        List<VariableElement> variableElements = ElementFilter.fieldsIn(generator.getEnclosedElements());
        String pkg = processingEnv.getElementUtils().getPackageOf(generator).getQualifiedName().toString();

        MethodSpec forkMethod = buildMethod(generator, variableElements);
        MethodSpec copyConstructor = buildConstructor(generator, variableElements);

        repository.addMethods(source, className, pkg, forkMethod, copyConstructor);

        return source;
    }

}
