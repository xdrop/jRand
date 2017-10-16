package me.xdrop.jrand.builders.basics

class NaturalGeneratorTest extends GroovyTestCase {
    def instance = {-> new NaturalGenerator()}

    void testMin() {
        assertTrue instance().min(50).gen() >= 50
    }

    void testMax() {
        assertTrue instance().max(300).gen() <= 300
    }

    void testRange() {
        assertTrue instance().range(3,3).gen() == 3
        assertTrue instance().min(3).max(3).gen() == 3
        assertTrue instance().range(5,7).gen() >= 5
        assertTrue instance().range(5,7).gen() <= 7
    }
}
