package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.generators.collections.ListRandUtils;

import java.util.List;

public class CountryGenerator extends Generator<String> {

    private List<Country> countries;
    private boolean prefix;

    public CountryGenerator() {
        this.countries = AssetLoader.loadList("countries.txt", new CountryMapper());
        this.prefix = false;
    }

    public CountryGenerator prefix() {
        return prefix(true);
    }

    public CountryGenerator prefix(boolean enabled) {
        this.prefix = enabled;
        return this;
    }

    public Country genAsCountry(){
        return ListRandUtils.chooseOne(countries);
    }

    @Override
    public String gen() {
        Country country = ListRandUtils.chooseOne(countries);
        if (prefix) {
            return country.getPrefix();
        } else {
            return country.getName();
        }
    }
}
