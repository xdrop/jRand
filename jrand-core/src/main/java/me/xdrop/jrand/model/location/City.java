package me.xdrop.jrand.model.location;

public class City {
    private String country;
    private String name;
    private String subcounty;
    private String geoNameid;

    public City(String country, String name, String subcounty, String geoNameid) {
        this.country = country;
        this.name = name;
        this.subcounty = subcounty;
        this.geoNameid = geoNameid;
    }


    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getSubcounty() {
        return subcounty;
    }

    public String getGeoNameid() {
        return geoNameid;
    }
}
