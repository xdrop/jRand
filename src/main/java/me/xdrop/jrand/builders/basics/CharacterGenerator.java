package me.xdrop.jrand.builders.basics;

import me.xdrop.jrand.Constants;
import me.xdrop.jrand.Generator;
import me.xdrop.jrand.builders.basics.enums.CHARSET;

import java.util.*;

public class CharacterGenerator extends Generator<Character> {

    private String customPool;
    private boolean alpha;
    private boolean symbols;
    private Casing casing;
    private boolean number;

    public enum Casing {
        LOWER, UPPER
    }

    public CharacterGenerator() {
        this.customPool = null;
        this.alpha = false;
        this.symbols = false;
        this.number = false;
        this.casing = Casing.LOWER;
    }

    public CharacterGenerator pool(String pool) {
        this.customPool = pool;
        return this;
    }

    public CharacterGenerator symbols(boolean symbols) {
        this.symbols = symbols;
        return this;
    }

    public CharacterGenerator alpha(boolean alpha) {
        this.alpha = alpha;
        return this;
    }

    public CharacterGenerator symbols() {
        this.symbols = true;
        return this;
    }

    public CharacterGenerator alpha() {
        this.alpha = true;
        return this;
    }

    public CharacterGenerator casing(Casing casing) {
        this.casing = casing;
        return this;
    }

    public CharacterGenerator number() {
        this.number = true;
        return this;
    }

    public CharacterGenerator number(boolean number) {
        this.number = number;
        return this;
    }


    public CharacterGenerator casing(String casing) {
        if (casing.equalsIgnoreCase("lower")) {
            this.casing = Casing.LOWER;
        } else {
            this.casing = Casing.UPPER;
        }
        return this;
    }


    @Override
    public Character gen() {
        String charPool;
        String letterPool;

        if (customPool != null && customPool.length() > 0) {
            charPool = customPool;
        } else {
            if (casing == Casing.LOWER) {
                letterPool = Constants.alphaLowerPool;
            } else if (casing == Casing.UPPER) {
                letterPool = Constants.alphaUpperPool;
            } else {
                letterPool = Constants.alphaLowerPool + Constants.alphaUpperPool;
            }

            if (alpha) {
                charPool = letterPool;
            } else if (symbols) {
                charPool = Constants.symbolsPool;
            } else if (number) {
                charPool = Constants.numericPool;
            } else {
                charPool = letterPool + Constants.numericPool + Constants.symbolsPool;
            }
        }

        return charPool.charAt(random().randInt(charPool.length() - 1));
    }
}
