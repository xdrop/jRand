package me.xdrop.jrand.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AssetLoader {
    public List<String> loadAsset(String assetName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(assetName).getFile());
        try {
            InputStream inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
           return new ArrayList<>();
        }
        return new ArrayList<>();
    }
}
