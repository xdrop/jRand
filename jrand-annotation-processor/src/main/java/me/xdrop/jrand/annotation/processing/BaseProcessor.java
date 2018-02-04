package me.xdrop.jrand.annotation.processing;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class BaseProcessor extends AbstractProcessor {

    public static String GENERATED_PACKAGE = "me.xdrop.jrand.generated.generators";
    public static String packageName = "me.xdrop.jrand";
    public static String generatorPath = "generators";
    private ProcessorRepository repository;
    private Messager messager;
    private Filer filer;
    private Path outputPathGenerators;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.filer = processingEnv.getFiler();
        this.repository = new ProcessorRepository();
        this.outputPathGenerators = Paths.get("jrand-core", "src", "generated",
                "java", "me", "xdrop", "jrand", "generators");
    }

    public Messager getMessager() {
        return messager;
    }

    public Filer getFiler() {
        return filer;
    }

    public ProcessorRepository getRepository() {
        return repository;
    }

    public Path getOutputPathGenerators() {
        return outputPathGenerators;
    }
}
