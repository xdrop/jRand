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

    /**
     * Set the pool of characters to choose from
     *
     * @param pool The pool of characters to choose from
     * @return
     */
    public StringGenerator pool(String pool) {
        charGen.pool(pool);
        return this;
    }

    /**
     * Return only symbols
     *
     * @return
     */
    public StringGenerator symbols() {
        charGen.symbols();
        return this;
    }

    /**
     * Return only alphabet characters
     *
     * @return
     */
    public StringGenerator alpha() {
        charGen.alpha();
        return this;
    }

    /**
     * Set the casing of the letters (in case alpha() is used)
     *
     * @param casing Casing of the letters
     * @return
     */
    public StringGenerator casing(CharacterGenerator.Casing casing) {
        charGen.casing(casing);
        return this;
    }

    /**
     * Return only digits
     *
     * @return
     */
    public StringGenerator digits() {
        charGen.digit();
        return this;
    }

    /**
     * Set the casing of the letters (in case alpha() is used)
     *
     * "upper" is uppercase, "lower" is lowercase
     *
     * @param casing Casing of the letters. Use "upper" or "lower"
     * @return
     */
    public StringGenerator casing(String casing) {
        charGen.casing(casing);
        return this;
    }

    /**
     * Set the range of the length of the string between minimum of
     * min and maximum of max
     *
     * @param min Minimum string length (inclusive)
     * @param max Maximum string length (inclusive)
     * @return
     */
    public StringGenerator range(int min, int max) {
        this.min = min;
        this.max = max;
        return this;
    }

    /**
     * Set the length of the string
     *
     * @param length The length of the string
     * @return
     */
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
