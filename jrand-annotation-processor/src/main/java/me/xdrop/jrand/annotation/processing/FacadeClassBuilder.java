package me.xdrop.jrand.annotation.processing;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class FacadeClassBuilder {

    public MethodSpec buildMethod(String accessor, TypeElement typeElement) {
        return  MethodSpec.methodBuilder(accessor)
                .addModifiers(Modifier.PUBLIC,Modifier.FINAL, Modifier.STATIC)
                .returns(ClassName.get(typeElement))
                .addStatement("return new $T()", ClassName.get(typeElement))
                .build();
    }

    public TypeSpec buildFacadeClass(Map<String,TypeElement> annotationMap) {
        TypeSpec.Builder jrandFacade = TypeSpec
                .classBuilder("JRand")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

        for (Map.Entry<String, TypeElement> facadeClass : annotationMap.entrySet()) {
            jrandFacade.addMethod(buildMethod(facadeClass.getKey(), facadeClass.getValue()));
        }

        return jrandFacade.build();

    }

    public void writeFacade(Writer writer, TypeSpec typeSpec, String packageName) throws IOException {
        JavaFile javaFile = JavaFile.builder(packageName, typeSpec).indent("    ").build();
        javaFile.writeTo(writer);
    }
}
