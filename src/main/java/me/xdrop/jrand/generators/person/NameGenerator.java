package me.xdrop.jrand.generators.person;

import me.xdrop.jrand.Generator;

public class NameGenerator extends Generator<String> {

    private LastnameGenerator last;
    private FirstnameGenerator first;
    private boolean reverseOrder;
    private boolean withMiddleName;
    private String separator = " ";
    private boolean asCardName;

    public NameGenerator() {
        this.last = new LastnameGenerator();
        this.first = new FirstnameGenerator();
    }

    /**
     * Set an option to print lastname first then the surname
     *
     * @return The same generator
     */
    public NameGenerator reverseOrder() {
        this.reverseOrder = true;
        return this;
    }

    /**
     * Set an option to add a middlename
     *
     * @return The same generator
     */
    public NameGenerator withMiddleName() {
        this.withMiddleName = true;
        return this;
    }

    /**
     * Set the separator joining the name and the surname
     *
     * @param sep The separator to use as a string
     * @return The same generator
     */
    public NameGenerator separator(String sep) {
        this.separator = sep;
        return this;
    }

    /**
     * Formats the name as the uppercase shortened names
     * typically found on cards
     *
     * @return The same generator
     */
    public NameGenerator cardName() {
        this.asCardName = true;
        return this;
    }

    @Override
    public String gen() {
        String lastName = last.gen();
        String firstName = first.gen();
        String name;

        if (withMiddleName) {
            if (reverseOrder) {
                name = firstName + separator + last.gen() + separator + lastName;
            } else {
                name = lastName + separator + last.gen() + separator + firstName;
            }
        } else {
            if (reverseOrder) {
                name = lastName + separator + firstName;
            } else {
                name = firstName + separator + lastName;
            }
        }

        if (asCardName) {
            name = formatAsCardName(name);
        }

        return name;

    }

    private String formatAsCardName(String name) {
        StringBuilder cardName = new StringBuilder(32);
        name = name.toUpperCase();
        String[] parts = name.split(" ");
        int i = 0;
        for (String part : parts) {
            if ((part.length() > 6 || name.length() > 16) && i != parts.length - 1) {
                cardName.append(part.substring(0, 1));
                cardName.append(".");
            } else {
                cardName.append(part);
            }
            cardName.append(" ");
            i++;
        }

        name = cardName.toString();
        return name;
    }
}
