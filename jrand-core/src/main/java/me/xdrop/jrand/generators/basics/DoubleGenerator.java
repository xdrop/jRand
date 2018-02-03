package me.xdrop.jrand.generators.basics;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.model.Range;

@Facade(accessor = "dbl")
public class DoubleGenerator extends Generator<Double> {

    private double min;
    private double max;

    public DoubleGenerator() {
        this.min = Double.MIN_VALUE;
        this.max = Double.MAX_VALUE;
    }

    /**
     * Sets the maximum value (inclusive)
     *
     * @param max The maximum value
     * @return The same generator
     */
    public DoubleGenerator max(double max) {
        this.max = max;
        return this;
    }

    /**
     * Set the minimum value (inclusive)
     *
     * @param min The minimum value
     * @return The same generator
     */
    public DoubleGenerator min(double min) {
        this.min = min;
        if (this.max < min) {
            this.max = min * 2;
        }
        return this;
    }

    /**
     * Set a min/max range
     *
     * @param min Minimum value to be returned (inclusive)
     * @param max Maximum value to be returned (inclusive)
     * @return The same generator
     */
    public DoubleGenerator range(double min, double max) {
        this.max = max;
        this.min = min;
        return this;
    }

    public DoubleGenerator range(Range<Double> range) {
        this.max = range.getMax();
        this.min = range.getMin();
        return this;
    }

    @Override
    public Double gen() {
        double rand = random().randDouble();
        return min + (rand * (max - min));
    }
}
