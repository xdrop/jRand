package me.xdrop.jrand.generators.location

import me.xdrop.jrand.JRand

class CoordinatesGeneratorTest extends GroovyTestCase {
    def instance = { -> JRand.coordinates() }

    void testDecimals() {
        assertTrue instance().decimals(3).gen().split(",")[0].split("\\.")[1].length() == 3
    }
}
