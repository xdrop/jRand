package me.xdrop.jrand.builders.basics;

import me.xdrop.jrand.Generator;

import java.util.Random;

public class BoolGenerator extends Generator<Boolean> {

    private int likelihood;

    public BoolGenerator() {
        this.likelihood = 50;
    }

    public BoolGenerator likelihood(int likelihood) {
        this.likelihood = likelihood;
        return this;
    }

    @Override
    public Boolean generate() {
        float rand = random().randFloat();
        if (rand <= ((float)likelihood / 100)) {
            return true;
        } else {
            return false;
        }
    }
}
