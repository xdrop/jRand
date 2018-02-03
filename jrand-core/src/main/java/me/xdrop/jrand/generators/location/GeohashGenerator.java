package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.generators.basics.StringGenerator;

@Facade(accessor = "geohash")
public class GeohashGenerator extends Generator<String> {
    private int length;
    private StringGenerator string;

    public GeohashGenerator() {
        this.length = 7;
        this.string = new StringGenerator();
    }


    /**
     * Set the length of the geohash
     * @param length The length of the geohash
     * @return The same generator
     */
    public GeohashGenerator length(int length) {
        this.length = length;
        return this;
    }


    @Override
    public String gen() {
        return string.pool("0123456789bcdefghjkmnpqrstuvwxyz").length(this.length).gen();
    }
}
