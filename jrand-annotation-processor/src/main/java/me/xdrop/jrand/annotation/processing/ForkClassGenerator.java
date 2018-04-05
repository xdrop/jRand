package me.xdrop.jrand.annotation.processing;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;

import javax.annotation.Generated;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Types;
import java.util.Arrays;
import java.util.List;

import static me.xdrop.jrand.annotation.processing.ProcessorRepository.CLASS_SUFFIX;

public class ForkClassGenerator {

    private static String GENERATOR_ID = "me.xdrop.jrand.annotation.processing.ForkClassGenerator";
    private ProcessingEnvironment processingEnv;

    public ForkClassGenerator(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    /**
     * Create a {@code fork()} method for the given class. Fork will attempt to return a clone of this
     * generator by cloning lists, maps, primitive types as well as other generators.
     * While forking any other data structure is subject to accidental mutation.
     *
     * @param generator The source generator class (eg. PhoneGenerator)
     * @param pkg The package of the generator class
     * @param variableElements A list of other variables in that class
     * @return A generated {@code fork()} method
     */
    public MethodSpec createForkMethod(TypeElement generator, String pkg, List<VariableElement> variableElements) {
        AnnotationSpec generated = AnnotationSpec.builder(Generated.class)
                .addMember("value", "$S", GENERATOR_ID)
                .build();

        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("fork")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(generated)
                .returns(ClassName.get(generator));

        StringBuilder fields = new StringBuilder();
        fields.append("\n");
        TypeMirror listElement = processingEnv.getElementUtils().getTypeElement("java.util.List").asType();
        TypeMirror mapElement = processingEnv.getElementUtils().getTypeElement("java.util.Map").asType();
        TypeMirror generatorSuper = generator.getSuperclass();
        Types typeUtils = processingEnv.getTypeUtils();

        for (VariableElement variable : variableElements) {

            String varName = variable.getSimpleName().toString();
            if (!variable.getModifiers().contains(Modifier.FINAL)){
                if (typeUtils.isSubtype(variable.asType(), typeUtils.erasure(listElement))) {
                    fields.append("new java.util.ArrayList<>(");
                    fields.append(varName);
                    fields.append(")");
                } else if (typeUtils.isAssignable(variable.asType(), typeUtils.erasure(mapElement))) {
                    fields.append("new java.util.HashMap<>(");
                    fields.append(varName);
                    fields.append(")");
                } else if (variable.asType().getKind().isPrimitive()) {
                    fields.append(varName);
                } else if (typeUtils.isAssignable(variable.asType(), typeUtils.erasure(generatorSuper))) {
                    fields.append("((");
                    fields.append(variable.asType()).append(CLASS_SUFFIX);
                    fields.append(')');
                    fields.append(varName);
                    fields.append(')');
                    fields.append(".fork()");
                } else {
                    fields.append(varName);
                }

                fields.append(",\n");
            }

        }

        String fieldString = "";
        if (fields.length() > 1) {
            fieldString = fields.substring(0, fields.length() - 2);
        }

        ClassName generatedClassName = ClassName.get(pkg, generator.getSimpleName().toString().concat(CLASS_SUFFIX));
        methodBuilder.addStatement("return new $T($L)", generatedClassName, fieldString);

        return methodBuilder.build();
    }

    /**
     * Create a simple copy-constructor made from the given list of fields. The constructor
     * will blindly assign all fields given to the values given to the constructor.
     *
     * @param variableElements The list of fields which this constructor will assign to
     * @return The generated constructor
     */
    public MethodSpec createCopyConstructor(List<VariableElement> variableElements) {
        AnnotationSpec generated = AnnotationSpec.builder(Generated.class)
                .addMember("value", "$S", GENERATOR_ID)
                .build();

        MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .addAnnotation(generated);

        variableElements.forEach(varEl -> {
            if (!varEl.getModifiers().contains(Modifier.FINAL)) {
                String identifier = varEl.getSimpleName().toString();
                constructor.addParameter(ClassName.get(varEl.asType()), identifier);
                constructor.addStatement("this.$N = $N", identifier, identifier);
            }
        });

        return constructor.build();
    }

    public MethodSpec createDefaultConstructor() {
        AnnotationSpec generated = AnnotationSpec.builder(Generated.class)
                .addMember("value", "$S", GENERATOR_ID)
                .build();

        MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(generated);

        return constructor.build();
    }

    /**
     * Generate the {@code fork} and constructor methods. See {@link ForkClassGenerator#createCopyConstructor(List)}
     * and {@link ForkClassGenerator#createForkMethod(TypeElement, String, List)}
     *
     * @param sourceClass The source class for which to generate these for
     * @return A list containing the fork method at index [0], the default constructor at [1],
     * and the copy constructor at index [2]
     */
    public List<MethodSpec> getForkAndCloneMethods (TypeElement sourceClass) {
        List<VariableElement> variableElements = ElementFilter.fieldsIn(sourceClass.getEnclosedElements());
        String pkg = processingEnv.getElementUtils().getPackageOf(sourceClass).toString();

        MethodSpec forkMethod = createForkMethod(sourceClass, pkg, variableElements);
        MethodSpec copyConstructor = createCopyConstructor(variableElements);
        MethodSpec defaultConstructor = createDefaultConstructor();
        return Arrays.asList(forkMethod, defaultConstructor, copyConstructor);
    }

}
