package me.xdrop.jrand.data;

import java.util.List;
import java.util.Map;

public class Asset<T> {
    private List<T> list;
    private Map<String, T> index;
    private Map<String, List<T>> multiIndex;

    private Asset(List<T> list) {
        this.list = list;
    }

    private Asset(List<T> list, Map<String, T> index) {
        this.list = list;
        this.index = index;
    }

    private Asset(List<T> list, Map<String,T> index, Map<String, List<T>> multiIndex) {
        this.list = list;
        this.index = index;
        this.multiIndex = multiIndex;
    }

    public static <T> Asset<T> from(List<T> list) {
        return new Asset<>(list);
    }

    public static <T> Asset<T> from(List<T> list, Map<String, T> index) {
        return new Asset<>(list, index);
    }

    public static <T> Asset<T> fromMulti(List<T> list, Map<String, List<T>> multiIndex) {
        return new Asset<>(list, null, multiIndex);
    }

    public List<T> getList() {
        return list;
    }

    public Map<String, T> getIndex() {
        return index;
    }

    public Map<String, List<T>> getMultiIndex() {
        return multiIndex;
    }
}
