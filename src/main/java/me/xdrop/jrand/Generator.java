package me.xdrop.jrand;

import me.xdrop.jrand.random.Rand;

import java.util.*;

public abstract class Generator<T> {

    private Rand randGen;

    public Generator() {
        this.randGen = new Rand();
    }

    public Rand random() {
        return this.randGen;
    }

    public abstract T gen();

    public String genString() {
        return gen().toString();
    }

    public List<T> genMany(int num) {
        List<T> many = new ArrayList<>();
        for (int n = 0; n < num; n++) {
            many.add(gen());
        }
        return many;
    }

    public Set<T> genManyAsSet(int num) {
        List<T> many = new LinkedList<>();
        for (int n = 0; n < num; n++) {
            many.add(gen());
        }
        return new HashSet<>(many);
    }

}
