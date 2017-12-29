package me.xdrop.jrand.processing;

import com.squareup.javapoet.TypeSpec;
import me.xdrop.jrand.annotation.Facade;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Map;
import java.util.Set;

public class FacadeProcessor extends AbstractProcessor {
    private Messager messager;
    private Map<String, String> facadeClasses;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Facade.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Annotation [Facade] should only be used on classes.");
                return true;
            }

            TypeElement typeElement = (TypeElement) element;
            Facade facade = element.getAnnotation(Facade.class);
            String accessor = facade.accessor();

            facadeClasses.put(accessor, typeElement.getQualifiedName().toString());
        }
        System.out.println(facadeClasses);
        messager.printMessage(Diagnostic.Kind.ERROR, "HI");

        TypeSpec.Builder jrandFacade = TypeSpec
                .classBuilder("JRand")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

        return false;
    }
}
