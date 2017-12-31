package me.xdrop.jrand.generators.person

import me.xdrop.jrand.data.AssetLoader
import me.xdrop.jrand.model.person.Gender

class FirstnameGeneratorTest extends GroovyTestCase {
    def instance = { -> new FirstnameGenerator() }
    def names = AssetLoader.loadList("firstnames-male")
    def namesF = AssetLoader.loadList("firstnames-female")

    void testGen() {
        def name = instance().gen()
        assertTrue ((name in namesF) || (name in names))
    }

    void testGender() {
        assertTrue instance().gender(Gender.FEMALE).gen() in namesF
        assertTrue instance().gender(Gender.MALE).gen() in names
        assertTrue instance().gender("male").gen() in names
        assertTrue instance().gender("m").gen() in names
        assertTrue instance().gender("female").gen() in names
        assertTrue instance().gender("f").gen() in names
    }

    void testPrint() {
        println instance().gen()
    }
}
