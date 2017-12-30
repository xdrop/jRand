package me.xdrop.jrand.generators.money;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.generators.basics.NaturalGenerator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Facade(accessor = "issueDate")
public class IssueDateGenerator extends Generator<String> {
    private boolean longVersion;
    private NaturalGenerator nat;

    public IssueDateGenerator() {
        this.nat = new NaturalGenerator();
        longVersion = false;
    }

    public IssueDateGenerator longVersion() {
        return longVersion(true);
    }

    public IssueDateGenerator longVersion(boolean longVersion) {
        this.longVersion = longVersion;
        return this;
    }

    @Override
    public String gen() {
        DateTime now = new DateTime();
        now = now.minusYears(nat.range(1, 10).gen());
        now = now.minusMonths(nat.range(0, 12).gen());
        return ExpiryDateGenerator.formatDate(now, longVersion);
    }
}
