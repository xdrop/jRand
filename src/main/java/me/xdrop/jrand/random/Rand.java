package me.xdrop.jrand.random;

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

    public long randLong() {
        return random.nextLong();
    }

    public boolean randBool() { return random.nextBoolean(); }

    public int randInt(int n) {
        return random.nextInt(n);
    }

}
