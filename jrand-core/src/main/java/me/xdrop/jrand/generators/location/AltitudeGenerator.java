package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.generators.basics.DecimalGenerator;

@Facade(accessor = "altitude")
public class AltitudeGenerator extends Generator<String> {
    protected DecimalGenerator decimal;

    public AltitudeGenerator() {
        this.decimal = new DecimalGenerator();
        this.decimal.max(8848);
        this.decimal.digits(5);
    }

    /**
     * Set the maximum altitude
     * @param max The maximum altitude
     * @return The same generator
     */
    public AltitudeGenerator max(double max) {
        decimal.max(max);
        return this;
    }

    /**
     * By default 5 digits of accuracy are returned.
     * Use this function to override.
     *
     * @param digits Number of digits to return
     * @return The same generator
     */
    public AltitudeGenerator digits(int digits) {
        decimal.digits(digits);
        return this;
    }

    @Override
    public String gen() {
        return decimal.gen();
    }


}
