package me.xdrop.jrand.generators.time;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.generators.basics.NaturalGenerator;

@Facade(accessor = "minute")
public class MinuteGenerator extends Generator<String> {

    private NaturalGenerator nat;

    public MinuteGenerator() {
        this.nat = new NaturalGenerator();
        this.nat.range(0, 59);
    }

    public int genInt() {
        return nat.gen();
    }

    @Override
    public String gen() {
        return nat.genString();
    }
}
