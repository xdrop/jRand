package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.generators.basics.DecimalGenerator;
import me.xdrop.jrand.model.Range;

@Facade(accessor = "depth")
public class DepthGenerator extends Generator<String> {
    private DecimalGenerator decimal;
    private int noDecimals;

    public DepthGenerator() {
        this.decimal = new DecimalGenerator().range(Range.from(-10994.0, 0.0));
        this.noDecimals = 5;
    }

    /**
     * Set the number of digits after the decimal place
     * @param noDecimals Number
     * @return The same generator
     */
    public DepthGenerator decimals(int noDecimals) {
        decimal.digits(noDecimals);
        return this;
    }

    /**
     * Set the minimum depth
     * @param min The minimum depth
     * @return The same generator
     */
    public DepthGenerator min(double min) {
        decimal.min(min);
        return this;
    }

    /**
     * Set the maximum depth
     * @param max The maximum depth
     * @return The same generator
     */
    public DepthGenerator max(double max) {
        decimal.max(max);
        return this;
    }

    /**
     * Set the minimum and maximum depth
     * @param min Minimum depth (inclusive)
     * @param max Maximum depth (inclusive)
     * @return The same generator
     */
    public DepthGenerator range(double min, double max) {
        decimal.range(min, max);
        return this;
    }

    @Override
    public String gen() {
        return decimal.gen();
    }
}
