package me.xdrop.jrand.model.location;

import me.xdrop.jrand.Tuple;
import me.xdrop.jrand.data.AssetMapper;
import me.xdrop.jrand.data.IndexMapper;

public class CityMapper implements AssetMapper<City>, IndexMapper<City>{


    @Override
    public City map(String element) {
        String[] parts = element.split(",");

        return new City(parts[1], parts[0], parts[2], parts[3]);
    }

    @Override
    public Tuple<String, City> indexedMap(City element) {
        return Tuple.from(element.getCountry(), element);
    }
}
