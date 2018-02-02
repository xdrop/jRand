package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;

@Facade(accessor = "coordinates")
public class CoordinatesGenerator extends Generator<String> {
    private int decimals;
    private LatitudeGenerator latitude;
    private LongitudeGenerator longitude;

    /**
     * Set the number of decimal digits to generate
     * @param noDecimals Number of decimal digits
     * @return The same generator
     */
    public CoordinatesGenerator decimals(int noDecimals) {
        latitude.decimals(noDecimals);
        longitude.decimals(noDecimals);
        return this;
    }

    @Override
    public String gen() {
        return latitude.gen() + ", " + longitude.gen();
    }
}
