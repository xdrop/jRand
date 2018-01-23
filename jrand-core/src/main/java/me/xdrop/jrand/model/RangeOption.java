package me.xdrop.jrand.model;

public class RangeOption {
    private int min;
    private int max;

    public static RangeOption from(int min, int max) {
        return new RangeOption(min ,max);
    }

    public static RangeOption from(int fixed) {
        return new RangeOption(fixed, fixed);
    }

    private RangeOption(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public boolean isSingle(){
        return min == max;
    }
}
