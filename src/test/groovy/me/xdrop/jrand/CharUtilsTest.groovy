package me.xdrop.jrand

class CharUtilsTest extends GroovyTestCase {
    void testCapitalize() {
        assertTrue CharUtils.capitalize("hey") == "Hey"
        assertTrue CharUtils.capitalize("") == ""
    }
}
