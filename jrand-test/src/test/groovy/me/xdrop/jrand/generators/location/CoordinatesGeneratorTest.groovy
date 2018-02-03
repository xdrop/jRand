package me.xdrop.jrand.generators.location

class CoordinatesGeneratorTest extends GroovyTestCase {
    def instance = {-> new CoordinatesGenerator()}

    void testDecimals() {
        assertTrue instance().decimals(3).gen().split(",")[0].split("\\.")[1].length() == 3
    }
}
