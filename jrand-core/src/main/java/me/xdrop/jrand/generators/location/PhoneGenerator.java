package me.xdrop.jrand.generators.location;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.annotation.PropertyFlag;
import me.xdrop.jrand.data.Assets;
import me.xdrop.jrand.generators.basics.NaturalGenerator;
import me.xdrop.jrand.generators.basics.StringGenerator;
import me.xdrop.jrand.utils.Choose;

import java.util.List;

@Facade(accessor = "phone")
public class PhoneGenerator extends Generator<String> {

    @PropertyFlag("Enable formatting the phone number")
    private boolean formatted;

    @PropertyFlag("Return a mobile number")
    private boolean mobile;

    @PropertyFlag("Return a landline number")
    private boolean landline;

    @PropertyFlag("Return a UK phone number")
    private boolean uk;

    @PropertyFlag("Return a US phone number")
    private boolean us;

    private StringGenerator string;
    private NaturalGenerator nat;
    private AreaCodeGenerator areacode;
    private String format;
    private List<String> prefixesUKLand;
    private List<String> prefixesUKMobile;

    public PhoneGenerator() {
        this.string = new StringGenerator();
        this.nat = new NaturalGenerator();
        this.areacode = new AreaCodeGenerator();
        this.prefixesUKLand = Assets.UK_PHONE_LAND.loadItems();
        this.prefixesUKMobile = Assets.UK_PHONE_MOBILE.loadItems();
    }

    /**
     * Specify a custom phone format. Use 'X' for a phone number,
     * any other characters will remain as is.
     * <p>
     * eg. XXXX-XXXX
     * might return 4521-9732
     *
     * @param format The string to format with
     * @return The same generator
     */
    public PhoneGenerator custom(String format) {
        this.format = format;
        return this;
    }

    private String generateCustom(String format) {
        StringBuilder sb = new StringBuilder(format.length());
        for (char c : format.toCharArray()) {
            if (c == 'X') {
                sb.append(string.gen());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    @Override
    public String gen() {
        if (format != null) {
            return generateCustom(format);
        }

        String number;

        if (uk) {
            String prefix;
            if (mobile) {
                prefix = Choose.one(prefixesUKMobile);
            } else if (landline) {
                prefix = Choose.one(prefixesUKLand);
            } else {
                prefix = Choose.one(prefixesUKMobile, prefixesUKLand);
            }
            int pad = 11 - prefix.length();
            return prefix + nat.range(0, 9).repeat(pad);

        } else if (us) {
            String area = areacode.gen();
            String exchange = nat.range(2, 9).genString() + nat.range(0, 9).repeat(2);
            String subscriber = nat.range(1000, 9999).genString();
            number = areacode + " " + exchange + "-" + subscriber;
        }
        return number;
    }
}
