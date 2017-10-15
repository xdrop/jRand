package me.xdrop.jrand.builders.basics;

import me.xdrop.jrand.Generator;

public class NaturalGenerator extends Generator<Integer> {

    private int min;
    private int max;

    public NaturalGenerator() {
        this.max = Integer.MAX_VALUE;
        this.min = 0;
    }

    /**
     * Set the minimum value (inclusive)
     *
     * @param min The minimum value
     * @return
     */
    public NaturalGenerator min(int min) {
        this.min = min;
        return this;
    }

    /**
     * Set the maximum value to return (inclusive)
     *
     * @param max The maximum value to return (inclusive)
     * @return
     */
    public NaturalGenerator max(int max) {
        this.max = max;
        return this;
    }

    /**
     * Set a min/max range
     *
     * @param min Minimum value to be returned (inclusive)
     * @param max Maximum value to be returned (inclusive)
     * @return
     */
    public NaturalGenerator range(int min, int max) {
        min(min);
        max(max);
        return this;
    }

    @Override
    public Integer gen() {
        return random().randInt((max - min) + 1) + min;
    }
}
