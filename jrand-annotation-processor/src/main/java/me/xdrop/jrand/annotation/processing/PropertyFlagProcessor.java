package me.xdrop.jrand.annotation.processing;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.squareup.javapoet.MethodSpec;
import me.xdrop.jrand.annotation.PropertyFlag;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@SupportedAnnotationTypes("me.xdrop.jrand.annotation.PropertyFlag")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class PropertyFlagProcessor extends BaseProcessor {
    private PropertyMethodGenerator propertyMethodGenerator;
    private Map<ClassIdentifier, List<MethodSpec>> methodPerClass = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        propertyMethodGenerator = new PropertyMethodGenerator(processingEnv, getRepository());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (roundEnv.processingOver()){
            return true;
        }

        for (Element element : roundEnv.getElementsAnnotatedWith(PropertyFlag.class)) {
            if (element.getKind() != ElementKind.FIELD) {
                getMessager().printMessage(Diagnostic.Kind.ERROR, "Annotation [PropertyFlag] should only be used on fields.");
                return true;
            }
            VariableElement symbol = (VariableElement) element;
            PropertyFlag annotation = symbol.getAnnotation(PropertyFlag.class);

            System.out.println(annotation.toString());
            PackageElement pkg = processingEnv.getElementUtils().getPackageOf(symbol);
            String className = symbol.getEnclosingElement().getSimpleName().toString();
            CompilationUnit cu = getRepository().getCU(pkg.toString(), className);

            ClassIdentifier id = new ClassIdentifier(className, pkg);
            List<MethodSpec> methodsInClass = methodPerClass.computeIfAbsent(id, k -> new ArrayList<>());

            methodsInClass.addAll(propertyMethodGenerator.buildMethods(cu, symbol,
                    annotation.value()));
        }


        methodPerClass.forEach((k,v) -> {
            PackageElement pkg = k.getPackageName();
            String fullPackage = pkg.getQualifiedName().toString();
            // Get the last part of the package eg. me.xdrop.generators.text would be text
            String lastPackage = getLastPackageName(pkg);
            Path path = Paths.get(getOutputPathGenerators().toString(), lastPackage);
            String className = k.getClassName();
            CompilationUnit cu = getRepository().getCU(pkg.toString(), className);

            getRepository().addMethods(cu, className, fullPackage, v);
            try {
                getRepository().writeTo(path, className, cu);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });




        return false;
    }
}
