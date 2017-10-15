package me.xdrop.jrand.builders.basics

class DecimalGeneratorTest extends GroovyTestCase {
    def instance = {-> new DecimalGenerator() }

    void testMin() {
        assertTrue instance().min(5.0).genAsDecimal() >= 5
    }

    void testMax() {
        assertTrue instance().max(5.0).genAsDecimal() <= 5
    }

    void testRange() {
        def res = instance().range(0.0, 1.0).genAsDecimal()
        assertTrue res >= 0.0 && res <= 1.0
    }

    void testDigits() {
        assertTrue instance().digits(2).gen().length() == 5
    }

    void testRoundUp() {
        // TODO: Implement
    }
}
