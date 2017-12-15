package me.xdrop.jrand.data;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AssetLoader {

    private static Map<String, List<String>> cache;

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
        URL filename = classLoader.getResource(assetName);

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

    public static void reverse(List<?> list) { rev(list); }

    private static <T> void rev(List<T> list) {
        List<T> tmp = new ArrayList<T>(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, tmp.get(list.size() - i - 1));
        }
    }

}
