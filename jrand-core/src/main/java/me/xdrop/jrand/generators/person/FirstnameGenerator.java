package me.xdrop.jrand.generators.person;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.utils.Choose;

import java.util.List;

@Facade(accessor = "firstname")
public class FirstnameGenerator extends Generator<String>{
    private List<String> names;

    public FirstnameGenerator() {
        this.names = AssetLoader.loadList("firstnames.txt");
    }

    @Override
    public String gen() {
        return Choose.chooseOne(names);
    }
}
