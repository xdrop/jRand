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
        this.includedCharsets = new HashSet<>();
    }

    public CharacterGenerator include(CHARSET... charsets) {

        for (CHARSET set : charsets) {
            if (!includedCharsets.add(set)) {
                for (char c : set.getCharset()) {
                    charPool.add(c);
                }
            }
        }

        return this;
    }


    @Override
    public Character gen() {
        return charPool.get(random().randInt(charPool.size() - 1));
    }
}
