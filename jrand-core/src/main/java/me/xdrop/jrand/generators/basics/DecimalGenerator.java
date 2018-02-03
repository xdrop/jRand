package me.xdrop.jrand.generators.basics;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.model.Range;

import java.math.BigDecimal;

@Facade(accessor = "decimal")
public class DecimalGenerator extends Generator<String> {

    private Range<Double> range;
    private int digits;
    private boolean roundUp;
    private DoubleGenerator dbl;

    public DecimalGenerator() {
        this.roundUp = true;
        this.dbl = new DoubleGenerator();
        this.range = Range.from(100.0);
    }

    /**
     * Set the minimum value (inclusive)
     *
     * @param min The minimum value
     * @return The same generator
     */
    public DecimalGenerator min(double min) {
        dbl.min(min);
        return this;
    }

    /**
     * Sets the maximum value (inclusive)
     *
     * @param max The maximum value
     * @return The same generator
     */
    public DecimalGenerator max(double max) {
        dbl.max(max);
        return this;
    }

    /**
     * Set a min/max range
     *
     * @param min Minimum value to be returned (inclusive)
     * @param max Maximum value to be returned (inclusive)
     * @return The same generator
     */
    public DecimalGenerator range(double min, double max) {
        dbl.range(min, max);
        return this;
    }

    /**
     * Set a min/max range
     * @param range The range
     * @return The same generator
     */
    public DecimalGenerator range(Range<Double> range) {
        dbl.range(range);
        return this;
    }

    /**
     * Sets the number of digits to return
     *
     * @param digits Number of digits
     * @return The same generator
     */
    public DecimalGenerator digits(int digits) {
        this.digits = digits;
        return this;
    }

    /**
     * Set whether to round up or down
     *
     * @param roundUp True for round up, false for round down
     * @return The same generator
     */
    public DecimalGenerator roundUp(boolean roundUp) {
        this.roundUp = roundUp;
        return this;
    }

    /**
     * Generate a {@link BigDecimal} as opposed to a String
     *
     * @return Return the value as BigDecimal
     */
    public BigDecimal genAsDecimal() {
        double rand = dbl.gen();
        BigDecimal decimal;
        if (digits != 0) {
            if (roundUp) {
                decimal = BigDecimal.valueOf(rand).setScale(digits, BigDecimal.ROUND_UP);
            } else {
                decimal = BigDecimal.valueOf(rand).setScale(digits, BigDecimal.ROUND_DOWN);
            }
        } else {
            decimal = BigDecimal.valueOf(rand);
        }

        return decimal;
    }

    @Override
    public String gen() {
        return genAsDecimal().toString();
    }
}
