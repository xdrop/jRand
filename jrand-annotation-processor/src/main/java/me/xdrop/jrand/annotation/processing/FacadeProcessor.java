package me.xdrop.jrand.annotation.processing;

import com.squareup.javapoet.JavaFile;
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
public class FacadeProcessor extends AbstractProcessor {
    private Messager messager;
    private Filer filer;
    private Map<String, TypeElement> facadeClasses;
    private static String packageName = "me.xdrop.jrand";
    public static String GENERATED_PACKAGE = "me.xdrop.jrand.generated.generators";
    private static String generatorPath = "generators";

    private Path outputPathGenerators;
    private FacadeClassBuilder classBuilder;
    private ForkClassGenerator forkClassGenerator;
    private Path outputPathFacade;

    private class FacadeGeneratorInfo {
        private String packageName;
        private String simpleName;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.filer = processingEnv.getFiler();
        this.facadeClasses = new HashMap<>();
        this.classBuilder = new FacadeClassBuilder();
        this.forkClassGenerator = new ForkClassGenerator(processingEnv);
        this.outputPathGenerators = Paths.get("jrand-core", "src", "generated",
                "java","me", "xdrop", "jrand", "generators");
        this.outputPathFacade = Paths.get("jrand-core", "src", "generated", "java", "me", "xdrop", "jrand");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Facade.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Annotation [Facade] should only be used on classes.");
                return true;
            }

            TypeElement typeElement = (TypeElement) element;
            PackageElement pkg = processingEnv.getElementUtils().getPackageOf(typeElement);

            Facade facade = element.getAnnotation(Facade.class);
            String accessor = facade.accessor();
            try {
                String[] subpackageParts = pkg.getQualifiedName().toString()
                        .split("\\.");
                String lastPackage = subpackageParts[subpackageParts.length - 1];

                String newGeneratedFileName = GENERATED_PACKAGE + "." + lastPackage + "." + typeElement.getSimpleName();
                String className = typeElement.getSimpleName().toString();
                Path path = Paths.get(outputPathGenerators.toString(), lastPackage);
                boolean mkdirs = path.toFile().mkdirs();
                try (FileWriter fw = new FileWriter(new File(path.toString(), className + ".java"))) {
                    forkClassGenerator.writeForkedClass(typeElement, fw);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            facadeClasses.put(accessor, typeElement);
            TypeSpec jrandFacade = classBuilder.buildFacadeClass(facadeClasses);


            try {
                try (FileWriter fw = new FileWriter(new File(outputPathFacade.toString(), "JRand.java"))) {
                    classBuilder.writeFacade(fw, jrandFacade, packageName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;

    }
}
