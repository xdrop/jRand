package me.xdrop.jrand.model.location;

import me.xdrop.jrand.Tuple;
import me.xdrop.jrand.data.IndexedAssetMapper;
import me.xdrop.jrand.model.location.Country;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryMapper implements IndexedAssetMapper<Country> {
    private Pattern pattern = Pattern.compile("\"\"\"([\\w\\d\\s]+)\"\"\"");

    public Country map(String element) {
        String[] parts = element.split(";");
        boolean fixedCode = false;
        String postalCode = parts[2];
        if (parts[2].startsWith("\"\"\"")) {
            fixedCode = true;
            Matcher matcher = pattern.matcher(parts[2]);
            if (matcher.find()) {
                postalCode = matcher.group(1);
            }
        }

        return new Country(parts[1], parts[0], postalCode, fixedCode);
    }

    @Override
    public Tuple<String, Country> indexedMap(String element) {
        Country mapped = map(element);
        return Tuple.from(mapped.getPrefix(), mapped);
    }
}
