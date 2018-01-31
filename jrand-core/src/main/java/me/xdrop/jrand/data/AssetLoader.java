package me.xdrop.jrand.data;

import me.xdrop.jrand.Tuple;

import java.io.*;
import java.util.*;

public class AssetLoader {

    private static Map<String, Asset> cache = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> Asset<T> fetch(String assetName, AssetMapper<T> mapper) {
        if (cache.containsKey(assetName)) {
            return cache.get(assetName);
        }

        Asset<T> read = readFile(assetName, mapper);
        cache.put(assetName, read);
        
        return read;
    }

    private static <T> Asset<T> readFile(String assetName, AssetMapper<T> mapper) {
        InputStream resource = loadResourceByName(assetName);

        if (resource == null) {
            return Asset.from(Collections.<T>emptyList());
        }

        return readFile(resource, mapper);

    }

    private static <T> Asset<T> readFile(InputStream resource, AssetMapper<T> mapper) {
        List<T> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
            while(reader.ready()) {
                T entry = mapper.map(reader.readLine());
                list.add(entry);
            }
        } catch (IOException e) {
            return Asset.from(Collections.<T>emptyList());
        }

        return Asset.from(list);
    }


    private static InputStream loadResourceByName(String assetName) {
        return AssetLoader.class.getResourceAsStream("/data/" + assetName);
    }

}
