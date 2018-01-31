package me.xdrop.jrand.data;

import java.util.List;
import java.util.Map;

public class Asset<T> {
    private List<T> itemList;
    private Map<String, T> mappingIndex;
    private Map<String, List<T>> groupingIndex;

    private Asset(List<T> itemList, Map<String, T> mappingIndex, Map<String, List<T>> groupingIndex) {
        this.itemList = itemList;
        this.mappingIndex = mappingIndex;
        this.groupingIndex = groupingIndex;
    }

    public static <T> Asset<T> from(List<T> asset) {
        return new Asset<>(asset, null, null);
    }

    public static <T> Asset<T> fromMapping(List<T> asset, Map<String,T> mappingIndex) {
        return new Asset<>(asset, mappingIndex, null);
    }

    public static <T> Asset<T> from(List<T> asset, Map<String,T> mappingIndex, Map<String, List<T>> groupingIndex) {
        return new Asset<>(asset, mappingIndex, groupingIndex);
    }

    public static <T> Asset<T> fromGrouping(List<T> asset, Map<String,List<T>> groupingIndex) {
        return new Asset<>(asset, null, groupingIndex);
    }

    public List<T> getItems() {
        return this.itemList;
    }

    public Map<String, T> getMappingIndex() {
        return this.mappingIndex;
    }

    public Map<String, List<T>> getGroupingIndex() {
        return this.groupingIndex;
    }

    public boolean hasMappingIndex() {
        return mappingIndex != null;
    }

    public boolean hasGroupingIndex() {
        return groupingIndex != null;
    }

}
