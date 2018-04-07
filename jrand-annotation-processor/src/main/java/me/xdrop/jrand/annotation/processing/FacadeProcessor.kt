package me.xdrop.jrand.annotation.processing

import com.squareup.javapoet.JavaFile
import me.xdrop.jrand.annotation.Facade
import me.xdrop.jrand.annotation.PropertyFlag
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic


/**
 * The facade processor is responsible for creating the JRand facade class,
 * as well as rendering the finalized extended generators with the
 * fork() method as well as any existing modifications made from
 * the property processor.
 */
@SupportedAnnotationTypes("me.xdrop.jrand.annotation.Facade")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
class FacadeProcessor : BaseProcessor() {
    private val classBuilder: FacadeClassBuilder = FacadeClassBuilder()
    private var forkClassGenerator: ForkClassGenerator? = null
    private val facadeClasses: MutableMap<String, Pair<TypeElement, String>> = HashMap()

    @Synchronized
    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        this.forkClassGenerator = ForkClassGenerator(processingEnv)
    }

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        if (roundEnv.processingOver()) {
            return true
        }

        roundEnv.getElementsAnnotatedWith(Facade::class.java).forEach { element ->
            if (element.kind != ElementKind.CLASS || element !is TypeElement) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Annotation [Facade] should only be used on classes.")
                return true
            }

            val pkg = processingEnv.elementUtils.getPackageOf(element)
            val annotation = element.getAnnotation(Facade::class.java)
            val id = ClassIdentifier(element, pkg)
            val accessor = annotation.accessor

            ProcessorRepository.addMethods(id, forkClassGenerator?.getForkAndCloneMethods(element) ?: emptyList())
            ProcessorRepository.writeToFiler(id, filer)

            facadeClasses[accessor] = Pair(element, pkg.toString())
        }

        val jrandFacade = classBuilder.buildFacadeClass(facadeClasses)

        try {
            JavaFile.builder("me.xdrop.jrand", jrandFacade)
                    .build().writeTo(filer)
        } catch (ignored: FilerException) {
            // This fails because on later rounds the facade is already created, but it is fine
        }

        return false
    }

}
