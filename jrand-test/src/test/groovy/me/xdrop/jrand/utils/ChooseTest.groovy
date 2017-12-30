package me.xdrop.jrand.utils


class ChooseTest extends GroovyTestCase {

    void testChooseOne() {
        def list = ["a", "b", "c"]
        assertTrue Choose.chooseOne(list) in list
        assertTrue Choose.chooseOne(["a"]) == "a"
    }

    void testChooseOneArray() {
        String[] list = ["a", "b", "c"] as String[]
        assertTrue Choose.chooseOne(list) in list
        assertTrue Choose.chooseOne(["a"] as String[]) == "a"
    }

    void testChooseN() {
        def list = ["a", "b", "c", "d", "e"]
        def chosen = Choose.chooseN(list, 3)
        assertTrue chosen.size() == 3
        chosen.forEach({ i ->
            assertTrue i in list
        })
    }

    void testChooseNUnique() {
        def list = ["a", "b", "c", "d", "e"]
        def chosen = Choose.chooseNUnique(list, 4)
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
