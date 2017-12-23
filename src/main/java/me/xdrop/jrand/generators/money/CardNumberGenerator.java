package me.xdrop.jrand.generators.money;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.generators.basics.NaturalGenerator;
import me.xdrop.jrand.generators.collections.ListRandUtils;

public class CardNumberGenerator extends Generator<String> {

    private NaturalGenerator nat;
    private CardTypeGenerator ctype;
    private CardType customCardType;
    private String formatString;
    private boolean useDefaultFormat;

    CardNumberGenerator() {
        nat = new NaturalGenerator();
        ctype = new CardTypeGenerator();
        customCardType = null;
    }

    /**
     * Calculate the last digit based on Luhn's algorithm for
     * validation.
     *
     * @param longNum The card number excluding the validation digit
     * @return The validation digit
     */
    public static int luhnCalculate(String longNum) {
        String[] digits = longNum.split("");
        long sum = 0;
        int digit;

        for (int i = 0, l = digits.length; i < l; ++i) {
            digit = Integer.parseInt(digits[i]);
            if ((i % 2) == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }

        return (int)((sum * 9) % 10);
    }

    /**
     * Specify the card type to generate
     *
     * @param cardType The cardtype to generate
     * @return The same generator
     */
    public CardNumberGenerator cardType(CardType cardType) {
        this.customCardType = cardType;
        return this;
    }

    /**
     * Specify the card type to generate via its name
     * @param cardType The name of the cardtype to generate
     * @return The same generator
     */
    public CardNumberGenerator cardType(String cardType) {
        return cardType(CardTypeGenerator.getTypeByName(cardType));
    }

    /**
     * Return the card number formatted (with spaces)
     * using the default formatting
     *
     * @return The same generator
     */
    public CardNumberGenerator format() {
        return format(true);
    }

    /**
     * Return the card number formatted (with spaces)
     * using the default formatting
     *
     * @param useDefault True for enable, false for disable
     * @return The same generator
     */
    public CardNumberGenerator format(boolean useDefault) {
        this.useDefaultFormat = useDefault;
        return this;
    }

    /**
     * Specify a custom string to format the card number.
     * Any instance of the character X in the string will
     * be replaced by a card digit while any instance of
     * '_' will obscure a digit by returning X instead
     *
     * For example: "XXXX XXXX XXXX XXXX"
     * will return "4567 8900 2334 2134"
     *
     * @param formatString The format string you wish to use
     *                     for example "XXXX XXXX XXXX XXXX"
     * @return The same generator
     */
    public CardNumberGenerator format(String formatString) {
        this.formatString = formatString;
        return this;
    }

    /**
     * Generate only card numbers from American Express,
     * Discover, Visa and Mastercard
     *
     * @return The same generator
     */
    public CardNumberGenerator common() {
        ctype.common();
        return this;
    }

    /**
     * Generate only card numbers from American Express,
     * Discover, Visa and Mastercard
     *
     * @param common True to return numbers from common cards,
     *               false to return from all
     * @return The same generator
     */
    public CardNumberGenerator common(boolean common) {
        ctype.common(common);
        return this;
    }

    /**
     * Specify a custom pool of cards from which to select
     * from.
     *
     * @param names The names of the cards to choose from
     * @return The same generator
     */
    public CardNumberGenerator only(String... names) {
        ctype.only(names);
        return this;
    }

    /**
     * Specify a custom pool of cards from which to select
     * from.
     *
     * @param cardTypes The cardTypes of the cards to choose from
     * @return The same generator
     */
    public CardNumberGenerator only(CardType... cardTypes) {
        ctype.only(cardTypes);
        return this;
    }

    private String generateCardNumber(CardType type) {
        IINRange iinRange = ListRandUtils.chooseOne(type.getIinRange());
        int length = ListRandUtils.chooseOne(type.getLengths());
        StringBuilder sb = new StringBuilder(length);
        String prefix;
        if (iinRange.getEnd() == -1) {
            prefix = "" + iinRange.getBegin();
        } else {
            prefix = "" + nat.range(iinRange.getBegin(), iinRange.getEnd()).gen();
        }

        sb.append(prefix);

        int remaining = length - prefix.length();

        while (remaining != 1) {
            sb.append(nat.range(0,9).gen());
            remaining--;
        }

        sb.append(luhnCalculate(sb.toString()));

        return sb.toString();
    }


    private String formatCard(String cardNo, String formatString) {
        StringBuilder sb = new StringBuilder(formatString.length());
        char[] card = cardNo.toCharArray();
        int i = 0;
        for (char c : formatString.toCharArray()) {
            if (c == 'X') {
                if (i < card.length) {
                    sb.append(card[i]);
                    i++;
                } else {
                    sb.append("X");
                }
            } else if (c == '_') {
                sb.append('X');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @Override
    public String gen() {
        CardType type;
        if (customCardType == null) {
            type = ctype.gen();
        } else {
            type = this.customCardType;
        }

        String cardNo = generateCardNumber(type);

        if (formatString != null) {
            return formatCard(cardNo, formatString);
        } else if (useDefaultFormat) {
            return formatCard(cardNo, CardTypeGenerator.defaultFormat(cardNo.length()));
        } else {
            return cardNo;
        }

    }
}
