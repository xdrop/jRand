package me.xdrop.jrand

import com.google.common.base.CharMatcher
import me.xdrop.jrand.builders.basics.CharacterGenerator
import org.codehaus.groovy.runtime.MethodClosure

class JRandTest extends GroovyTestCase {

    void testBool() {
        def instance = JRand.bool()
        assertTrue instance.likelihood(100).generate()
        assertFalse instance.likelihood(0).generate()
        likelihoodTest(instance.&likelihood)
    }

    void testChar() {
        assertTrue CharMatcher.JAVA_UPPER_CASE.matchesAllOf(JRand.character().generate().toString())
        assertTrue CharMatcher.JAVA_LOWER_CASE.matchesAllOf(JRand.character().casing("lower").alpha().generate().toString())
        assertTrue CharMatcher.JAVA_LETTER.matchesAllOf(JRand.character().alpha().generate().toString())
        assertTrue CharMatcher.JAVA_LETTER_OR_DIGIT.matchesAllOf(JRand.character().generate().toString())
        assertTrue CharMatcher.JAVA_DIGIT.matchesAllOf(JRand.character().number().generate().toString())
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
        assertTrue((likelihood - 3) <= actual && actual <= (likelihood + 3))

    }
}
