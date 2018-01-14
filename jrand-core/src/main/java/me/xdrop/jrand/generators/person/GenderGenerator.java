package me.xdrop.jrand.generators.person;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.generators.basics.BoolGenerator;
import me.xdrop.jrand.model.person.Gender;

@Facade(accessor = "gender")
public class GenderGenerator extends Generator<String> {

    private String male;
    private String female;
    private boolean full;
    private BoolGenerator bool;

    public GenderGenerator() {
        this.bool = new BoolGenerator();
    }

    /**
     * Specify custom strings to be returned
     * @param male The male string to return
     * @param female The female string to return
     * @return The same generator
     */
    public GenderGenerator format(String male, String female) {
        this.male = male;
        this.female = female;
        return this;
    }

    /**
     * Return "Male" and "Female" instead of "M" and "F"
     * @return The same generator
     */
    public GenderGenerator full() {
        return full(true);
    }

    /**
     * Return "Male" and "Female" instead of "M" and "F"
     * @param enabled True for full,
     *                False for short
     * @return The same generator
     */
    public GenderGenerator full(boolean enabled) {
        this.full = enabled;
        return this;
    }

    /**
     * The likelihood to return male
     * @param likelihood An integer out of 100,
     *                   where 100 is 100% chance of
     *                   male.
     * @return The same generator
     */
    public GenderGenerator likelihood(int likelihood) {
        bool.likelihood(likelihood);
        return this;
    }

    /**
     * Generate as a {@link Gender} object
     * @return The gender object
     */
    public Gender genAsGender() {
        if(bool.gen()) {
            return Gender.MALE;
        } else {
            return Gender.FEMALE;
        }
    }

    @Override
    public String gen() {
        boolean _male = bool.gen();

        if (male != null && female != null){
            return _male ? male : female;
        }

        if (full) {
            return _male ? "Male" : "Female";
        } else {
            return _male ? "M" : "F";
        }

    }
}
