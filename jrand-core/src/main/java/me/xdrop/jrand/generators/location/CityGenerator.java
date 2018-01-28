package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.data.Assets;
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
        this.cities = Assets.CITIES.loadItems();
        this.citiesMap = Assets.CITIES.load().getGroupingIndex();
    }

    /**
     * Return a city from the given country
     * @param country The given country
     * @return The same generator
     */
    public CityGenerator country(String country) {
        this.country = country;
        return this;
    }

    @Override
    public String gen() {
        if (country != null && citiesMap.get(country) != null) {
            List<City> list = citiesMap.get(country);
            return Choose.one(list).getName();
        }
        return Choose.one(cities).getName();
    }
}
