package me.xdrop.jrand.generators.text

class LoremGeneratorTest extends GroovyTestCase {
    def instance = { -> new LoremGenerator() }

    void testParagraphs() {
        def lorem = instance().paragraphs(3).gen().split("\n\n").length
        def lorem2 = instance().paragraphs(1, 3).gen().split("\n\n").length
        assertTrue lorem == 3
        assertTrue lorem2 >= 1 && lorem2 <= 3
    }

    void testSentences() {
        def sentence = instance().sentences(3).gen().split("\\.").length
        def gen = instance().sentences(1, 3).gen()
        def sentence2 = gen.split("\\.").length
        assertTrue sentence == 4 && sentence2 >= 1 && sentence2 <= 4
    }

    void testWords() {
        def words = instance().words(3).sentences(1).gen().split(" ").length

        def low = instance().words(1, 3).sentences(1).gen()
        def words2 = low.split(" ").length

        assertTrue words == 3 && words2 >= 1 && words2 <= 3
    }

    void testCapitalize() {
        def substring = instance().capitalize(true).gen().substring(0, 1)
        def substring2 = instance().capitalize(false).gen().substring(0, 1)
        assertTrue substring.toUpperCase() == substring
        assertTrue substring2.toLowerCase() == substring2
    }

    void testIntro() {
        instance().intro().gen().startsWith("Lorem ipsum dolor sit amet")
    }

    void testSingle() {
        instance().gen().trim().split(" ").length == 1
    }

    void testPrint() {
        println instance().gen()
    }

}
