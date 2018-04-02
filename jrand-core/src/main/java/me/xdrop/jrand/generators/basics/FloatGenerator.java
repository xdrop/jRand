package me.xdrop.jrand.generators.basics;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;

@Facade(accessor = "flt")
public class FloatGenerator extends Generator<Float> {
    protected float min;
    protected float max;

    public FloatGenerator() {
        this.min = Float.MIN_VALUE;
        this.max = Float.MAX_VALUE;
    }

    /**
     * Sets the maximum value (inclusive)
     *
     * @param max The maximum value
     * @return The same generator
     */
    public FloatGenerator max(float max) {
        this.max = max;
        return this;
    }

    /**
     * Set the minimum value (inclusive)
     *
     * @param min The minimum value
     * @return The same generator
     */
    public FloatGenerator min(float min) {
        this.min = min;
        return this;
    }

    /**
     * Set a min/max range
     *
     * @param min Minimum value to be returned (inclusive)
     * @param max Maximum value to be returned (inclusive)
     * @return The same generator
     */
    public FloatGenerator range(float min, float max) {
        this.max = max;
        this.min = min;
        return this;
    }

    @Override
    public Float gen() {
        float rand = random().randFloat();
        return min + (rand * (max - min));
    }
}
