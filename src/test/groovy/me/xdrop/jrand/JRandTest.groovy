package me.xdrop.jrand

import com.google.common.base.CharMatcher
import me.xdrop.jrand.builders.basics.enums.CHARSET
import org.codehaus.groovy.runtime.MethodClosure

class JRandTest extends GroovyTestCase {

    void testBool() {
        def instance = JRand.bool()
        assertTrue instance.likelihood(100).rand()
        assertFalse instance.likelihood(0).rand()
        likelihoodTest(instance.&likelihood)
    }

    void testChar() {
        assertTrue CharMatcher.JAVA_UPPER_CASE.matchesAllOf(JRand.character().include(CHARSET.CHARS_UPPER).randString())
        assertTrue CharMatcher.JAVA_LOWER_CASE.matchesAllOf(JRand.character().include(CHARSET.CHARS_LOWER).randString())
        assertTrue CharMatcher.JAVA_LETTER.matchesAllOf(JRand.character().include(CHARSET.CHARS_UPPER, CHARSET.CHARS_LOWER).randString())
        assertTrue CharMatcher.JAVA_LETTER_OR_DIGIT.matchesAllOf(JRand.character().include(CHARSET.CHARS_LOWER, CHARSET.CHARS_UPPER, CHARSET.NUMBERS).randString())

        println JRand.character().include(CHARSET.CHARS_UPPER, CHARSET.NUMBERS).genMany(17).toString()
    }

    void testDouble() {
        assertTrue JRand.dbl().max(2) <= 2
        assertTrue JRand.dbl().min(5) >= 5
    }

    void testFloat() {
        assertTrue JRand.flt().max(2) <= 2
        assertTrue JRand.flt().min(5) >= 5
    }

    boolean likelihoodTest(MethodClosure func) {
        def likelihood = new Random().nextInt(100)
        def generator = func(likelihood)

        def bucketYes = 0
        def bucketNo = 0

        int iterations = 1000

        for (int i = 0; i <= iterations; i++) {
            if (generator.generate()) {
                bucketYes++
            } else {
                bucketNo++;
            }
        }

        def actual = (bucketYes / iterations) * 100
        println("Likelihood actual": actual);
        println("Likelihood expected": likelihood);
        assertTrue((likelihood - 5) <= actual && actual <= (likelihood + 5))

    }
}
