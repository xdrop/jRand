package me.xdrop.jrand.builders.text;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.builders.basics.NaturalGenerator;

public class WordGenerator extends Generator<String> {

    private NaturalGenerator nat;
    private SyllableGenerator syl;
    private int syllablesMin;
    private int syllablesMax;
    private int fixedSyllables;
    private int length;

    public WordGenerator() {
        this.syllablesMin = 1;
        this.syllablesMax = 3;
        this.fixedSyllables = -1;
        this.length = -1;
        this.syl = new SyllableGenerator();
        this.nat = new NaturalGenerator();
    }

    /**
     * Set the length of the word to return
     *
     * @param length The length of the word to return
     * @return
     */
    public WordGenerator length(int length) {
        this.length = length;
        return this;
    }

    /**
     * Set the number of syllables to return
     *
     * @param syllables Number of syllables to return
     * @return
     */
    public WordGenerator syllables(int syllables) {
        this.syllablesMin = syllables;
        this.syllablesMax = -1;
        return this;
    }

    @Override
    public String gen() {
        StringBuilder sbr = new StringBuilder(8);

        if (length > 0) {
            while (sbr.length() < length) {
                sbr.append(syl.gen());
            }
            return sbr.substring(0, length - 1);
        } else {
            int syllables;
            if (fixedSyllables == -1) {
                syllables = nat.range(syllablesMin,syllablesMax).gen();
            } else {
                syllables = fixedSyllables;
            }
            for (int i = 0; i < syllables; i++) {
                sbr.append(syl.gen());
            }

            return sbr.toString();
        }

    }
}
