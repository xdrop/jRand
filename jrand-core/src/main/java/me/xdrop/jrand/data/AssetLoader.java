package me.xdrop.jrand.data;

import me.xdrop.jrand.Tuple;

import java.io.*;
import java.net.URL;
import java.util.*;

public class AssetLoader {

    private static Map<String, Asset> cache = new HashMap<>();

    public static List<String> loadList(String assetName) {
       return loadList(assetName, new StringMapper());
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> loadList(String assetName, AssetMapper<T> assetMapper) {
        if (cache.containsKey(assetName)) {
            return cache.get(assetName).getList();
        }
        Asset<T> cached = internalLoad(assetName, assetMapper);
        cache.put(assetName,  cached);

        return cached.getList();
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> loadIndex(String assetName, IndexedAssetMapper<T> assetMapper) {
        if (cache.containsKey(assetName)) {
            return cache.get(assetName).getIndex();
        }
        Asset<T> cached = internalLoad(assetName, assetMapper);
        cache.put(assetName,  cached);

        return cached.getIndex();
    }


    private static <T> Asset<T> internalLoad(String assetName, AssetMapper<T> mapper) {
        InputStream resource = loadResourceByName(assetName);

        if (resource == null) {
            return Asset.from(Collections.<T>emptyList());
        }

        if (mapper instanceof IndexedAssetMapper) {
            return loadIndexed(resource, (IndexedAssetMapper<T>) mapper);
        } else {
            return loadUnindexed(resource, mapper);
        }

    }

    private static <T> Asset<T> loadUnindexed(InputStream resource, AssetMapper<T> mapper) {
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

    private static <T> Asset<T> loadIndexed(InputStream resource, IndexedAssetMapper<T> mapper) {
        List<T> list = new ArrayList<>();
        Map<String,T> map = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
            while(reader.ready()) {
                Tuple<String, T> entry = mapper.indexedMap(reader.readLine());
                list.add(entry.getVal());
                map.put(entry.getKey(), entry.getVal());
            }
        } catch (IOException e) {
            return Asset.from(Collections.<T>emptyList());
        }

        return Asset.from(list,map);
    }

    private static InputStream loadResourceByName(String assetName) {
        return AssetLoader.class.getResourceAsStream("/data/" + assetName);
    }

}
