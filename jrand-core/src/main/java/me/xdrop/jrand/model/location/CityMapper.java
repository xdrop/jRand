package me.xdrop.jrand.model.location;

import me.xdrop.jrand.data.AssetMapper;

public class CityMapper implements AssetMapper<City> {

    @Override
    public City map(String element) {
        String[] parts = element.split(",");

        return new City(parts[1], parts[0], parts[2], parts[3]);
    }
}
