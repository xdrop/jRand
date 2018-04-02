package me.xdrop.jrand.generators.time;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.generators.basics.NaturalGenerator;

@Facade(accessor = "millisecond")
public class MillisecondGenerator extends Generator<String> {
    protected NaturalGenerator nat;

    public MillisecondGenerator() {
        this.nat = new NaturalGenerator();
        this.nat.range(0, 1000);
    }

    public int genInt() {
        return nat.gen();
    }

    @Override
    public String gen() {
        return nat.genString();
    }

}
