package me.xdrop.jrand.model.person;

import me.xdrop.jrand.data.AssetMapper;

public class PrefixMapper implements AssetMapper<Prefix> {
    @Override
    public Prefix map(String element) {
        String[] parts = element.split(";");
        return new Prefix(parts[0], parts[1], parts[2]);
    }
}
