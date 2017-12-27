package me.xdrop.jrand.generators.money

class ExpiryDateGeneratorTest extends GroovyTestCase {
    def instance = {-> new ExpiryDateGenerator()}

    void test(){
        println instance().gen()
        println instance().expired().gen()
        println instance().longVersion().gen()
    }
}
