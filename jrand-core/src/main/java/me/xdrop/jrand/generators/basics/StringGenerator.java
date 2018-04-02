package me.xdrop.jrand.generators.basics;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.model.basics.enums.CHARSET;

@Facade(accessor = "string")
public class StringGenerator extends Generator<String> {

    protected CharacterGenerator charGen;
    protected int min;
    protected int max;
    protected int length;

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
     * @return The same generator
     */
    public StringGenerator pool(String pool) {
        charGen.pool(pool);
        return this;
    }

    /**
     * Return only symbols
     *
     * @return The same generator
     */
    public StringGenerator symbols() {
        charGen.symbols();
        return this;
    }

    /**
     * Return only alphabet characters
     *
     * @return The same generator
     */
    public StringGenerator alpha() {
        charGen.alpha();
        return this;
    }
    /**
     * Add digits to the pool of elements this generator
     * will return
     * @return The same generator
     */
    public StringGenerator addDigits() {
        charGen.addDigits();
        return this;
    }

    /**
     * Add letters to the pool of elements this generator
     * will return
     * @return The same generator
     */
    public StringGenerator addAlpha() {
        charGen.addAlpha();
        return this;
    }

    /**
     * Add symbols to the pool of elements this generator
     * will return
     * @return The same generator
     */
    public StringGenerator addSymbols() {
        charGen.addSymbols();
        return this;
    }

    /**
     * Add a charset to the pool
     * @param charset The charset to add
     * @return The same generator
     */
    public StringGenerator addCharset(CHARSET charset) {
        charGen.addCharset(charset);
        return this;
    }

    /**
     * Set the casing of the letters (in case alpha() is used)
     *
     * @param casing Casing of the letters
     * @return The same generator
     */
    public StringGenerator casing(CharacterGenerator.Casing casing) {
        charGen.casing(casing);
        return this;
    }

    /**
     * Return only digits
     *
     * @return The same generator
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
     * @return The same generator
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
     * @return The same generator
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
     * @return The same generator
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
