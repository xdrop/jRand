package me.xdrop.jrand.generators.basics;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import javax.annotation.Generated;

@Facade(accessor = "bool")
public class BoolGenerator extends Generator<Boolean> {

    private int likelihood;

    public BoolGenerator() {
        this.likelihood = 50;
    }

    /**
     * Sets the likelihood of generating a true value
     *
     * @param likelihood The likelihood as int between 0 and 100
     * @return The same generator
     */
    public BoolGenerator likelihood(int likelihood) {
        this.likelihood = likelihood;
        return this;
    }

    @Override
    public Boolean gen() {
        float rand = random().randFloat();
        return rand <= ((float) likelihood / 100);
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    public final BoolGenerator fork() {
        return new BoolGenerator(
                likelihood);
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    private BoolGenerator(int likelihood) {
        this.likelihood = likelihood;
    }
}
