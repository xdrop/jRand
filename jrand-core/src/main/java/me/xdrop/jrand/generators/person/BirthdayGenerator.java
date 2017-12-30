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

    private PersonType personType;
    private String format;
    private final String defaultFormat= "dd/M/yy";

    public BirthdayGenerator() {
        this.format = defaultFormat;
        this.personType = PersonType.GENERIC;
    }

    public BirthdayGenerator type(PersonType type){
        this.personType = type;
        return this;
    }

    public BirthdayGenerator american(boolean american) {
        if (!american) {
            this.format = defaultFormat;
        } else {
            this.format = "M/dd/yy";
        }
        return this;
    }

    public BirthdayGenerator american() {
        return american(true);
    }

    public BirthdayGenerator child() {
        this.personType = PersonType.CHILD;
        return this;
    }

    public BirthdayGenerator adult() {
        this.personType = PersonType.ADULT;
        return this;
    }

    public BirthdayGenerator teen() {
        this.personType = PersonType.TEEN;
        return this;
    }

    public BirthdayGenerator senior() {
        this.personType = PersonType.SENIOR;
        return this;
    }

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

    public DateTime getDateTime() {
        DateTime now = new DateTime();
        int age = new AgeGenerator().personType(personType).gen();
        int dobYear = now.minusYears(age).getYear();
        DateTime yearBegin = new DateTime(dobYear, DateTimeConstants.JANUARY, 1, 0,0);
        Period offsetInCurrentYear = new Period(now, new DateTime(now.getYear(), DateTimeConstants.JANUARY, 1, 0, 0));
        DateTime nextYear  = yearBegin.plusYears(1).minusDays(1).plus(offsetInCurrentYear);

        long mills = getRandomTimeBetweenTwoDates(nextYear.getMillis(), yearBegin.getMillis());
        return new DateTime(mills);
    }

    @Override
    public String genString() {
        DateTimeFormatter format = DateTimeFormat.forPattern(this.format);
        return format.print(getDateTime());
    }
}
