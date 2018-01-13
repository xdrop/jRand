package me.xdrop.jrand.generators.basics;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.Tuple;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.model.basics.enums.CHARSET;

import java.nio.charset.Charset;
import java.util.*;
import javax.annotation.Generated;

@Facade(accessor = "character")
public class CharacterGenerator extends Generator<Character> {

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
        this.includedCharsets.add(CHARSET.NUMBERS);
        this._default = true;
        this.pool = new ArrayList<>(32);
        preparePool();
    }

    private void resetIncluded() {
        if (this._default) {
            this._default = false;
            includedCharsets.clear();
        }
    }

    /**
     * Set the pool of characters to choose from
     *
     * @param pool The pool of characters to choose from
     * @return The same generator
     */
    public CharacterGenerator pool(String pool) {
        if (pool == null) {
            return this;
        }
        this.pool.clear();
        for (char c : pool.toCharArray()) {
            this.pool.add(c);
        }
        return this;
    }

    /**
     * Return only symbols
     *
     * @return The same generator
     */
    public CharacterGenerator symbols() {
        resetIncluded();
        includedCharsets.add(CHARSET.SYMBOLS);
        preparePool();
        return this;
    }


    /**
     * Return only alphabet characters
     *
     * @return The same generator
     */
    public CharacterGenerator alpha() {
        resetIncluded();
        includedCharsets.add(CHARSET.CHARS_LOWER);
        includedCharsets.add(CHARSET.CHARS_UPPER);
        preparePool();
        return this;
    }

    /**
     * Set the casing of the letters (in case alpha() is used)
     *
     * @param casing Casing of the letters
     * @return The same generator
     */
    public CharacterGenerator casing(Casing casing) {
        if (casing == Casing.LOWER) {
            includedCharsets.remove(CHARSET.CHARS_UPPER);
        } else {
            includedCharsets.remove(CHARSET.CHARS_LOWER);
        }
        preparePool();
        return this;
    }

    /**
     * Return only digits
     *
     * @return The same generator
     */
    public CharacterGenerator digit() {
        resetIncluded();
        includedCharsets.add(CHARSET.NUMBERS);
        preparePool();
        return this;
    }

    /**
     * Add digits to the pool of elements this generator
     * will return
     * @return The same generator
     */
    public CharacterGenerator addDigits() {
        includedCharsets.add(CHARSET.NUMBERS);
        preparePool();
        return this;
    }

    /**
     * Add letters to the pool of elements this generator
     * will return
     * @return The same generator
     */
    public CharacterGenerator addAlpha() {
        includedCharsets.add(CHARSET.CHARS_LOWER);
        includedCharsets.add(CHARSET.CHARS_UPPER);
        preparePool();
        return this;
    }

    /**
     * Add symbols to the pool of elements this generator
     * will return
     * @return The same generator
     */
    public CharacterGenerator addSymbols() {
        includedCharsets.add(CHARSET.SYMBOLS);
        preparePool();
        return this;
    }

    /**
     * Add a charset to the pool
     * @param charset The charset to add
     * @return The same generator
     */
    public CharacterGenerator addCharset(CHARSET charset) {
        includedCharsets.add(charset);
        preparePool();
        return this;
    }

    /**
     * Set the casing of the letters (in case alpha() is used)
     *
     * "upper" is uppercase, "lower" is lowercase
     *
     * @param casing Casing of the letters. Use "upper" or "lower"
     * @return The same generator
     */
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
    public Character gen() {

        if (pool == null || pool.size() < 1){
            throw new RuntimeException("The character pool is empty, please ensure you call .pool()" +
                    "before continuing");
        }

        return pool.get(random().randInt(pool.size() - 1));
    }

    /**
     * When specifying a pool you can use this to generate an item from the pool
     * along with its index it in the pool
     * @return A {@link Tuple} of a character and its index in the pool
     */
    public Tuple<Character, Integer> genWithIndex() {
        if (pool == null || pool.size() < 1){
            throw new RuntimeException("The character pool is empty, please ensure you call .pool()" +
                    "before continuing");
        }

        int index = random().randInt(pool.size() - 1);
        return Tuple.from(pool.get(index), index);
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    public final CharacterGenerator fork() {
        return new CharacterGenerator(
                new java.util.ArrayList<>(pool),
                includedCharsets,
                _default);
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    private CharacterGenerator(List<Character> pool, Set<CHARSET> includedCharsets,
            boolean _default) {
        this.pool = pool;
        this.includedCharsets = includedCharsets;
        this._default = _default;
    }
}
