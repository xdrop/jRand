package me.xdrop.jrand.generators.location;

public class StreetSuffix {
    private String longVersion;
    private String shortVersion;

    public StreetSuffix(String longVersion, String shortVersion) {
        this.longVersion = longVersion;
        this.shortVersion = shortVersion;
    }

    public String getLongVersion() {
        return longVersion;
    }

    public String getShortVersion() {
        return shortVersion;
    }
}
