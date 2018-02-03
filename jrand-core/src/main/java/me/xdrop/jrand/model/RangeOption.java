package me.xdrop.jrand.model;

public class RangeOption<T extends Number> {
    private T min;
    private T max;

    public static <T extends Number> RangeOption<T> from(T min, T max) {
        return new RangeOption<>(min, max);
    }

    public static <T extends Number> RangeOption<T> from(T fixed) {
        return new RangeOption<>(fixed, fixed);
    }

    private RangeOption(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public boolean isSingle() {
        return min.equals(max);
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }

    public RangeOption<T> newMin(T min) {
        return new RangeOption<>(min, max);
    }

    public RangeOption<T> newMax(T max) {
        return new RangeOption<>(min, max);
    }
}
