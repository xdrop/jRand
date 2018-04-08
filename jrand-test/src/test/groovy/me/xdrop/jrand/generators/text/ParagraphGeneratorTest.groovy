package me.xdrop.jrand.generators.text

import me.xdrop.jrand.JRand

class ParagraphGeneratorTest extends GroovyTestCase {
    def instance = { -> JRand.paragraph() }

    void testSentences() {
        assertTrue instance().sentences(10).gen().split("\\.").length == 10
        def length = instance().sentences(2, 3).gen().split("\\.").length
        assertTrue length >= 2 && length <= 3
    }

    void testPunctuation() {
        // TODO: Figure out a way to write this reliably
    }

    void testWordsPerSentence() {
        def sen = instance().sentences(1)
                .wordsPerSentence(1)
                .gen()
                .split(" ")
                .length == 1
        def zwx = instance().sentences(1)
                .wordsPerSentence(1, 3)
                .gen()
                .split(" ")
                .length
        assertTrue sen
        assertTrue zwx >= 1 && zwx <= 3

    }


}
