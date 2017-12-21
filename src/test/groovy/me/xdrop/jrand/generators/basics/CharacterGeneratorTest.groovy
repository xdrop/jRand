package me.xdrop.jrand.generators.basics

import com.google.common.base.CharMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

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
        assertTrue CharMatcher.JAVA_DIGIT.matchesAllOf(instance().digit().genString())
    }

    void testLowerCase() {
        assertTrue CharMatcher.JAVA_LOWER_CASE.matchesAllOf(instance().alpha().casing("lower").genString())
    }

    void testDefault () {
        assertTrue CharMatcher.JAVA_LETTER_OR_DIGIT.matchesAllOf(instance().genString())
    }

    void testGenWithIndex() {
        def pool = "abcdef"
        def res = instance().pool(pool).genWithIndex()
        assertTrue res.key in pool.toCharArray() && res.val == pool.indexOf(res.key.toString())
    }

    void testSetNullPool() {
        assertTrue instance().pool(null).gen() != null
    }

    void testResetIncluded() {
        assertTrue CharMatcher.JAVA_LETTER_OR_DIGIT.matchesAllOf(instance().alpha().digit().genString())
    }

    void testGenWithIndexEmptyPool() {
        shouldFail(RuntimeException){
            instance().pool("").genWithIndex()
        }
        shouldFail (RuntimeException) {
            instance().pool("").gen()
        }
    }


}
