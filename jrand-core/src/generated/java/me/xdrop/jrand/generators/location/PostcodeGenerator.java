package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.generators.basics.CharacterGenerator;
import me.xdrop.jrand.model.location.Country;
import me.xdrop.jrand.model.location.CountryMapper;

import java.util.Map;
import javax.annotation.Generated;

@Facade(accessor = "postcode")
public class PostcodeGenerator extends Generator<String> {
    private CharacterGenerator character;
    private CountryGenerator countryGen;
    private Country country;

    public PostcodeGenerator() {
        this.character = new CharacterGenerator();
        this.countryGen = new CountryGenerator();
    }

    /**
     * Specify the country <b>prefix</b> as a string to generate the postcode for.
     * @param countryPrefix The country's 2 or 3 digit prefix
     * @return The same generator
     */
    public PostcodeGenerator country(String countryPrefix) {
        Map<String, Country> index = AssetLoader.loadIndex("countries.txt", new CountryMapper());
        this.country = index.get(countryPrefix.toUpperCase());
        return this;
    }

    /**
     * Generate a postcode from a postal format.
     *
     * In the postal format 'A' will generate an alphabet
     * character while '9' will generate a digit.
     *
     * For example:
     * <pre>
     *     A999
     * </pre>
     *
     * Will return something like:
     * <pre>
     *     Z233
     * </pre>
     * @param postalFormat The postal format string
     * @return The random postcode generated
     */
    public String fromFormat(String postalFormat) {
        if (postalFormat.equals("-")) {
            return fromFormat("A9999");
        }

        StringBuilder sb = new StringBuilder(8);
        for (char c : postalFormat.toCharArray()) {
            if (c == 'A') {
                sb.append(character.alpha().casing("upper").gen());
            } else if (c == '9') {
                sb.append(character.pool("123456789").gen());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @Override
    public String gen() {
        if (country == null ){
            country = countryGen.genAsCountry();
        }

        if (!country.isPostalFixed()) {
            return fromFormat(country.getPostalFormat());
        } else {
            return country.getPostalFormat();
        }
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    public final PostcodeGenerator fork() {
        return new PostcodeGenerator(
                character.fork(),
                countryGen.fork(),
                country);
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    private PostcodeGenerator(CharacterGenerator character, CountryGenerator countryGen,
            Country country) {
        this.character = character;
        this.countryGen = countryGen;
        this.country = country;
    }
}
