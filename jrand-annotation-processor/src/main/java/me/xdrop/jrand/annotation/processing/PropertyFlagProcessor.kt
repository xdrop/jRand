package me.xdrop.jrand.annotation.processing

import me.xdrop.jrand.annotation.PropertyFlag
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement
import javax.tools.Diagnostic

@SupportedAnnotationTypes("me.xdrop.jrand.annotation.PropertyFlag")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
class PropertyFlagProcessor : BaseProcessor() {

    @Synchronized
    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
    }

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        if (roundEnv.processingOver()) {
            return true
        }

        roundEnv.getElementsAnnotatedWith(PropertyFlag::class.java).forEach { element ->
            if (element.kind != ElementKind.FIELD || element !is VariableElement) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Annotation [PropertyFlag] should only be used on fields.")
                return true
            }

            val annotation = element.getAnnotation(PropertyFlag::class.java)
            val pkg = processingEnv.elementUtils.getPackageOf(element)
            val enclosingClass = element.enclosingElement as TypeElement

            val id = ClassIdentifier(enclosingClass, pkg)

            // Add these methods to the shared class image
            ProcessorRepository.addMethods(id, createPropertySetterMethods(element, annotation.value).asList())
        }

        // Claim all flag annotations
        return true
    }
}
