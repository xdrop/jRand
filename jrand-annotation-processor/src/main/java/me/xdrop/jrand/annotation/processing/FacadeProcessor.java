package me.xdrop.jrand.annotation.processing;

import com.github.javaparser.ast.CompilationUnit;
import com.squareup.javapoet.TypeSpec;
import me.xdrop.jrand.annotation.Facade;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@SupportedAnnotationTypes("me.xdrop.jrand.annotation.Facade")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class FacadeProcessor extends BaseProcessor {
    private FacadeClassBuilder classBuilder;
    private ForkClassGenerator forkClassGenerator;
    private Map<String, TypeElement> facadeClasses;
    private Path outputPathFacade;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.facadeClasses = new HashMap<>();
        this.classBuilder = new FacadeClassBuilder();
        this.forkClassGenerator = new ForkClassGenerator(processingEnv, getRepository());
        this.outputPathFacade = Paths.get("jrand-core", "src", "generated", "java", "me", "xdrop", "jrand");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Facade.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                getMessager().printMessage(Diagnostic.Kind.ERROR, "Annotation [Facade] should only be used on classes.");
                return true;
            }

            TypeElement typeElement = (TypeElement) element;
            PackageElement pkg = processingEnv.getElementUtils().getPackageOf(typeElement);

            Facade facade = element.getAnnotation(Facade.class);
            String accessor = facade.accessor();
            facadeClasses.put(accessor, typeElement);

            try {
                // Get the last part of the package eg. me.xdrop.generators.text would be text
                String lastPackage = getLastPackageName(pkg);
                // Get the class name eg. LoremGenerator
                String className = typeElement.getSimpleName().toString();
                // Get the output path which eg. generated/me/xdrop/generators/text
                Path path = Paths.get(getOutputPathGenerators().toString(), lastPackage);
                boolean mkdirs = path.toFile().mkdirs();
                // Get the source Compilation unit
                CompilationUnit sourceCU = getRepository().getCU(pkg.toString(), className);
                // Create the new compilation unit
                CompilationUnit newCU = forkClassGenerator.buildForkedClass(typeElement, className, sourceCU);
                getRepository().addCU(className, newCU);
                // Write the new compilation unit
                getRepository().writeTo(path, className, newCU);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        TypeSpec jrandFacade = classBuilder.buildFacadeClass(facadeClasses);

        try {
            try (FileWriter fw = new FileWriter(new File(outputPathFacade.toString(), "JRand.java"))) {
                classBuilder.writeFacade(fw, jrandFacade, packageName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }



    private class FacadeGeneratorInfo {
        private String packageName;
        private String simpleName;
    }
}
