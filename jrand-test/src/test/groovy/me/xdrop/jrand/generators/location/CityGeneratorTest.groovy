package me.xdrop.jrand.generators.location

import me.xdrop.jrand.JRand

class CityGeneratorTest extends GroovyTestCase {
    def instance = { -> JRand.city() }

    void testCountry() {
        instance().country("Cyprus").gen()
    }

    void testGen() {
    }
}
