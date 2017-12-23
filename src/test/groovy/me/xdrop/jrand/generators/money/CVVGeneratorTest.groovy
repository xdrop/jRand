package me.xdrop.jrand.generators.money

class CVVGeneratorTest extends GroovyTestCase {
    def instance = { -> new CVVGenerator() }

    void testIsThreeDigitsLong() {
        def cvv = instance()
        assertTrue cvv.gen().length() == 3
    }

    void testAmexIsFour() {
        def cvv = instance()
        assertTrue cvv.amex().gen().length() == 4
    }
}
