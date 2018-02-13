package me.xdrop.jrand.generators.time;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.generators.basics.NaturalGenerator;

@Facade(accessor = "second")
public class SecondGenerator extends Generator<Integer> {

    private NaturalGenerator nat;

    public SecondGenerator() {
        this.nat = new NaturalGenerator();
        this.nat.min(0).max(59);
    }

    @Override
    public Integer gen() {
        return this.nat.gen();
    }


}
