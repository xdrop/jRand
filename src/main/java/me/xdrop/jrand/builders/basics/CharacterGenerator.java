package me.xdrop.jrand.builders.basics;

import me.xdrop.jrand.Constants;
import me.xdrop.jrand.Generator;
import me.xdrop.jrand.builders.basics.enums.CHARSET;

import java.util.*;

public class CharacterGenerator extends Generator<Character> {

    private List<Character> charPool;
    //To ensure user doesn't add duplicate charsets to pool.
    private Set<CHARSET> includedCharsets;

    public CharacterGenerator() {
        this.charPool = new ArrayList<>(30);
    }

    public CharacterGenerator include(CHARSET... charsets) {

        for (CHARSET set : charsets) {
            if (includedCharsets.contains(set)) {
                continue;
            } else {
                includedCharsets.add(set);
                for (char c : set.getCharset()) {
                    charPool.add(c);
                }
            }
        }

        return this;
    }


    @Override
    public Character generate() {
        return charPool.get(random().randInt(charPool.size() - 1));
    }

    @Override
    public Collection<Character> generateMany(int num) {
        List<Character> list = new ArrayList<>();
        for(int n = 0; n < num; n++) {
            list.add(this.generate());
        }

        return list;
    }
}
