package me.xdrop.jrand.builders.text;

import me.xdrop.jrand.CharUtils;
import me.xdrop.jrand.Generator;
import me.xdrop.jrand.builders.basics.NaturalGenerator;

public class ParagraphGenerator extends Generator<String> {

    private int sentences;
    private SentenceGenerator sentGen;

    public ParagraphGenerator() {
        this.sentences = new NaturalGenerator().range(3,7).gen();
        this.sentGen = new SentenceGenerator();
    }

    /**
     * Set the number of sentences to return.
     *
     * @param sentences Number of sentences to return
     * @return
     */
    public ParagraphGenerator sentences(int sentences) {
        this.sentences = sentences;
        return this;
    }

    /**
     * Enable extended punctuation (include ?,!,'s,') in sentence
     *
     * @return
     */
    public ParagraphGenerator punctuation() {
        sentGen.punctuation();
        return this;
    }

    /**
     * Generate a sentence with this amount of words
     *
     * @param wordsPerSentence Amount of words in the sentence
     * @return
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
     * @return
     */
    public ParagraphGenerator wordsPerSentence(int min, int max) {
        sentGen.words(min, max);
        return this;
    }

    @Override
    public String gen(){
        return CharUtils.join(sentGen.genMany(this.sentences)," ");
    }
}
