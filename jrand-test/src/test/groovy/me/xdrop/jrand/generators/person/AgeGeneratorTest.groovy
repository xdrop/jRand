package me.xdrop.jrand.generators.person

import me.xdrop.jrand.model.person.PersonType


class AgeGeneratorTest extends GroovyTestCase {
    def instance = {-> new AgeGenerator()}

    void testChild() {
        def years = instance().child().gen()
        assertTrue years <= PersonType.CHILD.max && years >= PersonType.CHILD.min
    }

    void testAdult() {
        def years = instance().adult().gen()
        assertTrue years <= PersonType.ADULT.max && years >= PersonType.ADULT.min
    }

    void testTeen() {
        def years = instance().teen().gen()
        assertTrue years <= PersonType.TEEN.max && years >= PersonType.TEEN.min
    }

    void testSenior() {
        def years = instance().senior().gen()
        assertTrue years <= PersonType.SENIOR.max && years >= PersonType.SENIOR.min
    }

    void testPersonType() {
        def years = instance().personType(PersonType.CHILD).gen()
        assertTrue years <= PersonType.CHILD.max && years >= PersonType.CHILD.min
    }
}
