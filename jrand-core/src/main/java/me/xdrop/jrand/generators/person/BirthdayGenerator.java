package me.xdrop.jrand.generators.person;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.model.person.PersonType;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

@Facade(accessor = "birthday")
public class BirthdayGenerator extends Generator<Date> {

    protected PersonType personType;
    protected String format;
    protected final String defaultFormat= "dd/M/yy";

    public BirthdayGenerator() {
        this.format = defaultFormat;
        this.personType = PersonType.GENERIC;
    }

    /**
     * Set the person type for which to generate the
     * birthday
     * @param type The person type
     * @return The same generator
     */
    public BirthdayGenerator type(PersonType type){
        this.personType = type;
        return this;
    }

    /**
     * Generate the date in US format (M/dd/yy)
     * @param american True for US,
     *                 False for default
     * @return The same generator
     */
    public BirthdayGenerator american(boolean american) {
        if (!american) {
            this.format = defaultFormat;
        } else {
            this.format = "M/dd/yy";
        }
        return this;
    }

    /**
     * Generate the date in US format (M/dd/yy)
     * @return The same generator
     */
    public BirthdayGenerator american() {
        return american(true);
    }

    /**
     * Generate birthday for a child
     * @return The same generator
     */
    public BirthdayGenerator child() {
        this.personType = PersonType.CHILD;
        return this;
    }

    /**
     * Generate birthday for an adult
     * @return The same generator
     */
    public BirthdayGenerator adult() {
        this.personType = PersonType.ADULT;
        return this;
    }

    /**
     * Generate birthday for a teen
     * @return The same generator
     */
    public BirthdayGenerator teen() {
        this.personType = PersonType.TEEN;
        return this;
    }

    /**
     * Generate birthday for a senior
     * @return The same generator
     */
    public BirthdayGenerator senior() {
        this.personType = PersonType.SENIOR;
        return this;
    }

    /**
     * Specify a custom date format
     * @param format The custom date format {@link DateTimeFormatter} as a string
     * @return The same generator
     */
    public BirthdayGenerator format(String format) {
        this.format = format;
        return this;
    }

    private long getRandomTimeBetweenTwoDates (long endTime, long beginTime) {
        long diff = endTime - beginTime + 1;
        return beginTime + (long) (random().randDouble() * diff);
    }

    @Override
    public Date gen() {
        DateTime birthday = getDateTime();
        return birthday.toDate();
    }

    /**
     * Generate as Joda Time {@link DateTime}
     * @return Joda time DateTime class
     */
    public DateTime getDateTime() {
        DateTime now = new DateTime();
        int age = new AgeGenerator().personType(personType).gen();
        int dobYear = now.minusYears(age).getYear();
        DateTime yearBegin = new DateTime(dobYear, DateTimeConstants.JANUARY, 1, 0,0);

        long mills = getRandomTimeBetweenTwoDates(now.minusYears(age).getMillis(), yearBegin.getMillis());
        return new DateTime(mills);
    }

    @Override
    public String genString() {
        DateTimeFormatter format = DateTimeFormat.forPattern(this.format);
        return format.print(getDateTime());
    }
}
