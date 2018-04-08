package me.xdrop.jrand.generators.person

import me.xdrop.jrand.JRand
import me.xdrop.jrand.model.person.Gender


class GenderGeneratorTest extends GroovyTestCase {
    def instance = { -> JRand.gender() }

    void testFormat() {
        assertTrue instance().format("L", "X").gen() in ["L", "X"]
    }

    void testFull() {
        assertTrue instance().full().gen() in ["Male", "Female"]
    }

    void testLikelihood() {
        assertTrue instance().likelihood(0).gen() == "F"
    }

    void testAsGender() {
        assertTrue instance().likelihood(0).genAsGender() == Gender.FEMALE
    }
}
