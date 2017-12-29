package me.xdrop.jrand.generators.text;

import me.xdrop.jrand.CharUtils;
import me.xdrop.jrand.Generator;
import me.xdrop.jrand.generators.basics.NaturalGenerator;

public class ParagraphGenerator extends Generator<String> {

    private int minSentences;
    private int maxSentences;
    private String joining;
    private SentenceGenerator sentGen;
    private NaturalGenerator nat;

    public ParagraphGenerator() {
        this.nat = new NaturalGenerator();
        this.sentGen = new SentenceGenerator();
        this.minSentences = 3;
        this.maxSentences = 7;
        this.joining = " ";
    }

    /**
     * Set the number of sentences to return.
     *
     * @param sentences Number of sentences to return
     * @return The same generator
     */
    public ParagraphGenerator sentences(int sentences) {
        this.minSentences = sentences;
        this.maxSentences = -1;
        return this;
    }

    /**
     * Set the number of sentences to return.
     *
     * @param min Minimum number of sentences
     * @param max Maximum number of sentences
     * @return The same generator
     */
    public ParagraphGenerator sentences(int min, int max) {
        this.minSentences = min;
        this.maxSentences = max;
        return this;
    }

    /**
     * Enable extended punctuation (include ?,!,'s,') in sentence
     *
     * @return The same generator
     */
    public ParagraphGenerator punctuation() {
        sentGen.punctuation();
        return this;
    }

    /**
     * Generate a sentence with this amount of words
     *
     * @param wordsPerSentence Amount of words in the sentence
     * @return The same generator
     */
    public ParagraphGenerator wordsPerSentence(int wordsPerSentence) {
        sentGen.words(wordsPerSentence);
        return this;
    }

    /**
     * Set the minimum/maximum words in a sentence
     *
     * @param min Minimum number of words
     * @param max Maximum number of words
     * @return The same generator
     */
    public ParagraphGenerator wordsPerSentence(int min, int max) {
        sentGen.words(min, max);
        return this;
    }

    /**
     * Set the paragraph joining string
     * @param joining The joining string
     * @return The same generator
     */
    public ParagraphGenerator joining(String joining) {
        this.joining = joining;
        return this;
    }

    @Override
    public String gen(){
        int sentences;
        if (maxSentences == -1){
            sentences = minSentences;
        } else {
            sentences = nat.range(minSentences, maxSentences).gen();
        }
        return CharUtils.join(sentGen.genMany(sentences), joining);
    }
}
