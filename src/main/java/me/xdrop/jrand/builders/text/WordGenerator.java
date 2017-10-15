package me.xdrop.jrand.builders.text;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.builders.basics.NaturalGenerator;

public class WordGenerator extends Generator<String> {

    private NaturalGenerator nat;
    private SyllableGenerator syl;
    private int syllablesMin;
    private int syllablesMax;
    private int length;

    public WordGenerator() {
        this.syllablesMin = 1;
        this.syllablesMax = 3;
        this.length = -1;
        this.syl = new SyllableGenerator();
        this.nat = new NaturalGenerator();
    }

    public WordGenerator setSyl(SyllableGenerator syl) {
        this.syl = syl;
        return this;
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

    /**
     * Set the number of syllables to return
     *
     * @param min Minimum number of syllables to return
     * @param max Maximum number of syllables to return
     * @return
     */
    public WordGenerator syllables(int min, int max) {
        this.syllablesMin = min;
        this.syllablesMax = max;
        return this;
    }

    @Override
    public String gen() {
        StringBuilder sbr = new StringBuilder(8);

        if (length > 0) {
            while (sbr.length() < length) {
                sbr.append(syl.gen());
            }
            return sbr.substring(0, length);
        } else {
            int syllables;
            if (syllablesMax == -1) {
                syllables = syllablesMin;
            } else {
                syllables = nat.range(syllablesMin, syllablesMax).gen();
            }
            for (int i = 0; i < syllables; i++) {
                sbr.append(syl.gen());
            }

            return sbr.toString();
        }

    }
}
