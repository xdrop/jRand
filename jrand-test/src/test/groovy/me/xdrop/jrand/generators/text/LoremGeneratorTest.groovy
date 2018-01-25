package me.xdrop.jrand.generators.text

class LoremGeneratorTest extends GroovyTestCase {
    def instance = {-> new LoremGenerator()}

    void testParagraphs() {
        println instance().gen()
    }

    void testParagraphs1() {
    }

    void testSentences() {
    }

    void testSentences1() {
    }

    void testWords() {
    }

    void testWords1() {
    }

    void testCapitalize() {
    }

    void testCapitalize1() {
    }

    void testIntro() {
        println instance().intro().gen()
    }

    void testIntro1() {
    }

    void testSingle() {
    }

    void testSingle1() {
    }
}
