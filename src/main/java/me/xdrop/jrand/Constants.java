package me.xdrop.jrand;

public class Constants {

    public static final int MAX_INT = Integer.MAX_VALUE;
    public static final int MIN_INT = Integer.MIN_VALUE;

    public static final String alphaLowerPool = "abcdefghijklmnopqrstuvwxyz";
    public static final String alphaUpperPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String numericPool    = "1234567890";
    public static final String symbolsPool    = "!@#$%^&*()[]";


    public static final String vowels = "aeiou";
    public static final String consonants = "bcdfghjklmnpqrstvxzwy";

    public static final String[] midPunc = new String[] {
            ",","'","'s",""
    };

    public static final String[] endPunc = new String[] {
            "?","!"
    };


    /*
        Probably don't need these, but provide a direct char[] interface.
     */
    public static final char[] alphaLowerPoolChar = alphaLowerPool.toCharArray();

    public static final char[] alphaUpperPoolChar = alphaUpperPool.toCharArray();

    public static final char[] numericPoolChar = numericPool.toCharArray();

    public static final char[] symbolsPoolChar = symbolsPool.toCharArray();

}
