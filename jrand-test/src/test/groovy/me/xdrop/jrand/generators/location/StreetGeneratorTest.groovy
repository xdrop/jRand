package me.xdrop.jrand.generators.location

import me.xdrop.jrand.data.AssetLoader
import me.xdrop.jrand.model.location.StreetSuffix
import me.xdrop.jrand.model.location.StreetSuffixMapper

import java.util.stream.Collectors

class StreetGeneratorTest extends GroovyTestCase {
    def instance = {-> new StreetGenerator()}

    void testShortSuffix() {
        List<StreetSuffix> all = AssetLoader.loadList("uk/street_suffixes.txt", new StreetSuffixMapper())
        all.addAll(AssetLoader.loadList("us/street_suffixes.txt", new StreetSuffixMapper()))

        def street = instance().shortSuffix().gen()
        assertTrue street.split(" ")[1] in all.stream().map({s -> s.shortVersion}).collect(Collectors.toList())
    }

    void testLongSuffix() {
        List<StreetSuffix> all = AssetLoader.loadList("uk/street_suffixes.txt", new StreetSuffixMapper())
        all.addAll(AssetLoader.loadList("us/street_suffixes.txt", new StreetSuffixMapper()))

        def street = instance().gen()
        assertTrue street.split(" ")[1] in all.stream().map({s -> s.longVersion}).collect(Collectors.toList())
    }


    void testUK() {
        List<StreetSuffix> uk = AssetLoader.loadList("uk/street_suffixes.txt", new StreetSuffixMapper())

        def street = instance().uk().gen()
        assertTrue street.split(" ")[1] in uk.stream().map({s -> s.longVersion}).collect(Collectors.toList())
    }

    void testUS(){
        List<StreetSuffix> us = AssetLoader.loadList("us/street_suffixes.txt", new StreetSuffixMapper())

        def street = instance().us().gen()
        assertTrue street.split(" ")[1] in us.stream().map({s -> s.longVersion}).collect(Collectors.toList())
    }

    void testHouseNumber() {
        def street = instance().houseNumber().gen()
        assertTrue street.split(" ")[0].isNumber()
    }

}
