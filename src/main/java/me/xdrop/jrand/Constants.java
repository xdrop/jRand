package me.xdrop.jrand;

public class Constants {

    public static int MAX_INT = Integer.MAX_VALUE;
    public static int MIN_INT = Integer.MIN_VALUE;

    public static String alphaLowerPool = "abcdefghijklmnopqrstuvwxyz";
    public static String alphaUpperPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String numericPool    = "1234567890";
    public static String symbolsPool    = "!@#$%^&*()[]";

    /*
        Probably don't need these, but provide a direct char[] interface.
     */
    public static char[] alphaLowerPoolChar = alphaLowerPool.toCharArray();

    public static char[] alphaUpperPoolChar = alphaUpperPool.toCharArray();

    public static char[] numericPoolChar = numericPool.toCharArray();

    public static char[] symbolsPoolChar = symbolsPool.toCharArray();

}
