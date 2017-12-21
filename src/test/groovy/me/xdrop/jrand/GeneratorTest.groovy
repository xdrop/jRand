package me.xdrop.jrand


class GeneratorTest extends GroovyTestCase {
    void testGenManyUnique() {
        def chosen = JRand.natural().range(0,100).genManyUnique(50)
        def seen = new HashSet()
        assertTrue chosen.size() == 50
        chosen.forEach({ i ->
            assertTrue !(i in seen)
        })
    }

    void testGenManyAsSet() {
        assertTrue JRand.natural().genManyAsSet(50) instanceof Set
        assertTrue JRand.natural().genManyAsSet(50).size() > 0
    }
}
