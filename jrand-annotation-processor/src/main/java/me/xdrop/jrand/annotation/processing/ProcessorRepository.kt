package me.xdrop.jrand.annotation.processing

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec

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

    /**
     * Adds all [methodSpecs] to the class at [target]
     */
    fun addMethods(target: ClassIdentifier, methodSpecs: List<MethodSpec>) {
        classToMethods.computeIfAbsent(target, { TypeSpec.classBuilder(ClassName.get(target.typeElement))})
                .addMethods(methodSpecs)
    }

    /**
     * Adds one [methodSpec] to the class at [target]
     */
    fun addMethod(target: ClassIdentifier, methodSpec: MethodSpec) {
        classToMethods.computeIfAbsent(target, { TypeSpec.classBuilder(ClassName.get(target.typeElement))})
                .addMethod(methodSpec)
    }
}