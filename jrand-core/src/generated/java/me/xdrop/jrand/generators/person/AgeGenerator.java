package me.xdrop.jrand.generators.person;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.generators.basics.NaturalGenerator;
import me.xdrop.jrand.model.person.PersonType;
import javax.annotation.Generated;

@Facade(accessor = "age")
public class AgeGenerator extends Generator<Integer> {

    private PersonType personType;
    private NaturalGenerator natGen;


    public AgeGenerator() {
        this.natGen = new NaturalGenerator();
        this.personType = PersonType.GENERIC;
    }

    public AgeGenerator child() {
        this.personType = PersonType.CHILD;
        return this;
    }

    public AgeGenerator adult() {
        this.personType = PersonType.ADULT;
        return this;
    }

    public AgeGenerator teen() {
        this.personType = PersonType.TEEN;
        return this;
    }

    public AgeGenerator senior() {
        this.personType = PersonType.SENIOR;
        return this;
    }

    public AgeGenerator personType(PersonType type) {
        this.personType = type;
        return this;
    }


    @Override
    public Integer gen() {
        return natGen.range(this.personType.getMin(), this.personType.getMax()).gen();
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    public final AgeGenerator fork() {
        return new AgeGenerator(
                personType,
                natGen.fork());
    }
    
    @Generated("me.xdrop.jrand.annotation.processing.ForkClassGenerator")
    private AgeGenerator(PersonType personType, NaturalGenerator natGen) {
        this.personType = personType;
        this.natGen = natGen;
    }


}
