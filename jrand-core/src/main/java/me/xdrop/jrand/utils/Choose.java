package me.xdrop.jrand.utils;

import me.xdrop.jrand.generators.basics.NaturalGenerator;

import java.util.ArrayList;
import java.util.List;

public class Choose {

    private final static NaturalGenerator nat = new NaturalGenerator();

    /**
     * Return a random element from the list
     * @param list The input list
     * @return The random element
     */
    public static <T> T one(List<T> list) {
         int index = nat.range(list.size()).gen();
         return list.get(index);
    }

    /**
     * Return a random element from the list
     * @param arr The input array
     * @return The random element
     */
    public static <T> T one(T[] arr) {
        int index = nat.range(arr.length).gen();
        return arr[index];
    }

    /**
     * Return a random element from the list
     * @return The random element
     */
    public static int one(int[] list) {
        int index = nat.range(list.length).gen();
        return list[index];
    }

    /**
     * Return a random element from the list
     * @return The random element
     */
    public static double one(double[] list) {
        int index = nat.range(list.length).gen();
        return list[index];
    }

    /**
     * Return n random elements from the list (with replacement)
     * @param list Input list
     * @param n Number of elements to retrieve
     * @return A list of random elements
     */
    public static <T> List<T> N(List<T> list, int n) {
        List<Integer> indices = nat.range(list.size()).genMany(n);
        List<T> result = new ArrayList<>();
        for (Integer i : indices) {
            result.add(list.get(i));
        }
        return result;
    }

    /**
     * Return n *unique* random elements from the list (without replacement)
     * @param list Input list
     * @param n Number of elements to return
     * @return A list of unique random elements
     */
    public static <T> List<T> NUnique(List<T> list, int n) {
        List<Integer> indices = nat.sample(n, list.size());
        List<T> result = new ArrayList<>();
        for (Integer i : indices) {
            result.add(list.get(i));
        }
        return result;
    }

    public static <T> List<T> shuffle() {
        throw new UnsupportedOperationException();
    }

    public static <T> List<T> shuffleInplace() {
        throw new UnsupportedOperationException();
    }




}
