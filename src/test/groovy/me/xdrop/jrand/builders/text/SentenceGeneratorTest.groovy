package me.xdrop.jrand.builders.text


class SentenceGeneratorTest extends GroovyTestCase {
    def instance = {-> new SentenceGenerator()}

    void testWords() {
        assertTrue instance().words(5).gen().split(" ").length == 5
        def length = instance().words(1, 3).gen().split(" ").length
        assertTrue length <= 3 && length >= 1
    }

    void testPunctuation() {
        // Might need internal test
    }
}
