package me.xdrop.jrand.generators.collections

class ListRandUtilsTest extends GroovyTestCase {

    void testChooseOne() {
        def list = ["a", "b", "c"]
        assertTrue ListRandUtils.chooseOne(list) in list
        assertTrue ListRandUtils.chooseOne(["a"]) == "a"
    }

    void testChooseOneArray() {
        String[] list = ["a", "b", "c"] as String[]
        assertTrue ListRandUtils.chooseOne(list) in list
        assertTrue ListRandUtils.chooseOne(["a"] as String[]) == "a"
    }

    void testChooseN() {
        def list = ["a", "b", "c", "d", "e"]
        def chosen = ListRandUtils.chooseN(list, 3)
        assertTrue chosen.size() == 3
        chosen.forEach({ i ->
            assertTrue i in list
        })
    }

    void testChooseNUnique() {
        def list = ["a", "b", "c", "d", "e"]
        def chosen = ListRandUtils.chooseNUnique(list, 4)
        def seen= new HashSet()
        assertTrue chosen.size() == 4
        chosen.forEach({ i ->
            assertTrue i in list && !(i in seen)
            seen.add(i)
        })
    }

    void testShuffle() {
        shouldFail (UnsupportedOperationException) {
            ListRandUtils.shuffle()
        }
    }

    void testShuffleInplace() {
        shouldFail (UnsupportedOperationException) {
            ListRandUtils.shuffleInplace()
        }
    }
}
