package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.data.AssetMapper;

public class StreetSuffixMapper implements AssetMapper<StreetSuffix> {

    @Override
    public StreetSuffix map(String element) {
        String[] parts = element.split(":");
        return new StreetSuffix(parts[0], parts[1]);
    }
}
