package me.xdrop.jrand.builders.basics;

import me.xdrop.jrand.Generator;

import java.util.Random;

public class BoolGenerator extends Generator<Boolean> {

    private int likelihood;

    public BoolGenerator() {
        this.likelihood = 50;
    }

    /**
     * Sets the likelihood of generating a true value
     * @param likelihood The likelihood as int between 0 and 100
     * @return
     */
    public BoolGenerator likelihood(int likelihood) {
        this.likelihood = likelihood;
        return this;
    }

    @Override
    public Boolean rand() {
        float rand = random().randFloat();
        if (rand <= ((float)likelihood / 100)) {
            return true;
        } else {
            return false;
        }
    }
}
