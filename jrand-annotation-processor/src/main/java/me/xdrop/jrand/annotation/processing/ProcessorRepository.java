package me.xdrop.jrand.annotation.processing;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.utils.SourceRoot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ProcessorRepository {
    private Map<String, Integer> rounds;
    private Map<String, CompilationUnit> compilationUnits;
    private SourceRoot sourceRoot;
    private int noProcessors;

    public ProcessorRepository() {
        this.rounds = new HashMap<>();
        this.compilationUnits = new HashMap<>();
        Path root = Paths.get("jrand-core", "src", "main", "java");
        sourceRoot = new SourceRoot(root);
        noProcessors = 2;
    }

    private CompilationUnit parseCU(String pkg, String filename){
        return LexicalPreservingPrinter.setup(sourceRoot.parse(pkg, filename));
    }

    public void addRound(String className) {
        int current = rounds.get(className);
        rounds.put(className, current + 1);
    }

    public boolean isComplete(String className) {
        return rounds.get(className) == noProcessors;
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

    public void writeTo(Path path, String className, CompilationUnit cu) throws IOException{
        if (isComplete(className)) {
            try (FileWriter fw = new FileWriter(new File(path.toString(), className + ".java"))) {
                if (cu != null) {
                    LexicalPreservingPrinter.print(cu, fw);
                }
            }
        }
    }

}
