package me.xdrop.jrand.generators.location

import me.xdrop.jrand.data.AssetLoader
import me.xdrop.jrand.data.Assets
import me.xdrop.jrand.model.location.CountryMapper

class CountryGeneratorTest extends GroovyTestCase {
    def instance = {-> new CountryGenerator()}
    def countries = Assets.COUNTRIES.loadItems()

    void testCountry() {
        def country = instance().gen()
        assertTrue countries.stream().any {c -> c.name == country}
    }

    void testPrefix() {
        def country = instance().prefix().gen()
        assertTrue countries.stream().any {c -> c.prefix == country}
    }
}
