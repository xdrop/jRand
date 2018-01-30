package me.xdrop.jrand.generators.person

import me.xdrop.jrand.data.AssetLoader
import me.xdrop.jrand.data.Assets
import me.xdrop.jrand.model.person.Gender
import me.xdrop.jrand.model.person.Prefix
import me.xdrop.jrand.model.person.PrefixMapper

class PrefixGeneratorTest extends GroovyTestCase {
    def instance = {-> new PrefixGenerator()}
    def prefixesMale = Assets.MALE_PREFIXES.loadItems()
    def prefixesFemale = Assets.FEMALE_PREFIXES.loadItems()
    def prefixesNeutral = Assets.NEUTRAL_PREFIXES.loadItems()
    def all = new ArrayList<Prefix>(prefixesFemale + prefixesMale)



    void testFull() {
        assertTrue instance().full().gen() in all.collect {x -> x.full}
    }

    void testAll() {
        assertTrue instance().all().gen() in (all + prefixesNeutral).collect{x -> x.abbreviation}
    }

    void testGender() {
        assertTrue instance().gender("m").gen() in prefixesMale.collect({x-> x.abbreviation})
        assertTrue instance().gender(Gender.MALE).gen() in prefixesMale.collect({ x-> x.abbreviation})
        assertTrue instance().gender("f").gen() in prefixesFemale.collect({x-> x.abbreviation})
        assertTrue instance().gender(Gender.FEMALE).gen() in prefixesFemale.collect({ x-> x.abbreviation})
        assertTrue instance().gender("n").gen() in prefixesNeutral.collect({x-> x.abbreviation})
        assertTrue instance().gender(Gender.NEUTRAL).gen() in prefixesNeutral.collect({ x-> x.abbreviation})
    }

}
