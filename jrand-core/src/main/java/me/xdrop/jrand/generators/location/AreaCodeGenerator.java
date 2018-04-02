package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.annotation.PropertyFlag;
import me.xdrop.jrand.generators.basics.NaturalGenerator;

@Facade(accessor = "areacode")
public class AreaCodeGenerator extends Generator<String> {
    protected NaturalGenerator nat;

    @PropertyFlag("Include parenthesis around the number")
    protected boolean parenthesis;

    public AreaCodeGenerator() {
        this.nat = new NaturalGenerator();
    }

    @Override
    public String gen() {
        String areaCode = "";
        if (parenthesis) {
            areaCode += "(";
        }
        areaCode += nat.range(2,9).genString() + nat.range(0, 8).genString() + nat.range(0,9).genString();
        if (parenthesis) {
            areaCode += ")";
        }
        return areaCode;
    }
}
