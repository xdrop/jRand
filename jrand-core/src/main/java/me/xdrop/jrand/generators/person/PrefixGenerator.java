package me.xdrop.jrand.generators.person;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.model.person.Prefix;
import me.xdrop.jrand.utils.Choose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Facade(accessor = "prefix")
public class PrefixGenerator extends Generator<String> {
    private boolean isLong;
    private List<Prefix> prefixPool;



    private static final Prefix[] malePrefixes = new Prefix[] {
            new Prefix("Mr", "Mister"), new Prefix("Sir", "Sir")
    };

    private static final Prefix[] femalePrefixes = new Prefix[] {
            new Prefix("Mrs", "Misses"), new Prefix("Ms", "Miss")
    };

    private static final Prefix[] neutralPrefixes = new Prefix[] {
            new Prefix("Dr", "Doctor")
    };

    public PrefixGenerator() {
        this.prefixPool = new ArrayList<>();
    }

    /**
     * Set whether to return prefixes are returned in a non-abreviated form
     * For example Misses instead of Mrs
     * @return The same generator
     */
    public PrefixGenerator full(boolean isLong) {
        this.isLong = isLong;
        return this;
    }

    /**
     * Set the gender of the prefixes to be returned in.
     * "male" or "m" for male, "female" or "f" for female,
     * anything else to include both.
     *
     * @param gender Set to "m"/"male" for male and "f"/"female" for female
     * @return The same generator
     */
    public PrefixGenerator gender(String gender) {
        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")) {
            prefixPool.addAll(Arrays.asList(malePrefixes));
        } else if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("f")) {
            prefixPool.addAll(Arrays.asList(femalePrefixes));
        } else {
            prefixPool.addAll(Arrays.asList(malePrefixes));
            prefixPool.addAll(Arrays.asList(femalePrefixes));
        }
        return this;
    }
    
    public String gen() {
        Prefix prefix = Choose.chooseOne(prefixPool);
        return isLong ? prefix.full : prefix.abbreviation;
    }
}
