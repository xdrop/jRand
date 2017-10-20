package me.xdrop.jrand

import me.xdrop.jrand.generators.basics.BoolGenerator
import me.xdrop.jrand.generators.basics.CharacterGenerator
import me.xdrop.jrand.generators.basics.DecimalGenerator
import me.xdrop.jrand.generators.basics.DoubleGenerator
import me.xdrop.jrand.generators.basics.FloatGenerator
import me.xdrop.jrand.generators.basics.NaturalGenerator
import me.xdrop.jrand.generators.basics.StringGenerator
import org.codehaus.groovy.runtime.MethodClosure

class JRandTest extends GroovyTestCase {

    void testFacade() {
        assertTrue JRand.character() instanceof CharacterGenerator
        assertTrue JRand.flt() instanceof FloatGenerator
        assertTrue JRand.dbl() instanceof DoubleGenerator
        assertTrue JRand.bool() instanceof BoolGenerator
        assertTrue JRand.decimal() instanceof DecimalGenerator
        assertTrue JRand.string() instanceof StringGenerator
        assertTrue JRand.natural() instanceof NaturalGenerator
    }

   static boolean likelihoodTest(MethodClosure func) {
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
