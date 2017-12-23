package me.xdrop.jrand.generators.money

class CardTypeGeneratorTest extends GroovyTestCase {
    def instance = {-> new CardTypeGenerator()}

    void testGetTypeByName() {
        assertTrue CardTypeGenerator.getTypeByName("amex") == CardType.AMERICAN_EXPRESS
        assertTrue CardTypeGenerator.getTypeByName("visa") == CardType.VISA
        assertTrue CardTypeGenerator.getTypeByName("electron") == CardType.VISA_ELECTRON
        assertTrue CardTypeGenerator.getTypeByName("mastercard") == CardType.MASTERCARD
        assertTrue CardTypeGenerator.getTypeByName("cup") == CardType.CHINA_UNIONPAY
        assertTrue CardTypeGenerator.getTypeByName("maestro") == CardType.MAESTRO
        assertTrue CardTypeGenerator.getTypeByName("discover") == CardType.DISCOVER
        assertTrue CardTypeGenerator.getTypeByName("dc-cb") == CardType.DINERS_CLUB_CARTE_BLANCHE
        assertTrue CardTypeGenerator.getTypeByName("dc-int") == CardType.DINERS_CLUB_INTL
        assertTrue CardTypeGenerator.getTypeByName("dc-uc") == CardType.DINERS_CLUB_US_CAN
        assertTrue CardTypeGenerator.getTypeByName("jcb") == CardType.JCB
        assertTrue CardTypeGenerator.getTypeByName("instapayment") == CardType.INSTAPAYMENT
        assertTrue CardTypeGenerator.getTypeByName("laser") == CardType.LASER
        assertTrue CardTypeGenerator.getTypeByName("solo") == CardType.SOLO
        assertTrue CardTypeGenerator.getTypeByName("switch") == CardType.SWITCH
    }

    void testAllTypes() {
        assertTrue CardTypeGenerator.allTypes().length > 0
    }

    void testDefaultFormat() {
        assertTrue CardTypeGenerator.defaultFormat(16) == "XXXX XXXX XXXX XXXX"
        assertTrue CardTypeGenerator.defaultFormat(15) == "XXXX XXXXXX XXXXX"
    }

    void testCommon() {
        def common = [CardType.AMERICAN_EXPRESS, CardType.DISCOVER, CardType.VISA, CardType.MASTERCARD]
        assertTrue instance().common().gen() in common
    }

    void testOnly() {
        def onlyVisa = [CardType.VISA, CardType.VISA_ELECTRON]
        assertTrue instance().only("visa", "electron").gen() in onlyVisa
    }

    void testOnly1() {
        def onlyVisa = [CardType.VISA, CardType.VISA_ELECTRON]
        assertTrue instance().only(CardType.VISA_ELECTRON, CardType.VISA).gen() in onlyVisa
    }
}
