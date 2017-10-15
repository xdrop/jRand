package me.xdrop.jrand.random;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Rand {
    private Random random = new Random();

    public float randFloat() {
        return random.nextFloat();
    }

    public double randDouble() {
        return random.nextDouble();
    }

    public int randInt() {
        return random.nextInt();
    }

    public int randInt(int n){
        return random.nextInt(n);
    }

    public <T> T chooseOne(List<T> list){
        return list.get(randInt(list.size()));
    }

    public <T> T chooseOne(T[] list){
        return list[randInt(list.length)];
    }
}
