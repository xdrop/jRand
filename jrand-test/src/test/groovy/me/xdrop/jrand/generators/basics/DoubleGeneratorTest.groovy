package me.xdrop.jrand.generators.basics

class DoubleGeneratorTest extends GroovyTestCase {
    def instance = {-> new DoubleGenerator()}

    void testMax() {
        assertTrue instance().max(2.0 as double).gen() <= 2
    }

    void testMin() {
        assertTrue instance().min(5.0 as double).gen() >= 5
    }

    void testRange() {
        def res = instance().range(0, 1).gen()
        assertTrue res <= 1 && res >= 0
    }
}
