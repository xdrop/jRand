package me.xdrop.jrand.builders.basics;

import me.xdrop.jrand.Generator;

public class FloatGenerator extends Generator<Float> {
    private float min;
    private float max;

    public FloatGenerator() {
        this.min = 0;
        this.max = 1;
    }

    /**
     * Sets the maximum value (inclusive)
     * @param max The maximum value
     * @return
     */
    public FloatGenerator max(float max) {
        this.max = max;
        return this;
    }

    /**
     * Set the minimum value (inclusive)
     * @param min The minimum value
     * @return
     */
    public FloatGenerator min(float min) {
        this.min = min;
        return this;
    }

    @Override
    public Float rand() {
        float rand = random().randFloat();
        float result = min + (rand * (max-min));
        return result;
    }
}
