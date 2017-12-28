package me.xdrop.jrand.generators.money

class CardGeneratorTest extends GroovyTestCase {
    def instance = {-> new CardGenerator()}

    void testNullity() {
        assertTrue instance().gen().cardType != null
        assertTrue instance().gen().country != null
        assertTrue instance().gen().name != null
        assertTrue instance().gen().cardNumber != null
        assertTrue instance().gen().billingAddress != null
        assertTrue instance().gen().country != null
        assertTrue instance().gen().issueDate != null
        assertTrue instance().gen().expiryDate != null
        assertTrue instance().gen().cvv != null
    }
    void testPrint() {
        println instance().gen()
    }
}
