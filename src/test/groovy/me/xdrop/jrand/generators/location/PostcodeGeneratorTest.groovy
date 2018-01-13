package me.xdrop.jrand.generators.location

import com.google.common.base.CharMatcher

class PostcodeGeneratorTest extends GroovyTestCase {
    def instance = {-> new PostcodeGenerator()}

    void testCountry() {
        def postcode = instance().country("cy").gen()
        assertTrue  postcode.length() == 4
        assertTrue CharMatcher.javaDigit().matchesAllOf(postcode)
        def ukPostcode = instance().country("gb").gen()
        assertTrue CharMatcher.javaLetterOrDigit().matchesAllOf(ukPostcode.replace(" ", ""))
        assertTrue ukPostcode.toUpperCase() == ukPostcode
        assertTrue CharMatcher.javaLetter().matchesAnyOf(ukPostcode)
    }

    void testDefault() {
        def postcode = instance().gen()
        assertTrue postcode.length() > 3
    }

    void testFixedPostcode() {
        def fixed = instance().country("FK").gen()
        assertTrue  fixed == "FIQQ 1ZZ"
    }

}
