package me.xdrop.jrand.generators.text;

import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.data.AssetLoader;

import java.util.List;

@Facade(accessor = "lorem")
public class LoremGenerator {
    private List<String> loremWords;

    public LoremGenerator() {
        this.loremWords = AssetLoader.loadList("lorem.txt");
    }
}
