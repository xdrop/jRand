package me.xdrop.jrand.annotation.processing;

import com.github.javaparser.ast.CompilationUnit;
import com.squareup.javapoet.*;

import javax.annotation.Generated;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class PropertyMethodGenerator {

    private static String GENERATOR_ID = "me.xdrop.jrand.annotation.processing.PropertyMethodGenerator";
    private ProcessingEnvironment processingEnv;
    private ProcessorRepository repository;

    public PropertyMethodGenerator(ProcessingEnvironment processingEnv, ProcessorRepository repository) {
        this.processingEnv = processingEnv;
        this.repository = repository;
    }

    public List<MethodSpec> buildMethods(CompilationUnit source, VariableElement field, String javadoc) {
        AnnotationSpec generated = AnnotationSpec.builder(Generated.class)
                .addMember("value", "$S", GENERATOR_ID)
                .build();

        String name = field.getSimpleName().toString();
        MethodSpec withParam = buildMethodWithParam(field, generated, name, javadoc);
        MethodSpec withoutParam = buildMethodWithoutParam(field, generated, name, javadoc);

        return Arrays.asList(withParam, withoutParam);

    }


    protected MethodSpec buildMethodWithoutParam(VariableElement field, AnnotationSpec generated, String name, String javadoc) {
        return MethodSpec.methodBuilder(name)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(generated)
                .addStatement("return $L(true)", name)
                .returns(ClassName.get(field.getEnclosingElement().asType()))
                .addJavadoc(javadoc + "\n")
                .addJavadoc("@return The same generator\n")
                .build();
    }

    protected MethodSpec buildMethodWithParam(VariableElement field, AnnotationSpec generated, String name, String javadoc) {
        return MethodSpec.methodBuilder(name)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(generated)
                .addParameter(TypeName.BOOLEAN.unbox(), "enabled")
                .addStatement("this.$L = enabled", name)
                .addStatement("return this")
                .returns(ClassName.get(field.getEnclosingElement().asType()))
                .addJavadoc(javadoc + "\n")
                .addJavadoc("@return The same generator\n")
                .build();
    }
}
