package me.xdrop.jrand.generators.money;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.model.location.Country;
import me.xdrop.jrand.generators.location.CountryGenerator;
import me.xdrop.jrand.generators.location.PostcodeGenerator;
import me.xdrop.jrand.generators.location.StreetGenerator;
import me.xdrop.jrand.generators.person.NameGenerator;
import me.xdrop.jrand.model.money.Card;
import me.xdrop.jrand.model.money.CardType;

@Facade(accessor = "card")
public class CardGenerator extends Generator<Card> {
    protected StreetGenerator street;
    protected NameGenerator name;
    protected CardNumberGenerator card;
    protected CardTypeGenerator cardType;
    protected CVVGenerator cvv;
    protected ExpiryDateGenerator expiry;
    protected IssueDateGenerator issue;
    protected CountryGenerator country;
    protected PostcodeGenerator postcode;

    public CardGenerator() {
        this.street = new StreetGenerator().houseNumber();
        this.name = new NameGenerator();
        this.card = new CardNumberGenerator();
        this.cvv = new CVVGenerator();
        this.expiry = new ExpiryDateGenerator();
        this.issue = new IssueDateGenerator();
        this.country = new CountryGenerator();
        this.postcode = new PostcodeGenerator();
        this.cardType = new CardTypeGenerator();
    }

    @Override
    public Card gen() {
        Card card = new Card();
        CardType _cardType = cardType.common().gen();
        card.setBillingAddress(street.gen());
        card.setCardNumber(this.card.cardType(_cardType).gen());
        card.setCvv(cvv.gen());
        Country _country = country.genAsCountry();
        card.setCountry(_country.getName());
        card.setExpiryDate(expiry.gen());
        card.setIssueDate(issue.gen());
        card.setCardType(_cardType.getFullname());
        card.setName(name.gen());
        card.setPostcode(postcode.country(_country.getPrefix()).gen());
        return card;
    }

}
