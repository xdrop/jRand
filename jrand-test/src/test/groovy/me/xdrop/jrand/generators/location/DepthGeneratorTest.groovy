package me.xdrop.jrand.generators.location

import me.xdrop.jrand.JRand

class DepthGeneratorTest extends GroovyTestCase {
    def instance = { -> JRand.depth() }

    void testDepth() {
        def gen = Double.parseDouble(instance().gen())
        assertTrue gen < 0 && gen >= -10994.0
    }
}
