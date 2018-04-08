package me.xdrop.jrand.generators.basics

import me.xdrop.jrand.JRand

class NaturalGeneratorTest extends GroovyTestCase {
    def instance = {-> JRand.natural() }

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

    void testSample() {
        def res = instance().sample(50, 100)
        assertTrue res.size() == 50
        for (int i : res) {
            assertTrue i >= 0 && i < 100
        }
    }
}
