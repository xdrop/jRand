package me.xdrop.jrand.generators.person

import me.xdrop.jrand.data.AssetLoader

class LastnameGeneratorTest extends GroovyTestCase {
    def instance = {-> new LastnameGenerator()}
    def names = AssetLoader.loadList("neutral/surnames.txt")

    void testGen() {
        assertTrue instance().gen() in names
    }

    void testPrint(){
        println instance().gen()
    }
}
