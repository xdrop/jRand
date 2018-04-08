package me.xdrop.jrand.generators.location

import me.xdrop.jrand.JRand

class LatitudeGeneratorTest extends GroovyTestCase {
    def instance = { -> JRand.latitude() }

    void testDecimals() {
        assertTrue instance().decimals(3).gen().split("\\.")[1].length() == 3
    }

    void testRange() {
        def gen = Double.parseDouble(instance().range(10, 20).gen())
        assertTrue gen <= 20 && gen >= 10
    }
}
