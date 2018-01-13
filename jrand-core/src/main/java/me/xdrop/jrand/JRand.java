package me.xdrop.jrand;

import me.xdrop.jrand.generators.basics.*;
import me.xdrop.jrand.generators.location.CountryGenerator;
import me.xdrop.jrand.generators.location.PostcodeGenerator;
import me.xdrop.jrand.generators.location.StreetGenerator;
import me.xdrop.jrand.generators.money.*;
import me.xdrop.jrand.generators.person.*;

/*
                               JRand Facade
 */
public class JRand {

    public static BoolGenerator bool() {
        return new BoolGenerator();
    }

    public static CharacterGenerator character() {
        return new CharacterGenerator();
    }

    public static DoubleGenerator dbl() {
        return new DoubleGenerator();
    }

    public static FloatGenerator flt() {
        return new FloatGenerator();
    }

    public static DecimalGenerator decimal() {
        return new DecimalGenerator();
    }

    public static NaturalGenerator natural() {
        return new NaturalGenerator();
    }

    public static StringGenerator string() {
        return new StringGenerator();
    }

    public static FirstnameGenerator firstname(){
        return new FirstnameGenerator();
    }

    public static LastnameGenerator lastname() {
        return new LastnameGenerator();
    }

    public static PrefixGenerator prefix(){
        return new PrefixGenerator();
    }

    public static AgeGenerator age() {
        return new AgeGenerator();
    }

    public static BirthdayGenerator birthday() {
        return new BirthdayGenerator();
    }

    public static GenderGenerator gender() {
        return new GenderGenerator();
    }

    public static CountryGenerator country() {
        return new CountryGenerator();
    }

    public static PostcodeGenerator postcode(){
        return new PostcodeGenerator();
    }

    public static StreetGenerator street() {
        return new StreetGenerator();
    }

    public static CardGenerator card(){
        return new CardGenerator();
    }

    public static CardNumberGenerator cardNo(){
        return new CardNumberGenerator();
    }

    public static CardTypeGenerator cardType(){
        return new CardTypeGenerator();
    }

    public static ExpiryDateGenerator expiryDate(){
        return new ExpiryDateGenerator();
    }

    public static IssueDateGenerator issueDate() {
        return new IssueDateGenerator();
    }

    public static CVVGenerator cvv(){
        return new CVVGenerator();
    }




}
