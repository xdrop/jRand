package me.xdrop.jrand.annotation.processing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.utils.SourceRoot;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProcessorRepository {
    public static String importClassName = "javax.annotation.Generated";
    private Map<String, Integer> rounds;
    private Map<String, CompilationUnit> compilationUnits;
    private SourceRoot sourceRoot;
    private int noProcessors;
    private ImportDeclaration generatedImportDecl;

    public ProcessorRepository() {
        this.rounds = new HashMap<>();
        this.compilationUnits = new HashMap<>();
        Path root = Paths.get("jrand-core", "src", "main", "java");
        sourceRoot = new SourceRoot(root);
        noProcessors = 2;
        generatedImportDecl = LexicalPreservingPrinter.setup(JavaParser.parseImport("import " + importClassName + ";\n"));
        generatedImportDecl.getData(LexicalPreservingPrinter.NODE_TEXT_DATA).addToken(0, 3, "\n");
    }

    private CompilationUnit parseCU(String pkg, String filename) {
        System.out.println(filename);
        return LexicalPreservingPrinter.setup(sourceRoot.parse(pkg, filename));
    }

    public void addRound(String className) {
        int current = rounds.getOrDefault(className, 0);
        rounds.put(className, current + 1);
    }

    public boolean isComplete(String className) {
        return rounds.getOrDefault(className, 0) == noProcessors;
    }

    public void addCU(String className, CompilationUnit cu) {
        compilationUnits.put(className, cu);
    }

    public CompilationUnit getCU(String pkg, String className) {
        CompilationUnit compilationUnit = compilationUnits.get(className);
        if (compilationUnit == null) {
            compilationUnit = parseCU(pkg, className + ".java");
        }
        return compilationUnit;
    }

    private TypeSpec generateClassWithMethods(String className, MethodSpec... methodSpecs) {
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className);
        for (MethodSpec m : methodSpecs) {
            classBuilder.addMethod(m);
        }
        return classBuilder.build();
    }

    private CompilationUnit stringToCU(String classFile) {
        return LexicalPreservingPrinter.setup(JavaParser.parse(classFile));
    }

    private Optional<ClassOrInterfaceDeclaration> getClassByName(CompilationUnit source, String name) {
        return source.getClassByName(name);
    }

    private boolean hasGeneratedImport(CompilationUnit source) {
        for (ImportDeclaration imp : source.getImports()) {
            if (imp.getName().asString().equals(importClassName)) {
                return true;
            }
        }
        return false;
    }

    public CompilationUnit addMethods(CompilationUnit source, String className, String pkg, MethodSpec... methodSpecs) {
        Optional<ClassOrInterfaceDeclaration> clazzInSource = getClassByName(source, className);
        TypeSpec builtClass = generateClassWithMethods(className, methodSpecs);
        String generatedToParse = JavaFile.builder(pkg, builtClass).indent("    ").build().toString();
        CompilationUnit cuToMerge = stringToCU(generatedToParse);
        List<MethodDeclaration> methodsToMerge = cuToMerge.findAll(MethodDeclaration.class);
        List<ConstructorDeclaration> constructorsToMerge = cuToMerge.findAll(ConstructorDeclaration.class);

        if (!hasGeneratedImport(source)) {
            source.addImport(importClassName);
        }

        if (!clazzInSource.isPresent()) {
            return null;
        }

        for (MethodDeclaration method : methodsToMerge) {
            clazzInSource.get().addMember(method);
        }

        for (ConstructorDeclaration constructor : constructorsToMerge) {
            clazzInSource.get().addMember(constructor);
        }

        return source;

    }

    public void writeTo(Path path, String className, CompilationUnit cu) throws IOException {
        try (FileWriter fw = new FileWriter(new File(path.toString(), className + ".java"))) {
            if (cu != null) {
                LexicalPreservingPrinter.print(cu, fw);
            }
        }
    }

}
