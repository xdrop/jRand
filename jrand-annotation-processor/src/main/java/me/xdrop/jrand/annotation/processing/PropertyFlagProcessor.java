package me.xdrop.jrand.annotation.processing;

import com.github.javaparser.ast.CompilationUnit;
import me.xdrop.jrand.annotation.PropertyFlag;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("me.xdrop.jrand.annotation.PropertyFlag")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class PropertyFlagProcessor extends BaseProcessor {
    private PropertyMethodGenerator propertyMethodGenerator = new PropertyMethodGenerator(processingEnv);

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(PropertyFlag.class)) {
            if (element.getKind() != ElementKind.FIELD) {
                getMessager().printMessage(Diagnostic.Kind.ERROR, "Annotation [PropertyFlag] should only be used on fields.");
                return true;
            }
            VariableElement symbol = (VariableElement) element;
            PackageElement pkg = processingEnv.getElementUtils().getPackageOf(symbol);
            String className = symbol.getEnclosingElement().getSimpleName().toString();
            CompilationUnit compilationUnit = getRepository().getCU(pkg.toString(), className);
            propertyMethodGenerator.buildMethod(compilationUnit, symbol, "");
        }
        return false;
    }
}
