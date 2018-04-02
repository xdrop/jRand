package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.data.Assets;
import me.xdrop.jrand.model.location.Country;
import me.xdrop.jrand.model.location.CountryMapper;
import me.xdrop.jrand.utils.Choose;

import java.util.List;

@Facade(accessor = "country")
public class CountryGenerator extends Generator<String> {

    protected List<Country> countries;
    protected boolean prefix;

    public CountryGenerator() {
        this.countries = Assets.COUNTRIES.loadItems();
        this.prefix = false;
    }

    /**
     * Return a country prefix
     * @return The same generator
     */
    public CountryGenerator prefix() {
        return prefix(true);
    }


    /**
     * Return a country prefix
     * @param enabled True to return prefix,
     *                False otherwise
     * @return The same generator
     */
    public CountryGenerator prefix(boolean enabled) {
        this.prefix = enabled;
        return this;
    }

    /**
     * Generate as a {@link Country} object
     * @return The country object
     */
    public Country genAsCountry(){
        return Choose.one(countries);
    }

    @Override
    public String gen() {
        Country country = Choose.one(countries);
        if (prefix) {
            return country.getPrefix();
        } else {
            return country.getName();
        }
    }
}
