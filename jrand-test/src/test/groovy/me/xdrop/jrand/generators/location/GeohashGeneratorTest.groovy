package me.xdrop.jrand.generators.location

import com.google.common.base.CharMatcher

class GeohashGeneratorTest extends GroovyTestCase {
    def instance = {-> new GeohashGenerator()}

    void testLength() {
        assertTrue instance().length(2).gen().length() == 2
    }

    void testCorrectPool() {
        assertTrue CharMatcher.anyOf("0123456789bcdefghjkmnpqrstuvwxyz").matchesAllOf(instance().gen())
    }
}
