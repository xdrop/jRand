package me.xdrop.jrand.builders.text


class SentenceGeneratorTest extends GroovyTestCase {
    def instance = {-> new SentenceGenerator()}

    void testWords() {
    }

    void testPunctuation() {
        println instance().punctuation().gen()
        println instance().punctuation().gen()
        println instance().punctuation().gen()
        println instance().punctuation().gen()
        println instance().punctuation().gen()
    }
}
