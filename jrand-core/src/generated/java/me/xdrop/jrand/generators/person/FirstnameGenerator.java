package me.xdrop.jrand.generators.person;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.data.AssetLoader;
import me.xdrop.jrand.model.person.Gender;
import me.xdrop.jrand.utils.Choose;

import java.util.List;
import javax.annotation.Generated;

@Facade(accessor = "firstname")
public class FirstnameGenerator extends Generator<String>{
    private List<String> maleNames;
    private List<String> femaleNames;
    private Gender gender;
    private GenderGenerator genderGen;

    public FirstnameGenerator() {
        this.maleNames = AssetLoader.loadList("male/firstnames.txt");
        this.femaleNames = AssetLoader.loadList("female/firstnames.txt");
        this.genderGen = new GenderGenerator();
    }

    /**
     * Set the gender
     * @param gender The gender as a {@link Gender} type
     * @return The same generator
     */
    public FirstnameGenerator gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    /**
     * Set the gender
     * @param gender The gender as a string,
     *               m for male, f for female
     * @return The same generator
     */
    public FirstnameGenerator gender(String gender) {
        if (gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("male")) {
            this.gender = Gender.MALE;
        } else if (gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("female")) {
            this.gender = Gender.FEMALE;
        }
        return this;
    }

    @Override
    public String gen() {
        Gender _gender;
        if (gender == null){
            _gender = genderGen.genAsGender();
        } else {
            _gender = gender;
        }

        if (_gender == Gender.MALE) {
            return Choose.one(maleNames);
        } else {
            return Choose.one(femaleNames);
        }
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    public final FirstnameGenerator fork() {
        return new FirstnameGenerator(
                new java.util.ArrayList<>(maleNames),
                new java.util.ArrayList<>(femaleNames),
                gender,
                genderGen.fork());
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    private FirstnameGenerator(List<String> maleNames, List<String> femaleNames, Gender gender,
            GenderGenerator genderGen) {
        this.maleNames = maleNames;
        this.femaleNames = femaleNames;
        this.gender = gender;
        this.genderGen = genderGen;
    }
}
