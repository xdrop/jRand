package me.xdrop.jrand.annotation.processing;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class PropertyMethodGenerator {

    private static String GENERATOR_ID = "me.xdrop.jrand.annotation.processing.PropertyMethodGenerator";

    public void buildMethod(VariableElement field, String javadoc) {
        AnnotationSpec generated = AnnotationSpec.builder(Generated.class)
                .addMember("value", "$S", GENERATOR_ID)
                .build();

        String name = field.getSimpleName().toString();
        MethodSpec.Builder withParam = MethodSpec.methodBuilder(name)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(generated)
                .addParameter(TypeName.BOOLEAN.unbox(), "enabled")
                .addStatement("this.$L = enabled", name)
                .addStatement("return this")
                .returns(ClassName.get(field.getEnclosingElement().asType()));

        MethodSpec.Builder withoutParam = MethodSpec.methodBuilder(name)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(generated)
                .addStatement("return $L(true)", name)
                .returns(ClassName.get(field.getEnclosingElement().asType()));

    }
}
