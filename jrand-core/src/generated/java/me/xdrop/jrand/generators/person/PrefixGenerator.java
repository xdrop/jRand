package me.xdrop.jrand.generators.person;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.model.person.Gender;
import me.xdrop.jrand.model.person.Prefix;
import me.xdrop.jrand.model.person.PrefixMapper;
import me.xdrop.jrand.utils.Choose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;


@Facade(accessor = "prefix")
public class PrefixGenerator extends Generator<String> {
    private List<Prefix> prefixPool;
    private List<Prefix> malePrefixes;
    private List<Prefix> femalePrefixes;
    private List<Prefix> neutralPrefixes;

    private boolean isLong;
    private boolean all;


    public PrefixGenerator() {
        this.prefixPool = new ArrayList<>();
        malePrefixes = AssetLoader.loadList("male/prefixes.txt", new PrefixMapper());
        femalePrefixes = AssetLoader.loadList("female/prefixes.txt", new PrefixMapper());
        neutralPrefixes = AssetLoader.loadList("neutral/prefixes.txt", new PrefixMapper());
        prefixPool.addAll(malePrefixes);
        prefixPool.addAll(femalePrefixes);
    }

    /**
     * Set whether to return prefixes are returned in a non-abreviated form
     * For example Misses instead of Mrs
     * @param isLong True for long,
     *               False otherwise
     * @return The same generator
     */
    public PrefixGenerator full(boolean isLong) {
        this.isLong = isLong;
        return this;
    }

    /**
     * Set whether to return prefixes are returned in a non-abreviated form
     * For example Misses instead of Mrs
     * @return The same generator
     */
    public PrefixGenerator full() {
        return full(true);
    }
    /**
     * Include all male, female and neutral genders
     * @return The same generator
     */
    public PrefixGenerator all() {
        return all(true);
    }

    /**
     * Include all male, female and neutral genders
     * @param enabled True for enabled,
     *                False otherwise
     * @return The same generator
     */
    public PrefixGenerator all(boolean enabled) {
        if (enabled){
            prefixPool.clear();
            prefixPool.addAll(malePrefixes);
            prefixPool.addAll(femalePrefixes);
            prefixPool.addAll(neutralPrefixes);
        } else {
            prefixPool.removeAll(neutralPrefixes);
        }
        return this;
    }

    /**
     * Set the gender of the prefixes to be returned in.
     *
     * @param gender Gender to generate
     * @return The same generator
     */
    public PrefixGenerator gender(Gender gender) {
        prefixPool.clear();
        if (gender == Gender.MALE) {
            prefixPool.addAll(malePrefixes);
        } else if (gender == Gender.FEMALE) {
            prefixPool.addAll(femalePrefixes);
        } else if (gender == Gender.NEUTRAL) {
            prefixPool.addAll(neutralPrefixes);
        } else {
            prefixPool.addAll(malePrefixes);
            prefixPool.addAll(femalePrefixes);
        }
        return this;
    }


    /**
     * Set the gender of the prefixes to be returned in.
     * "male" or "m" for male, "female" or "f" for female,
     * "n" for neutral, "all" for all, anything else to include only
     * male and female
     *
     * @param gender Set to "m"/"male" for male and "f"/"female" for female
     * @return The same generator
     */
    public PrefixGenerator gender(String gender) {
        prefixPool.clear();
        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")) {
            prefixPool.addAll(malePrefixes);
        } else if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("f")) {
            prefixPool.addAll(femalePrefixes);
        } else if (gender.equalsIgnoreCase("neutral") || gender.equalsIgnoreCase("n")) {
            prefixPool.addAll(neutralPrefixes);
        } else if (gender.equalsIgnoreCase("all")) {
            prefixPool.addAll(malePrefixes);
            prefixPool.addAll(femalePrefixes);
            prefixPool.addAll(neutralPrefixes);
        } else {
            prefixPool.addAll(malePrefixes);
            prefixPool.addAll(femalePrefixes);
        }
        return this;
    }

    public Prefix genAsPrefix() {
        return Choose.chooseOne(prefixPool);
    }

    public String gen() {
        Prefix prefix = Choose.chooseOne(prefixPool);
        return isLong ? prefix.getFull() : prefix.getAbbreviation();
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    public final PrefixGenerator fork() {
        return new PrefixGenerator(
                new java.util.ArrayList<>(prefixPool),
                new java.util.ArrayList<>(malePrefixes),
                new java.util.ArrayList<>(femalePrefixes),
                new java.util.ArrayList<>(neutralPrefixes),
                isLong,
                all);
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    private PrefixGenerator(List<Prefix> prefixPool, List<Prefix> malePrefixes,
            List<Prefix> femalePrefixes, List<Prefix> neutralPrefixes, boolean isLong,
            boolean all) {
        this.prefixPool = prefixPool;
        this.malePrefixes = malePrefixes;
        this.femalePrefixes = femalePrefixes;
        this.neutralPrefixes = neutralPrefixes;
        this.isLong = isLong;
        this.all = all;
    }
}
