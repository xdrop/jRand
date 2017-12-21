package me.xdrop.jrand.generators.basics;

import me.xdrop.jrand.Generator;

public class BoolGenerator extends Generator<Boolean> {

    private int likelihood;

    public BoolGenerator() {
        this.likelihood = 50;
    }

    /**
     * Sets the likelihood of generating a true value
     *
     * @param likelihood The likelihood as int between 0 and 100
     * @return
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
}
