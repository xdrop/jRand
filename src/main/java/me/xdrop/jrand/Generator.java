package me.xdrop.jrand;

import me.xdrop.jrand.random.Rand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public Collection<T> genMany(int num){
        List<T> many = new ArrayList<>();
        for(int n = 0; n < num; n++) {
            many.add(gen());
        }
        return many;
    }

}
