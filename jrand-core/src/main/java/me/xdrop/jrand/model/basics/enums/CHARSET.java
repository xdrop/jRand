package me.xdrop.jrand.model.basics.enums;

import static me.xdrop.jrand.Constants.*;

public enum CHARSET {

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
