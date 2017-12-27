package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.data.AssetMapper;

public class CountryMapper implements AssetMapper<Country> {
    @Override
    public Country map(String element) {
        String[] parts = element.split(":");
        return new Country(parts[0], parts[1]);
    }
}
