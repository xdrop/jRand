package me.xdrop.jrand.generators.time;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.annotation.PropertyFlag;
import me.xdrop.jrand.generators.basics.NaturalGenerator;

@Facade(accessor = "hour")
public class HourGenerator extends Generator<String> {

    private NaturalGenerator nat;

    @PropertyFlag("Enable to return an hour between 1 and 24")
    private boolean twentyfour;

    public HourGenerator() {
        this.nat = new NaturalGenerator();
        this.nat.min(1).max(12);
    }


    public int genInt() {
        if (twentyfour) {
            nat.range(1, 24);
        } else {
            nat.range(1, 12);
        }
        return nat.gen();
    }

    @Override
    public String gen() {
        return genInt() + "";
    }


}
