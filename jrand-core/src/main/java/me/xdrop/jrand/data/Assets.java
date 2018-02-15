package me.xdrop.jrand.data;

import me.xdrop.jrand.model.location.*;
import me.xdrop.jrand.model.person.Prefix;
import me.xdrop.jrand.model.person.PrefixMapper;

public class Assets {

    public static AssetDescriptor<City> CITIES = new AssetDescriptor<>("cities.txt", new CityMapper(), null, new CityMapper());
    public static AssetDescriptor<Country> COUNTRIES = new AssetDescriptor<>("countries.txt", new CountryMapper(), new CountryMapper());
    public static AssetDescriptor<StreetSuffix> UK_STREET_SUFFIXES = new AssetDescriptor<>("uk/street_suffixes.txt", new StreetSuffixMapper());
    public static AssetDescriptor<StreetSuffix> US_STREET_SUFFIXES = new AssetDescriptor<>("us/street_suffixes.txt", new StreetSuffixMapper());
    public static AssetDescriptor<String> NEUTRAL_SURNAMES = new AssetDescriptor<>("neutral/surnames.txt", new StringMapper());
    public static AssetDescriptor<String> MALE_FIRSTNAMES = new AssetDescriptor<>("male/firstnames.txt", new StringMapper());
    public static AssetDescriptor<String> FEMALE_FIRSTNAMES = new AssetDescriptor<>("female/firstnames.txt", new StringMapper());
    public static AssetDescriptor<Prefix> NEUTRAL_PREFIXES = new AssetDescriptor<>("neutral/prefixes.txt", new PrefixMapper());
    public static AssetDescriptor<Prefix> MALE_PREFIXES = new AssetDescriptor<>("male/prefixes.txt", new PrefixMapper());
    public static AssetDescriptor<Prefix> FEMALE_PREFIXES = new AssetDescriptor<>("female/prefixes.txt", new PrefixMapper());
    public static AssetDescriptor<String> LOREM = new AssetDescriptor<>("lorem.txt", new StringMapper());
    public static AssetDescriptor<String> UK_PHONE_LAND = new AssetDescriptor<>("uk/landline_phone_numbers.txt", new StringMapper());
    public static AssetDescriptor<String> UK_PHONE_MOBILE = new AssetDescriptor<>("uk/mobile_phone_numbers.txt", new StringMapper());
    public static AssetDescriptor<String> US_PHONE_LAND = new AssetDescriptor<>("uk/landline_phone_numbers.txt", new StringMapper());
    public static AssetDescriptor<String> US_PHONE_MOBILE = new AssetDescriptor<>("uk/mobile_phone_numbers.txt", new StringMapper());

}
