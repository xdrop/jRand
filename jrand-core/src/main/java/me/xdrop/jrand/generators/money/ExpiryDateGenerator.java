package me.xdrop.jrand.generators.money;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.generators.basics.NaturalGenerator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Facade(accessor = "expiryDate")
public class ExpiryDateGenerator extends Generator<String>{
    private boolean longVersion;
    private boolean expired;
    private boolean canExpire;

    private NaturalGenerator nat;

    public ExpiryDateGenerator() {
        this.nat = new NaturalGenerator();
        longVersion = false;
        expired = false;
        canExpire = false;
    }


    /**
     * Return the expiry date as MM/YYYY
     * @return The same generator
     */
    public ExpiryDateGenerator longVersion() {
        return longVersion(true);
    }

    /**
     * Return the expiry date as MM/YYYY
     * @param longVersion True for MM/YYYY,
     *                    False for MM/YY
     * @return The same generator
     */
    public ExpiryDateGenerator longVersion(boolean longVersion) {
        this.longVersion = longVersion;
        return this;
    }

    /**
     * Set whether this can generate a past expiry date card
     * @param enabled True for enabled,
     *                False otherwise
     * @return The same generator
     */
    public ExpiryDateGenerator canExpire(boolean enabled) {
        this.canExpire = enabled;
        return this;
    }

    /**
     * Generate an expired card
     * @return The same generator
     */
    public ExpiryDateGenerator expired() {
        return expired(true);
    }

    /**
     * Generate an expired card
     * @param enabled True for enabled,
     *                False otherwise
     * @return The same generator
     */
    public ExpiryDateGenerator expired(boolean enabled) {
        this.expired = enabled;
        return this;
    }

    @Override
    public String gen() {
        DateTime now = new DateTime();
        if (expired) {
            now = now.minusYears(nat.range(1, 2).gen());
        } else if (canExpire) {
            now = now.minusYears(nat.range(-5,5).gen());
        } else {
            now = now.plusYears(nat.range(1,5).gen());
        }
        now = now.minusMonths(nat.range(0,11).gen());
        return formatDate(now, longVersion);
    }

    protected static String formatDate(DateTime now, boolean longVersion) {
        if (longVersion) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/yyyy");
            return formatter.print(now);
        } else {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/yy");
            return formatter.print(now);
        }
    }
}
