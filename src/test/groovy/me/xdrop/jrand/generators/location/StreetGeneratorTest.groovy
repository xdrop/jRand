package me.xdrop.jrand.generators.location

import me.xdrop.jrand.data.AssetLoader

import java.util.stream.Collectors

class StreetGeneratorTest extends GroovyTestCase {
    def instance = {-> new StreetGenerator()}

    void testShortSuffix() {
        List<StreetSuffix> all = AssetLoader.loadListMapped("uk/street_suffixes.txt", new StreetSuffixMapper())
        all.addAll(AssetLoader.loadListMapped("us/street_suffixes.txt", new StreetSuffixMapper()))

        def street = instance().shortSuffix().gen()
        assertTrue street.split(" ")[1] in all.stream().map({s -> s.shortVersion}).collect(Collectors.toList())
    }

    void testLongSuffix() {
        List<StreetSuffix> all = AssetLoader.loadListMapped("uk/street_suffixes.txt", new StreetSuffixMapper())
        all.addAll(AssetLoader.loadListMapped("us/street_suffixes.txt", new StreetSuffixMapper()))

        def street = instance().gen()
        assertTrue street.split(" ")[1] in all.stream().map({s -> s.longVersion}).collect(Collectors.toList())
    }


    void testUK() {
        List<StreetSuffix> uk = AssetLoader.loadListMapped("uk/street_suffixes.txt", new StreetSuffixMapper())

        def street = instance().gen()
        assertTrue street.split(" ")[1] in uk.stream().map({s -> s.longVersion}).collect(Collectors.toList())
    }

    void testUS(){
        List<StreetSuffix> us = AssetLoader.loadListMapped("uk/street_suffixes.txt", new StreetSuffixMapper())

        def street = instance().gen()
        assertTrue street.split(" ")[1] in us.stream().map({s -> s.longVersion}).collect(Collectors.toList())
    }

    void testHouseNumber() {
        def street = instance().houseNumber().gen()
        assertTrue street.split(" ")[0].isNumber()
    }

}
