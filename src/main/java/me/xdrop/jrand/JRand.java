package me.xdrop.jrand;

import me.xdrop.jrand.builders.basics.BoolGenerator;
import me.xdrop.jrand.builders.basics.CharacterGenerator;

public class JRand {

    public static BoolGenerator bool() {
        return new BoolGenerator();
    }

    public static CharacterGenerator character() {
        return new CharacterGenerator();
    }


}
