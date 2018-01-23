package me.xdrop.jrand.generators.text;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.model.RangeOption;

import java.util.List;

@Facade(accessor = "lorem")
public class LoremGenerator extends Generator<String> {
    private List<String> loremWords;
    private RangeOption noParagraphs;
    private RangeOption noSentences;
    private RangeOption noWords;
    private boolean capitalize;
    private boolean intro;
    private boolean single;

    public LoremGenerator() {
        this.loremWords = AssetLoader.loadList("lorem.txt");
    }

    /**
     * Set the number of paragraphs in the lorem
     * @param noParagraphs The number of paragraphs
     * @return The same generator
     */
    public LoremGenerator paragraphs(int noParagraphs) {
        this.noParagraphs = RangeOption.from(noParagraphs);
        return this;
    }

    /**
     * Set the number of paragraphs in the lorem
     * @param min Minimum number of paragraphs (Inclusive)
     * @param max Maximum number of paragraphs (Inclusive)
     * @return The same generator
     */
    public LoremGenerator paragraphs(int min, int max) {
        this.noParagraphs = RangeOption.from(min, max);
        return this;
    }

    /**
     * Set the number of sentencea in the lorem
     * @param noSentences The number of sentences
     * @return The same generator
     */
    public LoremGenerator sentences(int noSentences) {
        this.noSentences = RangeOption.from(noSentences);
        return this;
    }

    /**
     * Set the number of sentences in a paragraph
     * @param min Minimum number of sentences (Inclusive)
     * @param max Maximum number of sentences (Inclusive)
     * @return The same generator
     */
    public LoremGenerator sentences(int min, int max) {
        this.noSentences = RangeOption.from(min, max);
        return this;
    }

    /**
     * Set the number of words in a sentence
     * @param noWords The number of words
     * @return The same generator
     */
    public LoremGenerator words(int noWords) {
        this.noWords = RangeOption.from(noWords);
        return this;
    }

    /**
     * Set the number of words in a sentence
     * @param min Minimum number of words (Inclusive)
     * @param max Maximum number of words (Inclusive)
     * @return The same generator
     */
    public LoremGenerator words(int min, int max) {
        this.noWords = RangeOption.from(min, max);
        return this;
    }

    /**
     * Capitalize the first word
     * @param enabled True for enabled,
     *                False otherwise
     * @return The same generator
     */
    public LoremGenerator capitalize(boolean enabled) {
        this.capitalize = enabled;
        return this;
    }

    /**
     * Capitalize the first word
     * @return The same generator
     */
    public LoremGenerator capitalize() {
        return capitalize(true);
    }

    /**
     * Start with 'Lorem ipsum dolor sit amet...'
     * @param enabled True for enabled,
     *                False otherwise
     * @return The same generator
     */
    public LoremGenerator intro(boolean enabled) {
        this.intro = enabled;
        return this;
    }

    /**
     * Start with 'Lorem ipsum dolor sit amet...'
     * @return The same generator
     */
    public LoremGenerator intro() {
        return intro(true);
    }

    /**
     * Return a single word
     * @param enabled True for enabled,
     *                False otherwise
     * @return The same generator
     */
    public LoremGenerator single(boolean enabled) {
        this.single = enabled;
        return this;
    }

    /**
     * Return a single word
     * @return The same generator
     */
    public LoremGenerator single() {
        return single(true);
    }

    @Override
    public String gen() {
        return null;
    }
}
