package me.xdrop.jrand.generators.location

import me.xdrop.jrand.data.AssetLoader

class CountryGeneratorTest extends GroovyTestCase {
    def instance = {-> new CountryGenerator()}
    def countries = AssetLoader.loadList("countries.txt", new CountryMapper())

    void testCountry() {
        def country = instance().gen()
        assertTrue countries.stream().any {c -> c.name == country}
    }

    void testPrefix() {
        def country = instance().prefix().gen()
        assertTrue countries.stream().any {c -> c.prefix == country}
    }
}
