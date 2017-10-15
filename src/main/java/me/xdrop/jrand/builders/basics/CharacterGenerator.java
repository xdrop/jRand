package me.xdrop.jrand.builders.basics;

import me.xdrop.jrand.Constants;
import me.xdrop.jrand.Generator;
import me.xdrop.jrand.builders.basics.enums.CHARSET;

import java.util.*;

public class CharacterGenerator extends Generator<Character> {

    private String customPool;
    private List<Character> pool;
    private Set<CHARSET> includedCharsets;
    private boolean _default;

    public enum Casing {
        LOWER, UPPER
    }

    public CharacterGenerator() {
        this.includedCharsets = new HashSet<>();
        this.includedCharsets.add(CHARSET.CHARS_UPPER);
        this.includedCharsets.add(CHARSET.CHARS_LOWER);
        this.includedCharsets.add(CHARSET.SYMBOLS);
        this.includedCharsets.add(CHARSET.NUMBERS);
        this.customPool = null;
        this._default = true;
        this.pool = new ArrayList<>(32);
    }

    private void resetIncluded() {
        if (this._default) {
            this._default = false;
            includedCharsets.clear();
        }
    }

    public CharacterGenerator pool(String pool) {
        this.pool.clear();
        for (char c : pool.toCharArray()) {
            this.pool.add(c);
        }
        return this;
    }

    public CharacterGenerator symbols() {
        resetIncluded();
        includedCharsets.add(CHARSET.SYMBOLS);
        preparePool();
        return this;
    }

    public CharacterGenerator alpha() {
        resetIncluded();
        includedCharsets.add(CHARSET.CHARS_LOWER);
        includedCharsets.add(CHARSET.CHARS_UPPER);
        preparePool();
        return this;
    }


    public CharacterGenerator casing(Casing casing) {
        if (casing == Casing.LOWER) {
            includedCharsets.remove(CHARSET.CHARS_UPPER);
        } else {
            includedCharsets.remove(CHARSET.CHARS_LOWER);
        }
        preparePool();
        return this;
    }

    public CharacterGenerator number() {
        resetIncluded();
        includedCharsets.add(CHARSET.NUMBERS);
        preparePool();
        return this;
    }


    public CharacterGenerator casing(String casing) {
        if (casing.equalsIgnoreCase("lower")) {
            return casing(Casing.LOWER);
        } else {
            return casing(Casing.UPPER);
        }
    }

    private void preparePool() {
        pool.clear();
        for (CHARSET charset: includedCharsets) {
            for (char c : charset.getCharset()){
                pool.add(c);
            }
        }
    }


    @Override
    public Character gen() { ;

        if (pool.size() < 1){
            return 'c';
        }

        return pool.get(random().randInt(pool.size() - 1));
    }
}
