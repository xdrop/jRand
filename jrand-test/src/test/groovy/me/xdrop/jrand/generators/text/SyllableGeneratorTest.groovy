package me.xdrop.jrand.generators.text

import me.xdrop.jrand.JRand

class SyllableGeneratorTest extends GroovyTestCase {
    def instance = { -> JRand.syllable() }

    void testLength() {
        assertTrue instance().length(3).gen().length() == 3
        assertTrue instance().length(4).gen().length() == 4
    }

    void testCapitalize() {
        def letter = instance().capitalize(true).gen().substring(0, 1)
        assertTrue letter == letter.toUpperCase()
    }
}
