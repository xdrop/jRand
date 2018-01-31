package me.xdrop.jrand.generators.person;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.data.Assets;
import me.xdrop.jrand.utils.Choose;

import java.util.List;

@Facade(accessor = "lastname")
public class LastnameGenerator extends Generator<String> {
    private List<String> names;

    public LastnameGenerator() {
        this.names = Assets.NEUTRAL_SURNAMES.load().getItems();
    }

    @Override
    public String gen() {
        return Choose.one(names);
    }
}
