package me.xdrop.jrand.annotation.processing

import com.squareup.javapoet.AnnotationSpec
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeName
import javax.annotation.Generated
import javax.lang.model.element.Modifier
import javax.lang.model.element.VariableElement

var GENERATOR_ID = "me.xdrop.jrand.annotation.processing.PropertyMethodGenerator"

/**
 * Generate two setter methods for the given [field], without a 'get' prefix. One
 * of the methods is the field name with no param, that when called simply sets
 * the field to true. The other one has an enabled param which sets the field
 * to that value.
 *
 * Note that the generated field is always of type boolean.
 *
 * @param field The decorated field for which the methods should be generated
 * @param javadoc A string containing associated javadoc for that method
 * @return The list containing two method specs, where [0] is the one with the parameter
 * and [1] is the one without a parameter
 */
fun createPropertySetterMethods(field: VariableElement, javadoc: String): Array<MethodSpec> {
    val generated = AnnotationSpec.builder(Generated::class.java)
            .addMember("value", "\$S", GENERATOR_ID)
            .build()

    val name = field.simpleName.toString()
    val withParam = createPropertySetterWithParam(field, generated, name, javadoc)
    val withoutParam = createPropertySetterNoParam(field, generated, name, javadoc)

    return arrayOf(withParam, withoutParam)
}

/**
 * Generates a [MethodSpec] containing a method named as [field], with no parameters
 * that when called simply sets the [field] property to `true`
 */
fun createPropertySetterNoParam(field: VariableElement,
                                generated: AnnotationSpec, name: String, javadoc: String): MethodSpec {
    return MethodSpec.methodBuilder(name)
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addAnnotation(generated)
            .addStatement("return \$L(true)", name)
            .returns(ClassName.get(field.enclosingElement.asType()))
            .addJavadoc(javadoc + "\n")
            .addJavadoc("@return The same generator\n")
            .build()
}

/**
 * Generates a [MethodSpec] containing a method named as [field], with one parameter
 * named `enabled` that when called simply sets the [field] property to the value
 * of `enabled`
 */
fun createPropertySetterWithParam(field: VariableElement,
                                  generated: AnnotationSpec, name: String, javadoc: String): MethodSpec {
    return MethodSpec.methodBuilder(name)
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addAnnotation(generated)
            .addParameter(TypeName.BOOLEAN.unbox(), "enabled")
            .addStatement("this.\$L = enabled", name)
            .addStatement("return this")
            .returns(ClassName.get(field.enclosingElement.asType()))
            .addJavadoc(javadoc + "\n")
            .addJavadoc("@return The same generator\n")
            .build()
}

