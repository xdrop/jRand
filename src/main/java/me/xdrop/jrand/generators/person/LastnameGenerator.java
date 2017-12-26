package me.xdrop.jrand.generators.person;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.generators.collections.ListRandUtils;

import java.util.List;

public class LastnameGenerator extends Generator<String> {
    private List<String> names;

    LastnameGenerator() {
        this.names = AssetLoader.loadList("surnames.txt");
    }

    @Override
    public String gen() {
        return ListRandUtils.chooseOne(names);
    }
}
