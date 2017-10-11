package me.xdrop.jrand;

import me.xdrop.jrand.builders.basics.*;

/*
                               JRand Facade
 */
public class JRand {

    public static final BoolGenerator bool() {
        return new BoolGenerator();
    }

    public static final CharacterGenerator character() {
        return new CharacterGenerator();
    }

    public static final DoubleGenerator dbl() { return new DoubleGenerator(); }

    public static final FloatGenerator flt() { return new FloatGenerator(); }

    public static final DecimalGenerator decimal() {
        return new DecimalGenerator();
    }


}
