package me.xdrop.jrand.utils


class ChooseTest extends GroovyTestCase {

    void testChooseOne() {
        def list = ["a", "b", "c"]
        assertTrue Choose.one(list) in list
        assertTrue Choose.one(["a"]) == "a"
    }

    void testChooseOneArray() {
        String[] list = ["a", "b", "c"] as String[]
        double[] dbl = [1.0, 2.0, 3.0] as double[]
        int[] ints = [1,2,3] as int[]
        assertTrue Choose.one(list) in list
        assertTrue Choose.one(["a"] as String[]) == "a"
        assertTrue Choose.one(dbl) in dbl
        assertTrue Choose.one(ints) in ints
    }

    void testChooseN() {
        def list = ["a", "b", "c", "d", "e"]
        def chosen = Choose.N(list, 3)
        assertTrue chosen.size() == 3
        chosen.forEach({ i ->
            assertTrue i in list
        })
    }


    void testChooseNUnique() {
        def list = ["a", "b", "c", "d", "e"]
        def chosen = Choose.NUnique(list, 4)
        def seen= new HashSet()
        assertTrue chosen.size() == 4
        chosen.forEach({ i ->
            assertTrue i in list && !(i in seen)
            seen.add(i)
        })
    }

    void testShuffle() {
        shouldFail (UnsupportedOperationException) {
            Choose.shuffle()
        }
    }

    void testShuffleInplace() {
        shouldFail (UnsupportedOperationException) {
            Choose.shuffleInplace()
        }
    }
}
