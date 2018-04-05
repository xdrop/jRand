package me.xdrop.jrand.annotation.processing

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import me.xdrop.jrand.annotation.processing.ProcessorRepository.CLASS_SUFFIX

import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import java.io.IOException
import java.io.Writer

class FacadeClassBuilder {

    fun buildMethod(accessor: String, pkg: String, typeElement: TypeElement): MethodSpec {
        val generatedSuperclass = ClassName.get(pkg, typeElement.simpleName.toString().plus(CLASS_SUFFIX))

        return MethodSpec.methodBuilder(accessor)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                .returns(generatedSuperclass)
                .addStatement("return new \$T()", generatedSuperclass)
                .build()
    }

    fun buildFacadeClass(annotationMap: Map<String, Pair<TypeElement, String>>): TypeSpec {
        val jrandFacade = TypeSpec
                .classBuilder("JRand")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)

        for ((key, value) in annotationMap) {
            jrandFacade.addMethod(buildMethod(key, value.second, value.first))
        }

        return jrandFacade.build()

    }

    @Throws(IOException::class)
    fun writeFacade(writer: Writer, typeSpec: TypeSpec, packageName: String) {
        val javaFile = JavaFile.builder(packageName, typeSpec).indent("    ").build()
        javaFile.writeTo(writer)
    }
}
