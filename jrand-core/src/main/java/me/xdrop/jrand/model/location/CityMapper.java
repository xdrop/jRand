package me.xdrop.jrand.model.location;

import me.xdrop.jrand.Tuple;
import me.xdrop.jrand.data.IndexedAssetMapper;

public class CityMapper implements IndexedAssetMapper<City>{
    @Override
    public Tuple<String, City> indexedMap(String element) {
        return null;
    }

    @Override
    public City map(String element) {
        String[] parts = element.split(",");

        return new City(parts[1], parts[0], parts[2], parts[3]);
    }
}
