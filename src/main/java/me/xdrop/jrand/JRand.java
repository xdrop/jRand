package me.xdrop.jrand;

import me.xdrop.jrand.builders.basics.BoolGenerator;

public class JRand {

    public static BoolGenerator bool() {
        return new BoolGenerator();
    }


}
