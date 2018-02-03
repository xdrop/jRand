package me.xdrop.jrand.generators.location

class AltitudeGeneratorTest extends GroovyTestCase {
    def instance = {-> new AltitudeGenerator()}

    void testMax() {
        assertTrue Double.parseDouble(instance().max(1000).gen()) < 1000
    }

    void testDigits() {
        println instance().max(2).digits(3).gen()
        assertTrue instance().max(2).digits(3).gen().length() == 5
    }
}
