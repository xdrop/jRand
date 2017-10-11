package me.xdrop.jrand.builders.basics.enums;

import org.apache.commons.lang3.ArrayUtils;

import static me.xdrop.jrand.Constants.*;
import org.apache.commons.lang3.ArrayUtils;

public enum CHARSET {

    CHARS_ALL(ArrayUtils.addAll(alphaLowerPoolChar, alphaUpperPoolChar)),
    CHARS_LOWER(alphaLowerPoolChar),
    CHARS_UPPER(alphaUpperPoolChar),
    NUMBERS(numericPoolChar),
    SYMBOLS(symbolsPoolChar);

    char[] charset;

    CHARSET(char[] set) {
        this.charset = set;
    }

    public char[] getCharset(){
        return this.charset;
    }

}
