package me.xdrop.jrand.generators.money

import java.util.regex.Matcher

class CardNumberGeneratorTest extends GroovyTestCase {
    def instance = {-> new CardNumberGenerator()}

    void testCardType() {
        assertTrue instance().cardType("visa").gen().startsWith("4")
        assertTrue instance().cardType("visa").gen().length() == 16
    }

    void testCardType1() {
        assertTrue instance().cardType(CardType.VISA).gen().startsWith("4")
        assertTrue instance().cardType(CardType.VISA).gen().length() == 16
    }

    void testFormatCustom() {
        def num = instance().cardType("visa").format("XXXX XXXX XXXX XXXX").gen()
        Matcher visaDefault = num =~ /4\d{3} \d{4} \d{4} \d{4}/
        assertTrue visaDefault.matches()
    }

    void testFormatDefault() {
        def num = instance().cardType("visa").format().gen()
        Matcher visaDefault = num =~ /4\d{3} \d{4} \d{4} \d{4}/
        assertTrue visaDefault.matches()
        def num2 = instance().cardType("amex").format().gen()
        Matcher amexDefault = num2 =~ /\d{4} \d{6} \d{5}/
        assertTrue amexDefault.matches()
    }

    void testOnly() {
        def num = instance().only("amex").gen()
        assertTrue num.length() == 15
        def num2 = instance().only(CardType.AMERICAN_EXPRESS).gen()
        assertTrue num2.length() == 15
    }

    void testCommon(){
        def num = instance().common().gen()
        assertTrue num.length() >= 15 && num.length() <= 16
    }

    void testLuhnCalculate() {
        assertTrue CardNumberGenerator.luhnCalculate("630403851107382") == 7
    }

    void testPrint() {
        println instance().gen()
        println instance().format("XXXX XXXX XXXX XXXX").gen()
        println instance().common().gen()
        println instance().cardType("amex").gen()
    }

}
