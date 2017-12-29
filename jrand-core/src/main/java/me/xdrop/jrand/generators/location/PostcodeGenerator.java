package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.generators.basics.CharacterGenerator;

import java.util.Map;

public class PostcodeGenerator extends Generator<String> {
    private final CharacterGenerator character;
    private final CountryGenerator countryGen;
    private Country country;

    public PostcodeGenerator() {
        this.character = new CharacterGenerator();
        this.countryGen = new CountryGenerator();
    }

    public PostcodeGenerator country(String countryPrefix) {
        Map<String, Country> index = AssetLoader.loadIndex("countries.txt", new CountryMapper());
        this.country = index.get(countryPrefix.toUpperCase());
        return this;
    }

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
}
