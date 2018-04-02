package me.xdrop.jrand.generators.time;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.generators.basics.NaturalGenerator;

@Facade(accessor = "second")
public class SecondGenerator extends Generator<String> {

    protected NaturalGenerator nat;

    public SecondGenerator() {
        this.nat = new NaturalGenerator();
        this.nat.min(0).max(59);
    }

    public int genInt() {
        return nat.gen();
    }

    @Override
    public String gen() {
        return nat.genString();
    }


}
