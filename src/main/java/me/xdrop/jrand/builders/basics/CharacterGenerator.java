package me.xdrop.jrand.builders.basics;

import com.sun.tools.internal.jxc.apt.Const;
import me.xdrop.jrand.Constants;
import me.xdrop.jrand.Generator;
import sun.plugin.converter.resources.Converter_sv;

public class CharacterGenerator extends Generator<Character> {

    private String customPool;
    private boolean alpha;
    private boolean symbols;
    private Casing casing;

    public CharacterGenerator() {
        this.customPool = null;
        this.alpha = false;
        this.symbols = false;
        this.casing = Casing.LOWER;
    }

    CharacterGenerator pool(String pool) {
        this.customPool = pool;
        return this;
    }

    CharacterGenerator symbols(boolean symbols) {
        this.symbols = symbols;
        return this;
    }

    CharacterGenerator casing(Casing casing) {
        this.casing = casing;
        return this;
    }

    @Override
    public Character generate() {
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
            } else {
                charPool = letterPool + Constants.numericPool + Constants.symbolsPool;
            }
        }

        return charPool.charAt(random().randInt(charPool.length() - 1));
    }


    public enum Casing {
        LOWER, UPPER
    }
}
