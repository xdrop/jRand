package me.xdrop.jrand.data;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssetLoader {
    public List<String> loadAsset(String assetName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL filename = classLoader.getResource(assetName);

        if (filename == null) return Collections.emptyList();

        File file = new File(filename.getFile());
        List<String> list = new ArrayList<>();
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
