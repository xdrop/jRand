package me.xdrop.jrand.annotation.processing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.squareup.javapoet.*;

import javax.annotation.Generated;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.util.Optional;

public class PropertyMethodGenerator {

    private static String GENERATOR_ID = "me.xdrop.jrand.annotation.processing.PropertyMethodGenerator";
    private ProcessingEnvironment processingEnv;

    public PropertyMethodGenerator(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    public void buildMethod(CompilationUnit source, VariableElement field, String javadoc) {
        AnnotationSpec generated = AnnotationSpec.builder(Generated.class)
                .addMember("value", "$S", GENERATOR_ID)
                .build();

        String name = field.getSimpleName().toString();
        MethodSpec withParam = buildMethodWithParam(field, generated, name);
        MethodSpec withoutParam = buildMethodWithoutParam(field, generated, name);


    }

    protected MethodSpec buildMethodWithoutParam(VariableElement field, AnnotationSpec generated, String name) {
        return MethodSpec.methodBuilder(name)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(generated)
                .addStatement("return $L(true)", name)
                .returns(ClassName.get(field.getEnclosingElement().asType()))
                .build();
    }

    protected MethodSpec buildMethodWithParam(VariableElement field, AnnotationSpec generated, String name) {
        return MethodSpec.methodBuilder(name)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(generated)
                .addParameter(TypeName.BOOLEAN.unbox(), "enabled")
                .addStatement("this.$L = enabled", name)
                .addStatement("return this")
                .returns(ClassName.get(field.getEnclosingElement().asType()))
                .build();
    }
}
