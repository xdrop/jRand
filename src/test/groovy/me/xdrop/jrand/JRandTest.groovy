package me.xdrop.jrand

import com.google.common.base.CharMatcher
import me.xdrop.jrand.builders.basics.enums.CHARSET
import org.codehaus.groovy.runtime.MethodClosure

class JRandTest extends GroovyTestCase {

    void testBool() {
        def instance = JRand.bool()
        assertTrue instance.likelihood(100).gen()
        assertFalse instance.likelihood(0).gen()
        likelihoodTest(instance.&likelihood)
    }

    void testChar() {
        assertTrue CharMatcher.JAVA_UPPER_CASE.matchesAllOf(JRand.character().alpha().casing("upper").genString())
        assertTrue CharMatcher.JAVA_LOWER_CASE.matchesAllOf(JRand.character().alpha().casing("lower").genString())
        assertTrue CharMatcher.JAVA_LETTER.matchesAllOf(JRand.character().alpha().genString())
        assertTrue CharMatcher.JAVA_LETTER_OR_DIGIT.matchesAllOf(JRand.character().genString())
        assertFalse CharMatcher.JAVA_LETTER_OR_DIGIT.matchesAllOf(JRand.character().symbols().genString())
    }

    void testDouble() {
        assertTrue JRand.dbl().max(2.0).gen() <= 2
        assertTrue JRand.dbl().min(5.0).gen() >= 5
    }

    void testFloat() {
        assertTrue JRand.flt().max(2.0).gen() <=2
        assertTrue JRand.flt().min(5.0).gen() >=5
    }

    void testDecimal() {
        println JRand.decimal().min(4.0).min(1.0).digits(2.0).gen()
        assertTrue JRand.decimal().max(4.0).min(1.0).digits(2.0).gen().length() == 4
    }

    boolean likelihoodTest(MethodClosure func) {
        def likelihood = new Random().nextInt(100)
        def generator = func(likelihood)

        def bucketYes = 0
        def bucketNo = 0

        int iterations = 1000

        for (int i = 0; i <= iterations; i++) {
            if (generator.gen()) {
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
