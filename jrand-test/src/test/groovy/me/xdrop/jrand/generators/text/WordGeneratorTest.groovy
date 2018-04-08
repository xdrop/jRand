package me.xdrop.jrand.generators.text

import me.xdrop.jrand.JRand

class WordGeneratorTest extends GroovyTestCase {
    def instance = { -> JRand.word() }

    void testLength() {
        assertTrue instance().length(6).gen().length() == 6
    }

    void testSyllables() {
        def syl = new SyllableGenerator().length(1)
        assertTrue instance().setSyl(syl).syllables(3)
                .length(3).gen().length() == 3
        def res = instance().setSyl(syl).syllables(5, 6).gen()
        assertTrue res.length() >= 5 && res.length() <= 6
    }
}
