package me.xdrop.jrand.data;

import java.io.*;
import java.net.URL;
import java.util.*;

public class AssetLoader {

    private static Map<String, List<String>> cache = new HashMap<>();

    public static List<String> loadAsset(String assetName) {
        if (cache.containsKey(assetName)) {
            return cache.get(assetName);
        }
        List<String> cached = internalLoadAsset(assetName);
        cache.put(assetName, cached);

        return cached;
    }

    private static List<String> internalLoadAsset(String assetName) {
        ClassLoader classLoader = AssetLoader.class.getClassLoader();
        URL filename = classLoader.getResource("data/" + assetName);

        if (filename == null) return Collections.emptyList();

        File file = new File(filename.getFile());
        List<String> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(reader.ready()) {
                list.add(reader.readLine());
            }

        } catch (IOException e) {
           return new ArrayList<>();
        }
        return list;
    }



}
