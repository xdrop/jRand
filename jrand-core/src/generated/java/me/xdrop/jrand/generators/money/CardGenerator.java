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
import javax.annotation.Generated;

@Facade(accessor = "card")
public class CardGenerator extends Generator<Card> {
    private StreetGenerator street;
    private NameGenerator name;
    private CardNumberGenerator card;
    private CardTypeGenerator cardType;
    private CVVGenerator cvv;
    private ExpiryDateGenerator expiry;
    private IssueDateGenerator issue;
    private CountryGenerator country;
    private PostcodeGenerator postcode;

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
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    public final CardGenerator fork() {
        return new CardGenerator(
                street.fork(),
                name.fork(),
                card.fork(),
                cardType.fork(),
                cvv.fork(),
                expiry.fork(),
                issue.fork(),
                country.fork(),
                postcode.fork());
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    private CardGenerator(StreetGenerator street, NameGenerator name, CardNumberGenerator card,
            CardTypeGenerator cardType, CVVGenerator cvv, ExpiryDateGenerator expiry,
            IssueDateGenerator issue, CountryGenerator country, PostcodeGenerator postcode) {
        this.street = street;
        this.name = name;
        this.card = card;
        this.cardType = cardType;
        this.cvv = cvv;
        this.expiry = expiry;
        this.issue = issue;
        this.country = country;
        this.postcode = postcode;
    }

}
