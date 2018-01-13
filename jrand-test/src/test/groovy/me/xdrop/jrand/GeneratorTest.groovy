package me.xdrop.jrand

import me.xdrop.jrand.generators.basics.NaturalGenerator


class GeneratorTest extends GroovyTestCase {
    void testGenManyUnique() {
        def chosen = new NaturalGenerator().range(0,100).genManyUnique(50)
        def seen = new HashSet()
        assertTrue chosen.size() == 50
        chosen.forEach({ i ->
            assertTrue !(i in seen)
        })
    }

    void testGenManyAsSet() {
        assertTrue new NaturalGenerator().genManyAsSet(50) instanceof Set
        assertTrue new NaturalGenerator().genManyAsSet(50).size() > 0
    }
}
