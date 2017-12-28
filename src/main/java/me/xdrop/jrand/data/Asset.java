package me.xdrop.jrand.data;

import java.util.List;
import java.util.Map;

public class Asset<T> {
    private List<T> list;
    private Map<String, T> index;

    private Asset(List<T> list) {
        this.list = list;
    }

    private Asset(List<T> list, Map<String, T> index) {
        this.list = list;
        this.index = index;
    }

    public static <T> Asset<T> from(List<T> list) {
        return new Asset<>(list);
    }

    public static <T> Asset<T> from(List<T> list, Map<String, T> index) {
        return new Asset<>(list, index);
    }

    public List<T> getList() {
        return list;
    }

    public Map<String, T> getIndex() {
        return index;
    }

}
