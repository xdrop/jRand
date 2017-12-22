package me.xdrop.jrand.random

class RandTest extends GroovyTestCase {
    // Eventually write uniformness test for these

    def rand = new Rand()
    void testRandFloat() {
       assertTrue rand.randFloat() instanceof Float
    }

    void testRandDouble() {
        assertTrue rand.randDouble() instanceof Double
    }

    void testRandInt() {
        assertTrue rand.randInt() instanceof Integer
    }

    void testRandLong() {
        assertTrue rand.randLong() instanceof Long
    }

    void testRandBool() {
        assertTrue rand.randBool() instanceof Boolean
    }

    void testRandInt1() {
        assertTrue rand.randInt(50) instanceof Integer && rand.randInt(50) < 50
    }
}
