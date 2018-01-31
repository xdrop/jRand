package me.xdrop.jrand.data;

import me.xdrop.jrand.Tuple;

public interface IndexMapper<T> {

    Tuple<String,T> indexedMap(T element);
}
