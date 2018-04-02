package me.xdrop.jrand.generators.money;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.generators.basics.NaturalGenerator;

@Facade(accessor = "cvv")
public class CVVGenerator extends Generator<String> {
    protected NaturalGenerator nat;
    protected boolean amex;

    public CVVGenerator() {
        nat = new NaturalGenerator();
        amex = false;
    }

    /**
     * Return a 4-digit American Express CVV
     *
     * @param enable Set to true to enable this
     *               setting
     * @return The same generator
     */
    public CVVGenerator amex(boolean enable) {
        this.amex = enable;
        return this;
    }

    /**
     * Return a 4-digit American Express CVV
     *
     * @return The same generator
     */
    public CVVGenerator amex() {
        return amex(true);
    }

    @Override
    public String gen() {
        StringBuilder cvv = new StringBuilder(3);
        cvv.append(nat.range(1,9).gen());
        cvv.append(nat.range(0,9).gen());
        cvv.append(nat.range(0,9).gen());
        if (amex) {
            cvv.append(nat.range(0,9).gen());
        }
        // nat.range(100,999)?
        return cvv.toString();
    }
}
