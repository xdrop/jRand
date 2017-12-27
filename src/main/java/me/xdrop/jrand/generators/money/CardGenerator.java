package me.xdrop.jrand.generators.money;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.generators.location.StreetGenerator;
import me.xdrop.jrand.generators.person.NameGenerator;

public class CardGenerator extends Generator<Card> {
    private StreetGenerator street;
    private NameGenerator name;
    private CardNumberGenerator card;
    private CVVGenerator cvv;
    private ExpiryDateGenerator expiry;
    private IssueDateGenerator issue;
    private CountryGenerator country;

    public CardGenerator() {
        this.street = new StreetGenerator().houseNumber();
        this.name = new NameGenerator();
        this.card = new CardNumberGenerator();
        this.cvv = new CVVGenerator();
        this.expiry = new ExpiryDateGenerator();
        this.issue = new IssueDateGenerator();
        this.country = new CountryGenerator();
    }

    @Override
    public Card gen() {
        Card card = new Card();
        card.setBillingAddress(street.gen());
        card.setCardNumber(this.card.gen());
        card.setCcv(cvv.gen());
        card.setCountry(country.gen());
        card.setExpiryDate(expiry.gen());
        card.setIssueDate(issue.gen());
        card.setName(name.gen());
        return new Card();
    }
}
