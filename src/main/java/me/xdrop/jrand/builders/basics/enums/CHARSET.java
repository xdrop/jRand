package me.xdrop.jrand.builders.basics.enums;

import me.xdrop.jrand.Constants;

public enum CHARSET {

    CHARS_LOWER(Constants.alphaLowerPoolChar),
    CHARS_UPPER(Constants.alphaUpperPoolChar),
    NUMBERS(Constants.numericPoolChar),
    SYMBOLS(Constants.symbolsPoolChar);

    char[] charset;

    CHARSET(char[] set) {
        this.charset = set;
    }

    public char[] getCharset(){
        return this.charset;
    }

}
