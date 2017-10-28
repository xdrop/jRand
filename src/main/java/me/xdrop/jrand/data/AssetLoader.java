package me.xdrop.jrand.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AssetLoader {
    public List<String> loadAsset(String assetName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(assetName).getFile());
        List<String> list = new ArrayList<>();
        reverse(list);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(reader.ready()) {
                list.add(reader.readLine());
            }

        } catch (IOException e) {
           return new ArrayList<>();
        }
        return list;
    }

    public static void reverse(List<?> list) { rev(list); }
    private static <T> void rev(List<T> list) {
        List<T> tmp = new ArrayList<T>(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, tmp.get(list.size() - i - 1));
        }
    }

}
