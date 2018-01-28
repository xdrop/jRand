package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.model.location.City;
import me.xdrop.jrand.model.location.CityMapper;
import me.xdrop.jrand.utils.Choose;

import java.util.List;
import java.util.Map;

public class CityGenerator extends Generator<String> {
    private final List<City> cities;
    private final Map<String, List<City>> citiesMap;
    private String country;


    public CityGenerator() {
        this.cities = AssetLoader.loadList("cities.txt", new CityMapper());
        this.citiesMap = AssetLoader.loadMultiIndex("cities.txt", new CityMapper());
    }

    public CityGenerator country(String country) {
        this.country = country;
        return this;
    }

    @Override
    public String gen() {
        if (country != null) {
            List<City> list = citiesMap.get(country);
            return Choose.one(list).getName();
        }
        return Choose.one(cities).getName();
    }
}
