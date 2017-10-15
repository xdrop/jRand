package me.xdrop.jrand.builders.basics

import com.google.common.base.CharMatcher

class CharacterGeneratorTest extends GroovyTestCase {
    def instance = { -> return new CharacterGenerator() }

    void testPool() {
        def pool = "qwz";
        def cpool = pool.toCharArray();
        def cgen = instance().pool(pool)
        for (def i = 0; i < 10; i++) {
            assertTrue cgen.gen() in cpool
        }
    }

    void testSymbols() {
        assertFalse CharMatcher.JAVA_LETTER_OR_DIGIT.matchesAllOf(instance().symbols().genString())
    }

    void testAlpha() {
        assertTrue CharMatcher.JAVA_LETTER.matchesAllOf(instance().alpha().genString())
    }

    void testUpperCase() {
        assertTrue CharMatcher.JAVA_UPPER_CASE.matchesAllOf(instance().alpha().casing("upper").genString())
    }

    void testNumber() {
        assertTrue CharMatcher.JAVA_DIGIT.matchesAllOf(instance().number().genString())
    }

    void testLowerCase() {
        assertTrue CharMatcher.JAVA_LOWER_CASE.matchesAllOf(instance().alpha().casing("lower").genString())
    }

    void testDefault () {
        assertTrue CharMatcher.JAVA_LETTER_OR_DIGIT.matchesAllOf(instance().genString())
    }


}
