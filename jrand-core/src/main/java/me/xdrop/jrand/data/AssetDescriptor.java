package me.xdrop.jrand.data;

import me.xdrop.jrand.Tuple;

import java.util.*;

public class AssetDescriptor<T> {

    private final String fileName;
    private final AssetMapper<T> mapper;
    private final IndexMapper<T> aliasIndexMapper;
    private final IndexMapper<T> groupingIndexMapper;

    public AssetDescriptor(String fileName, AssetMapper<T> mapper, IndexMapper<T> aliasIndexMapper, IndexMapper<T> groupingIndexMapper) {
        this.fileName = fileName;
        this.mapper = mapper;
        this.aliasIndexMapper = aliasIndexMapper;
        this.groupingIndexMapper = groupingIndexMapper;
    }

    public AssetDescriptor(String fileName, AssetMapper<T> mapper, IndexMapper<T> aliasIndexMapper) {
        this.fileName = fileName;
        this.mapper = mapper;
        this.aliasIndexMapper = aliasIndexMapper;
        this.groupingIndexMapper = null;
    }

    public AssetDescriptor(String fileName, AssetMapper<T> mapper) {
        this.fileName = fileName;
        this.mapper = mapper;
        this.aliasIndexMapper = null;
        this.groupingIndexMapper = null;
    }

    private Map<String, T> createAliasIndex(Asset<T> asset) {
        Map<String,T> aliasIndex = new HashMap<>();
        for (T t : asset.getItems()) {
            Tuple<String, T> alias = Objects.requireNonNull(aliasIndexMapper).indexedMap(t);
            aliasIndex.put(alias.getKey(), alias.getVal());
        }
        return aliasIndex;
    }

    private Map<String, List<T>> createGroupingIndex(Asset<T> asset) {
        Map<String,List<T>> groupingIndex = new HashMap<>();
        for (T t : asset.getItems()) {
            Tuple<String, T> alias = Objects.requireNonNull(groupingIndexMapper).indexedMap(t);
            List<T> group = groupingIndex.get(alias.getKey());
            if (group == null) {
                group = new ArrayList<>();
                groupingIndex.put(alias.getKey(), group);
            }
            group.add(alias.getVal());
        }
        return groupingIndex;
    }


    public Asset<T> load() {
        Asset<T> fromCache = AssetLoader.fetch(fileName, mapper);
        Map<String, T> aliasIndex = fromCache.getMappingIndex();
        Map<String, List<T>> groupingIndex = fromCache.getGroupingIndex();

        if (aliasIndexMapper != null && !fromCache.hasMappingIndex()) {
            aliasIndex = createAliasIndex(fromCache);
        }

        if (groupingIndexMapper != null && !fromCache.hasGroupingIndex()) {
            groupingIndex = createGroupingIndex(fromCache);
        }

        return Asset.from(fromCache.getItems(), aliasIndex, groupingIndex);
    }

    public List<T> loadItems() {
        return load().getItems();
    }
}
