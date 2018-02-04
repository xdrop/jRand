package me.xdrop.jrand.annotation.processing;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

public class PropertyMethodGenerator {

    private static String GENERATOR_ID = "me.xdrop.jrand.annotation.processing.PropertyMethodGenerator";

    public void buildMethod(TypeElement field, String javadoc) {
        AnnotationSpec generated = AnnotationSpec.builder(Generated.class)
                .addMember("value", "$S", GENERATOR_ID)
                .build();

        String name = field.getSimpleName().toString();
        MethodSpec.Builder withParam = MethodSpec.methodBuilder(name)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(generated)
                .addParameter(ClassName.get(boolean.class), "enabled")
                .addStatement("this.$L = enabled", name)
                .addStatement("return this")
                .returns(ClassName.get(field.getSuperclass()));

        MethodSpec.Builder withoutParam = MethodSpec.methodBuilder(name)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(generated)
                .addStatement("return $L(true)", name)
                .returns(ClassName.get(field.getSuperclass()));

        System.out.println(withParam.build().toString());
    }
}
