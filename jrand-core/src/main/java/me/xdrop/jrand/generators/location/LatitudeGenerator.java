package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.generators.basics.DecimalGenerator;
import me.xdrop.jrand.model.Range;

@Facade(accessor = "latitude")
public class LatitudeGenerator extends Generator<String> {
    private Range<Double> range;
    private int decimals;
    private DecimalGenerator decimal;

    public LatitudeGenerator() {
        this.decimals = 5;
        this.decimal = new DecimalGenerator();
        this.decimal.range(Range.from(-90.0, 90.0));
    }

    /**
     * Set the number of decimals to return
     * @param noOfDecimals The number of decimals to return
     * @return The same generator
     */
    public LatitudeGenerator decimals(int noOfDecimals) {
        this.decimal.digits(noOfDecimals);
        return this;
    }

    /**
     * Set the minimum, and maximum latitude
     * @param min Minimum latitude (inclusive)
     * @param max Maximum latitude (inclusive)
     * @return The same generator
     */
    public LatitudeGenerator range(double min, double max) {
        this.decimal.range(min, max);
        return this;
    }

    @Override
    public String gen() {
        return decimal.gen();
    }
}
