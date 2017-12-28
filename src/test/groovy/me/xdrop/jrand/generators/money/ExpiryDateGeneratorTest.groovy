package me.xdrop.jrand.generators.money

import org.joda.time.format.DateTimeFormat

class ExpiryDateGeneratorTest extends GroovyTestCase {
    def instance = {-> new ExpiryDateGenerator()}

    void testExpired() {
        def exp = DateTimeFormat.forPattern("mm/yy").parseDateTime(instance().expired().gen())
        assertTrue exp.isBeforeNow()
    }

    void testNonExpired() {
        def exp = DateTimeFormat.forPattern("mm/yy").parseDateTime(instance().gen())
        assertFalse  exp.isBeforeNow()
    }

    void testCanExpire() {
        def parser = DateTimeFormat.forPattern("mm/yy")
        boolean before = false
        boolean after = false
        100.times({
            def time = parser.parseDateTime(instance().canExpire(true).gen())
            if (time.isBeforeNow()) {
                before = true
            } else if (time.isAfterNow()) {
                after = true
            }
        })
        assertTrue before && after
    }

    void testLongversion() {
        assertFalse instance().gen().length() == 7
        assertTrue  instance().longVersion().gen().length() == 7
        assertTrue DateTimeFormat.forPattern("mm/yyyy").parseDateTime(instance().longVersion().gen()) != null
    }

    void testPrint(){
        println instance().gen()
        println instance().expired().gen()
        println instance().longVersion().gen()
    }
}
