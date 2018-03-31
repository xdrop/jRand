package me.xdrop.jrand.annotation.processing

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import javax.annotation.processing.Filer
import javax.lang.model.element.Modifier

/**
 * This class serves as a common ground for all annotation processors. Two
 * or more annotation processors may have an effect on the same generated
 * class, and this allows both processors to operate on an interim
 * image of the class file before committing it to disk.
 *
 * Note that we make the assumptions that processors are not run in
 * parallel. Doing so may void the integrity of the operation.
 */
object ProcessorRepository {

    private val classToMethods : MutableMap<ClassIdentifier, TypeSpec.Builder> = HashMap();
    const val CLASS_SUFFIX = "Gen"

    /**
     * Generates a default [TypeSpec.Builder] for [clazz].
     */
    private fun defaultClassSpec(clazz: ClassIdentifier): TypeSpec.Builder {
        return  TypeSpec.classBuilder(clazz.className + CLASS_SUFFIX)
                .superclass(ClassName.get(clazz.typeElement))
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
    }

    /**
     * Adds all [methodSpecs] to the class at [target].
     */
    fun addMethods(target: ClassIdentifier, methodSpecs: List<MethodSpec>) {
        classToMethods.computeIfAbsent(target, { defaultClassSpec(target) })
                .addMethods(methodSpecs)
    }

    /**
     * Adds one [methodSpec] to the class at [target].
     */
    fun addMethod(target: ClassIdentifier, methodSpec: MethodSpec) {
        classToMethods.computeIfAbsent(target, { defaultClassSpec(target) })
                .addMethod(methodSpec)
    }

    /**
     * Builds the class identified by [clazz] to create a [TypeSpec].
     */
    fun buildClass(clazz : ClassIdentifier): TypeSpec? {
        return classToMethods[clazz]?.build()
    }

    /**
     * Writes the class identified by [clazz] to the given [filer]
     */
    fun writeToFiler(clazz: ClassIdentifier, filer: Filer) {
        JavaFile.builder(clazz.packageName.toString(), buildClass(clazz))
                .build().writeTo(filer)
    }
}