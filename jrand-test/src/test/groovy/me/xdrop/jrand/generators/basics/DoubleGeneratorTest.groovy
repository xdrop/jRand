package me.xdrop.jrand.generators.basics

import me.xdrop.jrand.JRand

class DoubleGeneratorTest extends GroovyTestCase {
    def instance = {-> JRand.decimal() }

    void testMax() {
        assertTrue Float.parseFloat(instance().max(2.0 as double).gen()) <= 2
    }

    void testMin() {
        assertTrue Float.parseFloat(instance().min(5.0 as double).gen()) >= 5
    }

    void testRange() {
        def res = Float.parseFloat(instance().range(0.0, 1.0).gen())
        assertTrue res <= 1 && res >= 0
    }
}
