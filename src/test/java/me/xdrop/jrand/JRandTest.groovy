package me.xdrop.jrand

import me.xdrop.jrand.builders.basics.BoolGenerator
import org.codehaus.groovy.runtime.MethodClosure

class JRandTest extends groovy.util.GroovyTestCase {

    void testBool() {
        def instance = JRand.bool()
        assertTrue instance.likelihood(100).generate()
        assertFalse instance.likelihood(0).generate()
        likelihoodTest(instance.&likelihood)
        def fun = instance.&likelihood
    }




    boolean likelihoodTest(MethodClosure func) {
        def likelihood = new Random().nextInt(100)
        def generator = func(likelihood)



        def bucketYes = 0
        def bucketNo = 0

        int iterations = 1000

        for (int i = 0; i<= iterations; i++){
            if(generator.generate()){
                bucketYes++
            } else {
                bucketNo++;
            }
        }

        def actual = (bucketYes / iterations) * 100
        println("Likelihood actual": actual);
        println("Likelihood expected": likelihood);
        assertTrue ((likelihood - 3) <= actual && actual <=(likelihood + 3))



    }
}
