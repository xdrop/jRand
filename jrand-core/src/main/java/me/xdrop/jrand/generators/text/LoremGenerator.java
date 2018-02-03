package me.xdrop.jrand.generators.text;

import me.xdrop.jrand.CharUtils;
import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.data.Asset;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.data.Assets;
import me.xdrop.jrand.generators.basics.NaturalGenerator;
import me.xdrop.jrand.model.Range;
import me.xdrop.jrand.utils.Choose;

import java.util.List;

@Facade(accessor = "lorem")
public class LoremGenerator extends Generator<String> {
    private NaturalGenerator nat;

    private String introText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";

    private List<String> loremWords;
    private Range noParagraphs;
    private Range noSentences;
    private Range noWords;
    private boolean capitalize;
    private boolean intro;
    private boolean single;
    private boolean commas;

    public LoremGenerator() {
        this.loremWords = Assets.LOREM.loadItems();
        this.noParagraphs = Range.from(1);
        this.noSentences = Range.from(5,8);
        this.noWords = Range.from(5,10);
        this.commas = true;
        this.capitalize = true;
        this.nat = new NaturalGenerator();
    }

    /**
     * Set the number of paragraphs in the lorem
     *
     * @param noParagraphs The number of paragraphs
     * @return The same generator
     */
    public LoremGenerator paragraphs(int noParagraphs) {
        this.noParagraphs = Range.from(noParagraphs);
        return this;
    }

    /**
     * Set the number of paragraphs in the lorem
     *
     * @param min Minimum number of paragraphs (Inclusive)
     * @param max Maximum number of paragraphs (Inclusive)
     * @return The same generator
     */
    public LoremGenerator paragraphs(int min, int max) {
        this.noParagraphs = Range.from(min, max);
        return this;
    }

    /**
     * Set the number of sentencea in the lorem
     *
     * @param noSentences The number of sentences
     * @return The same generator
     */
    public LoremGenerator sentences(int noSentences) {
        this.noSentences = Range.from(noSentences);
        return this;
    }

    /**
     * Set the number of sentences in a paragraph
     *
     * @param min Minimum number of sentences (Inclusive)
     * @param max Maximum number of sentences (Inclusive)
     * @return The same generator
     */
    public LoremGenerator sentences(int min, int max) {
        this.noSentences = Range.from(min, max);
        return this;
    }

    /**
     * Set the number of words in a sentence
     *
     * @param noWords The number of words
     * @return The same generator
     */
    public LoremGenerator words(int noWords) {
        this.noWords = Range.from(noWords);
        return this;
    }

    /**
     * Set the number of words in a sentence
     *
     * @param min Minimum number of words (Inclusive)
     * @param max Maximum number of words (Inclusive)
     * @return The same generator
     */
    public LoremGenerator words(int min, int max) {
        this.noWords = Range.from(min, max);
        return this;
    }

    /**
     * Capitalize the first word
     *
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
     *
     * @return The same generator
     */
    public LoremGenerator capitalize() {
        return capitalize(true);
    }

    /**
     * Start with 'Lorem ipsum dolor sit amet...'
     *
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
     *
     * @return The same generator
     */
    public LoremGenerator intro() {
        return intro(true);
    }

    /**
     * Return a single word
     *
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
     *
     * @return The same generator
     */
    public LoremGenerator single() {
        return single(true);
    }

    /**
     * Enable/Disable adding commas
     * @param enabled True for enabled,
     *                False for disabled
     * @return The same generator
     */
    public LoremGenerator commas(boolean enabled) {
        this.commas = enabled;
        return this;
    }

    private String word() {
        return Choose.one(loremWords);
    }

    private String sentence(boolean intro) {
        int number = nat.range(noWords).gen();
        StringBuilder sentence = new StringBuilder(64);
        String previousWord = "";

        if (intro) {
            sentence.append(introText).append(". ");
            return sentence.toString();
        }

        while (number > 0) {
            if (commas && sentence.length() > 0 && nat.range(0,5).gen() == 5) {
                sentence.append(",");
            }
            if (sentence.length() > 0) sentence.append(" ");
            String nextWord;
            // Avoid duplicate words
            while ((nextWord = word()).equals(previousWord)){ }
            sentence.append(nextWord);
            previousWord = nextWord;
            number--;
        }

        sentence.append(". ");

        return capitalize ? CharUtils.capitalize(sentence.toString()): sentence.toString();
    }

    private String paragraph(boolean intro) {
        int number = nat.range(noSentences).gen();
        StringBuilder paragraph = new StringBuilder(512);

        if (intro) {
            paragraph.append(sentence(true));
            number--;
        }

        while (number > 0) {
            paragraph.append(sentence(false));
            number--;
        }


        return paragraph.toString();
    }

    @Override
    public String gen() {
        StringBuilder loremIpsum = new StringBuilder(512);

        if (single) {
            return capitalize ? CharUtils.capitalize(word()) : word();
        }

        int number = nat.range(noParagraphs).gen();

        if (intro) {
            loremIpsum.append(paragraph(true));
            number--;
        }

        while (number > 0) {
            loremIpsum.append(paragraph(false));
            number--;
            if (number != 0) {
                loremIpsum.append("\n\n");
            }
        }

        return loremIpsum.toString();

    }
}
