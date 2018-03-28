package me.xdrop.jrand.annotation.processing

import me.xdrop.jrand.annotation.Facade
import me.xdrop.jrand.annotation.PropertyFlag
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
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
    private val forkClassGenerator: ForkClassGenerator = ForkClassGenerator(processingEnv)
    private val facadeClasses: MutableMap<String, TypeElement> = HashMap()
    private var outputPathFacade: Path = Paths.get("jrand-core", "src", "generated", "java", "me", "xdrop", "jrand")

    @Synchronized
    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
    }

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        if (roundEnv.processingOver()) {
            return true
        }

        roundEnv.getElementsAnnotatedWith(PropertyFlag::class.java).forEach { element ->
            if (element.kind != ElementKind.CLASS || element !is TypeElement) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Annotation [Facade] should only be used on classes.")
                return true
            }

            val pkg = processingEnv.elementUtils.getPackageOf(element)
            val annotation = element.getAnnotation(Facade::class.java)
            val id = ClassIdentifier(element, pkg)
            val accessor = annotation.accessor

            ProcessorRepository.addMethods(id, forkClassGenerator.getForkAndCloneMethods(element))
            ProcessorRepository.writeToFiler(id, filer)

            facadeClasses[accessor] = element
        }

        val jrandFacade = classBuilder.buildFacadeClass(facadeClasses)

        try {
            FileWriter(File(outputPathFacade.toString(), "JRand.java")).use { fw ->
                classBuilder.writeFacade(fw, jrandFacade, rootPackage)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return true
    }

}
