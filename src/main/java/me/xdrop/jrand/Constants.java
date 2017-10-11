package me.xdrop.jrand;

public class Constants {

    public static int MAX_INT = Integer.MAX_VALUE;
    public static int MIN_INT = Integer.MIN_VALUE;

    public static String alphaLowerPool = "abcdefghijklmnopqrstuvwxyz";
    public static String alphaUpperPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String numericPool    = "1234567890";
    public static String symbolsPool    ="!@#$%^&*()[]";

    public static char[] alphaLowerPoolChar = new char[] {
            'a','b','c','d','e','f','g',
            'h','i','j','k','l','m','n',
            'o','p','q','r','s','t','u',
            'v','w','x','y','z'};

    public static char[] alphaUpperPoolChar = new char[] {
            'A','B','C','D','E','F','G',
            'H','I','J','K','L','M','N',
            'O','P','Q','R','S','T','U',
            'V','W','X','Y','Z'};

    public static char[] numericPoolChar = new char[] {
            '1','2','3','4','5','6','7','8','9','0'};

    public static char[] symbolsPoolChar = new char[] {
            '!','@','#','$','%','^','&','*','(',')','[',']'
    };
}
