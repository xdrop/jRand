package me.xdrop.jrand.generators.location;

public class Country {
    private String name;
    private String prefix;
    private String postalFormat;
    private boolean postalFixed;

    public Country(String name, String prefix, String postalFormat, boolean postalFixed) {
        this.name = name;
        this.prefix = prefix;
        this.postalFormat = postalFormat;
        this.postalFixed = postalFixed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPostalFormat() {
        return postalFormat;
    }

    public void setPostalFormat(String postalFormat) {
        this.postalFormat = postalFormat;
    }

    public boolean isPostalFixed() {
        return postalFixed;
    }

    public void setPostalFixed(boolean postalFixed) {
        this.postalFixed = postalFixed;
    }
}
