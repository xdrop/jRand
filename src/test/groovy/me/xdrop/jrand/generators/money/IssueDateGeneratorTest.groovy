package me.xdrop.jrand.generators.money

import org.joda.time.format.DateTimeFormat

class IssueDateGeneratorTest extends GroovyTestCase {
    def instance = { -> new IssueDateGenerator() }

    void testIsBeforeNow() {
        def exp = DateTimeFormat.forPattern("mm/yy").parseDateTime(instance().gen())
        assertTrue exp.isBeforeNow()
    }

    void testLongVersion() {
        assertFalse instance().gen().length() == 7
        assertTrue instance().longVersion().gen().length() == 7
        assertTrue DateTimeFormat.forPattern("mm/yyyy").parseDateTime(instance().longVersion().gen()) != null
    }

    void testPrint() {
        println instance().gen()
        println instance().longVersion().gen()
    }
}
