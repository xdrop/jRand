package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.model.location.Country;
import me.xdrop.jrand.model.location.CountryMapper;
import me.xdrop.jrand.utils.Choose;

import java.util.List;
import javax.annotation.Generated;

@Facade(accessor = "country")
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
        return Choose.chooseOne(countries);
    }

    @Override
    public String gen() {
        Country country = Choose.chooseOne(countries);
        if (prefix) {
            return country.getPrefix();
        } else {
            return country.getName();
        }
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    public final CountryGenerator fork() {
        return new CountryGenerator(
                new java.util.ArrayList<>(countries),
                prefix);
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    private CountryGenerator(List<Country> countries, boolean prefix) {
        this.countries = countries;
        this.prefix = prefix;
    }
}
