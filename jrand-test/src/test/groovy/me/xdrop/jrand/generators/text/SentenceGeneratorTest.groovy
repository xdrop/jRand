package me.xdrop.jrand.generators.text

import me.xdrop.jrand.JRand


class SentenceGeneratorTest extends GroovyTestCase {
    def instance = { -> JRand.sentence() }

    void testWords() {
        assertTrue instance().words(5).gen().split(" ").length == 5
        def length = instance().words(1, 3).gen().split(" ").length
        assertTrue length <= 3 && length >= 1
    }

    void testPunctuation() {
        // Might need internal test
        // For now we do a simple test
        def sentence = instance().punctuation().words(5).gen()
        assertTrue sentence.contains(".") || sentence.contains("?") || sentence.contains("!")
    }
}
