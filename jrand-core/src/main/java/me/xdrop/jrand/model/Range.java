package me.xdrop.jrand.model;

public class Range<T extends Number> {
    private T min;
    private T max;

    public static <T extends Number> Range<T> from(T min, T max) {
        return new Range<>(min, max);
    }

    public static <T extends Number> Range<T> from(T fixed) {
        return new Range<>(fixed, fixed);
    }

    private Range(T min, T max) {
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

    public Range<T> newMin(T min) {
        return new Range<>(min, max);
    }

    public Range<T> newMax(T max) {
        return new Range<>(min, max);
    }
}
