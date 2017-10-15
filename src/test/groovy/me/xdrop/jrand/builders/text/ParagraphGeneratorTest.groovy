package me.xdrop.jrand.builders.text

class ParagraphGeneratorTest extends GroovyTestCase {
    def instance = {-> new ParagraphGenerator()}

    void testSentences() {
        println instance().gen()
    }

    void testPunctuation() {
    }

    void testWordsPerSentence() {
    }

    void testWordsPerSentence1() {
    }
}
