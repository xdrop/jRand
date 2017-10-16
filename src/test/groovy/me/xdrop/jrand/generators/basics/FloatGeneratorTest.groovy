package me.xdrop.jrand.generators.basics

class FloatGeneratorTest extends GroovyTestCase {
    def instance = {-> new FloatGenerator()}

    void testMax() {
        assertTrue instance().max(2.0).gen() <= 2
    }

    void testMin() {
        assertTrue instance().min(5.0).gen() >= 5
    }

    void testRange() {
        def res = instance().range(0,1).gen()
        assertTrue res >= 0 && res <= 1
    }
}
