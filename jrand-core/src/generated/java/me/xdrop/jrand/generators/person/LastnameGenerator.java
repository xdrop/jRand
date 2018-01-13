package me.xdrop.jrand.generators.person;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.utils.Choose;

import java.util.List;
import javax.annotation.Generated;

@Facade(accessor = "lastname")
public class LastnameGenerator extends Generator<String> {
    private List<String> names;

    public LastnameGenerator() {
        this.names = AssetLoader.loadList("neutral/surnames.txt");
    }

    @Override
    public String gen() {
        return Choose.chooseOne(names);
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    public final LastnameGenerator fork() {
        return new LastnameGenerator(
                new java.util.ArrayList<>(names));
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    private LastnameGenerator(List<String> names) {
        this.names = names;
    }
}
