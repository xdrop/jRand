package me.xdrop.jrand.generators.person

import me.xdrop.jrand.JRand
import me.xdrop.jrand.model.person.PersonType
import org.joda.time.DateTime
import org.joda.time.Period

class BirthdayGeneratorTest extends GroovyTestCase {
    def instance = { -> JRand.birthday() }

    void testType() {
        DateTime date = instance().type(PersonType.CHILD).getDateTime()
        def years = new Period(date, new DateTime()).years
        assertTrue years <= PersonType.CHILD.max && years >= PersonType.CHILD.min
    }

    void testAmerican() {
        assertTrue Integer.parseInt(instance().american(false).genString().split("/")[1]) <= 12
        assertTrue Integer.parseInt(instance().american(true).genString().split("/")[0]) <= 12
        assertTrue Integer.parseInt(instance().american().genString().split("/")[0]) <= 12
    }

    void testChild() {
        DateTime date = instance().child().getDateTime()
        def years = new Period(date, new DateTime()).years
        assertTrue years <= PersonType.CHILD.max && years >= PersonType.CHILD.min
    }

    void testAdult() {
        DateTime date = instance().adult().getDateTime()
        def years = new Period(date, new DateTime()).years
        assertTrue years <= PersonType.ADULT.max && years >= PersonType.ADULT.min
    }

    void testTeen() {
        DateTime date = instance().teen().getDateTime()
        def years = new Period(date, new DateTime()).years
        assertTrue years <= PersonType.TEEN.max && years >= PersonType.TEEN.min
    }

    void testSenior() {
        DateTime date = instance().senior().getDateTime()
        def years = new Period(date, new DateTime()).years
        assertTrue years <= PersonType.SENIOR.max && years >= PersonType.SENIOR.min
    }

    void testFormat() {
        assertTrue instance().format("dd").genString().length() == 2
    }

    void testDate() {
        assertTrue instance().gen() instanceof Date
        assertTrue instance().gen() != new Date()
    }


}
