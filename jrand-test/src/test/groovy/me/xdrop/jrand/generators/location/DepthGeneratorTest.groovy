package me.xdrop.jrand.generators.location

class DepthGeneratorTest extends GroovyTestCase {
    def instance = {-> new DepthGenerator()}

    void testDepth() {
        def gen = Double.parseDouble(instance().gen())
        assertTrue gen < 0 && gen >= -10994.0
    }
}
