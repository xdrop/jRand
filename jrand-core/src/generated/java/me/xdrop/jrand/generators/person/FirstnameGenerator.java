package me.xdrop.jrand.generators.person;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.utils.Choose;

import java.util.List;
import javax.annotation.Generated;

@Facade(accessor = "firstname")
public class FirstnameGenerator extends Generator<String>{
    private List<String> names;

    public FirstnameGenerator() {
        this.names = AssetLoader.loadList("male/firstnames.txt");
    }

    @Override
    public String gen() {
        return Choose.chooseOne(names);
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    public final FirstnameGenerator fork() {
        return new FirstnameGenerator(
                new java.util.ArrayList<>(names));
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    private FirstnameGenerator(List<String> names) {
        this.names = names;
    }
}
