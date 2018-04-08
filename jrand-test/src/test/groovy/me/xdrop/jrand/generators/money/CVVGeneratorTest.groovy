package me.xdrop.jrand.generators.money

import me.xdrop.jrand.JRand

class CVVGeneratorTest extends GroovyTestCase {
    def instance = { -> JRand.cvv() }

    void testIsThreeDigitsLong() {
        def cvv = instance()
        assertTrue cvv.gen().length() == 3
    }

    void testAmexIsFour() {
        def cvv = instance()
        assertTrue cvv.amex().gen().length() == 4
    }

    void testPrint() {
        println instance().gen()
        println instance().amex().gen()
    }
}
