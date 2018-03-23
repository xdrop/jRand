package me.xdrop.jrand.annotation.processing

import me.xdrop.jrand.annotation.Facade
import me.xdrop.jrand.annotation.PropertyFlag

import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic
import java.io.*
import java.nio.file.Path
import java.nio.file.Paths
import java.util.HashMap


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

            ProcessorRepository.addMethod(id, forkClassGenerator.)

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
