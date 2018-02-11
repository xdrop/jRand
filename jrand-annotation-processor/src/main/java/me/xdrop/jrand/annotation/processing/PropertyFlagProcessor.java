package me.xdrop.jrand.annotation.processing;

import com.github.javaparser.ast.CompilationUnit;
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
import java.util.Set;

@SupportedAnnotationTypes("me.xdrop.jrand.annotation.PropertyFlag")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class PropertyFlagProcessor extends BaseProcessor {
    private PropertyMethodGenerator propertyMethodGenerator;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        propertyMethodGenerator = new PropertyMethodGenerator(processingEnv, getRepository());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(PropertyFlag.class)) {
            if (element.getKind() != ElementKind.FIELD) {
                getMessager().printMessage(Diagnostic.Kind.ERROR, "Annotation [PropertyFlag] should only be used on fields.");
                return true;
            }
            VariableElement symbol = (VariableElement) element;
            PropertyFlag annotation = symbol.getAnnotation(PropertyFlag.class);
            PackageElement pkg = processingEnv.getElementUtils().getPackageOf(symbol);
            String className = symbol.getEnclosingElement().getSimpleName().toString();
            CompilationUnit compilationUnit = getRepository().getCU(pkg.toString(), className);
            String fullPackage = pkg.getQualifiedName().toString();
            CompilationUnit newCU = propertyMethodGenerator.buildMethod(compilationUnit, symbol,
                    className, fullPackage,annotation.value());
            // Get the last part of the package eg. me.xdrop.generators.text would be text
            String lastPackage = getLastPackageName(pkg);
            Path path = Paths.get(getOutputPathGenerators().toString(), lastPackage);

            try {
                getRepository().writeTo(path, className, newCU);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
