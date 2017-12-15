package me.xdrop.jrand;

import me.xdrop.jrand.generators.basics.*;
import me.xdrop.jrand.generators.person.AgeGenerator;
import me.xdrop.jrand.generators.person.BirthdayGenerator;
import me.xdrop.jrand.generators.person.GenderGenerator;

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

    public static AgeGenerator age() {
        return new AgeGenerator();
    }

    public static BirthdayGenerator birthday() {
        return new BirthdayGenerator();
    }

    public static GenderGenerator gender() {
        return new GenderGenerator();
    }



}
