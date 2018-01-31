package me.xdrop.jrand.generators.location

class CityGeneratorTest extends GroovyTestCase {
    def instance = {-> new CityGenerator()}

    void testCountry() {
        instance().country("Cyprus").gen()
    }

    void testGen() {
    }
}
