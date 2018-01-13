package me.xdrop.jrand;

import me.xdrop.jrand.generators.basics.BoolGenerator;
import me.xdrop.jrand.generators.basics.CharacterGenerator;
import me.xdrop.jrand.generators.basics.DecimalGenerator;
import me.xdrop.jrand.generators.basics.DoubleGenerator;
import me.xdrop.jrand.generators.basics.FloatGenerator;
import me.xdrop.jrand.generators.basics.NaturalGenerator;
import me.xdrop.jrand.generators.basics.StringGenerator;
import me.xdrop.jrand.generators.location.CountryGenerator;
import me.xdrop.jrand.generators.location.PostcodeGenerator;
import me.xdrop.jrand.generators.location.StreetGenerator;
import me.xdrop.jrand.generators.money.CVVGenerator;
import me.xdrop.jrand.generators.money.CardGenerator;
import me.xdrop.jrand.generators.money.CardNumberGenerator;
import me.xdrop.jrand.generators.money.CardTypeGenerator;
import me.xdrop.jrand.generators.money.ExpiryDateGenerator;
import me.xdrop.jrand.generators.money.IssueDateGenerator;
import me.xdrop.jrand.generators.person.AgeGenerator;
import me.xdrop.jrand.generators.person.BirthdayGenerator;
import me.xdrop.jrand.generators.person.FirstnameGenerator;
import me.xdrop.jrand.generators.person.GenderGenerator;
import me.xdrop.jrand.generators.person.LastnameGenerator;
import me.xdrop.jrand.generators.person.NameGenerator;
import me.xdrop.jrand.generators.person.PrefixGenerator;
import me.xdrop.jrand.generators.text.LoremGenerator;
import me.xdrop.jrand.generators.text.ParagraphGenerator;
import me.xdrop.jrand.generators.text.SentenceGenerator;
import me.xdrop.jrand.generators.text.SyllableGenerator;
import me.xdrop.jrand.generators.text.WordGenerator;

public final class JRand {
    public static final BirthdayGenerator birthday() {
        return new BirthdayGenerator();
    }

    public static final CountryGenerator country() {
        return new CountryGenerator();
    }

    public static final ParagraphGenerator paragraph() {
        return new ParagraphGenerator();
    }

    public static final FirstnameGenerator firstname() {
        return new FirstnameGenerator();
    }

    public static final StringGenerator string() {
        return new StringGenerator();
    }

    public static final BoolGenerator bool() {
        return new BoolGenerator();
    }

    public static final GenderGenerator gender() {
        return new GenderGenerator();
    }

    public static final PrefixGenerator prefix() {
        return new PrefixGenerator();
    }

    public static final CardNumberGenerator cardNo() {
        return new CardNumberGenerator();
    }

    public static final ExpiryDateGenerator expiryDate() {
        return new ExpiryDateGenerator();
    }

    public static final LoremGenerator lorem() {
        return new LoremGenerator();
    }

    public static final CharacterGenerator character() {
        return new CharacterGenerator();
    }

    public static final StreetGenerator street() {
        return new StreetGenerator();
    }

    public static final IssueDateGenerator issueDate() {
        return new IssueDateGenerator();
    }

    public static final SentenceGenerator sentence() {
        return new SentenceGenerator();
    }

    public static final CVVGenerator cvv() {
        return new CVVGenerator();
    }

    public static final NaturalGenerator natural() {
        return new NaturalGenerator();
    }

    public static final SyllableGenerator syllable() {
        return new SyllableGenerator();
    }

    public static final PostcodeGenerator postcode() {
        return new PostcodeGenerator();
    }

    public static final CardTypeGenerator cardType() {
        return new CardTypeGenerator();
    }

    public static final FloatGenerator flt() {
        return new FloatGenerator();
    }

    public static final DoubleGenerator dbl() {
        return new DoubleGenerator();
    }

    public static final LastnameGenerator lastname() {
        return new LastnameGenerator();
    }

    public static final NameGenerator name() {
        return new NameGenerator();
    }

    public static final DecimalGenerator decimal() {
        return new DecimalGenerator();
    }

    public static final WordGenerator word() {
        return new WordGenerator();
    }

    public static final AgeGenerator age() {
        return new AgeGenerator();
    }

    public static final CardGenerator card() {
        return new CardGenerator();
    }
}
