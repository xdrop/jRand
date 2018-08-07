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

    public String repeat(int times) {
        StringBuilder sb = new StringBuilder();
        while (times-- > 0) {
            sb.append(gen());
        }
        return sb.toString();
    }

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

    public List<T> genManyUnique(int num) {
        List<T> many = new LinkedList<>();
        Set<T> present = new HashSet<>();


        for (int n = 0; n < num; n++) {
            T gen;
            do {
                gen = gen();
            } while (present.contains(gen));
            many.add(gen);
            present.add(gen);
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
