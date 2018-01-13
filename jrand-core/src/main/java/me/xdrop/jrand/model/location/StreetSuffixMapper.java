package me.xdrop.jrand.model.location;

import me.xdrop.jrand.data.AssetMapper;
import me.xdrop.jrand.model.location.StreetSuffix;

public class StreetSuffixMapper implements AssetMapper<StreetSuffix> {

    @Override
    public StreetSuffix map(String element) {
        String[] parts = element.split(":");
        return new StreetSuffix(parts[0], parts[1]);
    }
}
