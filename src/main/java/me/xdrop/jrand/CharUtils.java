package me.xdrop.jrand;

import java.util.List;

public class CharUtils {

    public static String capitalize(String in) {
        if (in.length() > 1) {
            return in.substring(0, 1).toUpperCase() + in.substring(1);
        } else {
            return in.toUpperCase();
        }
    }

    public static String join(List<String> in, String sep) {
        int size = in.size();
        StringBuilder sbr = new StringBuilder(size * 2);

        for (int i = 1; i <= size; i++) {
            sbr.append(in.get(i - 1));
            if (i != size){
                sbr.append(sep);
            }
        }

        return sbr.toString();

    }

    public static String join(List<String> in, String sep, String fin) {
        return join(in, sep) + fin;
    }


}
