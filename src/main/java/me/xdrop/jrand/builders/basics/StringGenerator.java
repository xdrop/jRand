package me.xdrop.jrand.builders.basics;

import me.xdrop.jrand.Generator;

public class StringGenerator extends Generator<String> {

    private CharacterGenerator charGen;
    private int min;
    private int max;
    private int length;

    public StringGenerator() {
        this.min = 1;
        this.max = 6;
        this.length = 0;
        this.charGen = new CharacterGenerator();
    }


    public StringGenerator pool(String pool) {
        charGen.pool(pool);
        return this;
    }

    public StringGenerator symbols() {
        charGen.symbols();
        return this;
    }

    public StringGenerator alpha() {
        charGen.alpha();
        return this;
    }

    public StringGenerator casing(CharacterGenerator.Casing casing) {
        charGen.casing(casing);
        return this;
    }

    public StringGenerator number() {
        charGen.number();
        return this;
    }

    public StringGenerator casing(String casing) {
        charGen.casing(casing);
        return this;
    }

    public StringGenerator range(int min, int max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public StringGenerator length(int length) {
        this.length = length;
        return this;
    }

    @Override
    public String gen() {
        StringBuilder builder = new StringBuilder(8);
        int length;
        if (this.length > 0) {
            length = this.length;
        } else {
            length = new NaturalGenerator().min(min).max(max).gen();
        }

        while (length > 0) {
            builder.append(charGen.gen());
            length--;
        }
        return builder.toString();
    }
}
