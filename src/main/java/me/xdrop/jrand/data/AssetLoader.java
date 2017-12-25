package me.xdrop.jrand.data;

import java.io.*;
import java.net.URL;
import java.util.*;

public class AssetLoader {

    private static Map<String, List<Object>> cache = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static List<String> loadList(String assetName) {
        if (cache.containsKey(assetName)) {
            return (List) cache.get(assetName);
        }
        List<String> cached = internalLoadList(assetName);
        cache.put(assetName, (List) cached);

        return cached;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> loadListMapped(String assetName, AssetMapper<T> mapper) {
        if (cache.containsKey(assetName)) {
            return (List) cache.get(assetName);
        }
        List<T> cached = loadMappedList(assetName, mapper);
        cache.put(assetName, (List) cached);

        return cached;
    }

    private static <T> List<T> loadMappedList(String assetName, AssetMapper<T> mapper) {
        File file =loadFileByAssetName(assetName);

        if (file == null) {
            return Collections.emptyList();
        }

        List<T> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(reader.ready()) {
                list.add(mapper.map(reader.readLine()));
            }
        } catch (IOException e) {
            return Collections.emptyList();
        }

        return list;
    }

    private static File loadFileByAssetName(String assetName) {
        ClassLoader classLoader = AssetLoader.class.getClassLoader();
        URL filename = classLoader.getResource("data/" + assetName);

        if (filename == null) return null;

        return new File(filename.getFile());
    }

    private static List<String> internalLoadList(String assetName) {
        File file = loadFileByAssetName(assetName);

        if (file == null) {
            return Collections.emptyList();
        }

        List<String> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(reader.ready()) {
                list.add(reader.readLine());
            }
        } catch (IOException e) {
           return Collections.emptyList();
        }
        return list;
    }



}
