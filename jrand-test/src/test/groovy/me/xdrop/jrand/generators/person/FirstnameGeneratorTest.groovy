package me.xdrop.jrand.generators.person

import me.xdrop.jrand.JRand
import me.xdrop.jrand.data.AssetLoader
import me.xdrop.jrand.data.Assets
import me.xdrop.jrand.model.person.Gender

class FirstnameGeneratorTest extends GroovyTestCase {
    def instance = { -> JRand.firstname() }
    def names = Assets.MALE_FIRSTNAMES.loadItems()
    def namesF = Assets.FEMALE_FIRSTNAMES.loadItems()

    void testGen() {
        def name = instance().gen()
        assertTrue((name in namesF) || (name in names))
    }

    void testGender() {
        assertTrue instance().gender(Gender.FEMALE).gen() in namesF
        assertTrue instance().gender(Gender.MALE).gen() in names
        assertTrue instance().gender("male").gen() in names
        assertTrue instance().gender("m").gen() in names
        assertTrue instance().gender("female").gen() in namesF
        assertTrue instance().gender("f").gen() in namesF
    }

    void testPrint() {
        println instance().gen()
    }
}
