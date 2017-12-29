package me.xdrop.jrand.data;

import me.xdrop.jrand.Tuple;

public interface IndexedAssetMapper<T> extends AssetMapper<T> {

    Tuple<String,T> indexedMap(String element);
}
