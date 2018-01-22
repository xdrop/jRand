package me.xdrop.jrand.generators.money;

import me.xdrop.jrand.Generator;
import me.xdrop.jrand.annotation.Facade;
import me.xdrop.jrand.model.money.CardType;
import me.xdrop.jrand.utils.Choose;

import java.util.*;

@Facade(accessor = "cardType")
public class CardTypeGenerator extends Generator<CardType> {

    private static Map<String, CardType> all;
    private static CardType[] allCol;
    private static Map<Integer, String> defaultFormats;

    static {
        all = new HashMap<>();
        all.put("AMEX", CardType.AMERICAN_EXPRESS);
        all.put("AMERICAN EXPRESS", CardType.AMERICAN_EXPRESS);
        all.put("VISA", CardType.VISA);
        all.put("ELECTRON", CardType.VISA_ELECTRON);
        all.put("VISA ELECTRON", CardType.VISA_ELECTRON);
        all.put("MASTERCARD", CardType.MASTERCARD);
        all.put("MC", CardType.MASTERCARD);
        all.put("CHINA UNIONPAY", CardType.CHINA_UNIONPAY);
        all.put("CUP", CardType.CHINA_UNIONPAY);
        all.put("MAES", CardType.MAESTRO);
        all.put("MAESTRO", CardType.MAESTRO);
        all.put("DISCOVER", CardType.DISCOVER);
        all.put("DISC", CardType.DISCOVER);
        all.put("DC-CB", CardType.DINERS_CLUB_CARTE_BLANCHE);
        all.put("DC-INT", CardType.DINERS_CLUB_INTL);
        all.put("DC-UC", CardType.DINERS_CLUB_US_CAN);
        all.put("JCB", CardType.JCB);
        all.put("INSTAPAYMENT", CardType.INSTAPAYMENT);
        all.put("IPI", CardType.INSTAPAYMENT);
        all.put("LASER", CardType.LASER);
        all.put("LASR", CardType.LASER);
        all.put("SOLO", CardType.SOLO);
        all.put("SWCH", CardType.SWITCH);
        all.put("SWITCH", CardType.SWITCH);
        defaultFormats = new HashMap<>();
        defaultFormats.put(16, "XXXX XXXX XXXX XXXX");
        defaultFormats.put(15, "XXXX XXXXXX XXXXX");
        allCol = CardType.values();
    }

    private List<CardType> customPool;

    public CardTypeGenerator() {
        this.customPool = new ArrayList<>();
    }

    /**
     * Static helper to return a card type by its name
     * @param name Short or full version of cards name
     * @return The card type
     */
    public static CardType getTypeByName(String name) {
        return all.get(name.toUpperCase());
    }

    /**
     * Return all card number types
     * @return Array of all card number types
     */
    public static CardType[] allTypes() {
        return allCol;
    }

    /**
     * Return the default format string (if any)
     * depending on the card number size provided.
     *
     * Return null otherwise
     *
     * @param length The card number length
     * @return The format string
     */
    public static String defaultFormat(int length) {
        return defaultFormats.get(length);
    }

    /**
     * Return only from Amex,Discover,Visa, and Mastercard
     * @return The same generator
     */
    public CardTypeGenerator common() {
        return common(true);
    }

    /**
     * Return only from Amex,Discover,Visa, and Mastercard
     * @param enable True to return only common cards, false
     *               to return from all
     * @return The same generator
     */
    public CardTypeGenerator common(boolean enable) {
        if (enable) {
            customPool.add(CardType.AMERICAN_EXPRESS);
            customPool.add(CardType.DISCOVER);
            customPool.add(CardType.VISA);
            customPool.add(CardType.MASTERCARD);
        } else {
            customPool.clear();
        }
        return this;
    }

    /**
     * Specify a custom pool of cards from which to select
     * from.
     *
     * @param names The names of the cards to choose from
     * @return The same generator
     */
    public CardTypeGenerator only(String... names) {
        this.customPool = new ArrayList<>();
        for (String s : names) {
            CardType cc = getTypeByName(s);
            if (cc != null) {
                this.customPool.add(cc);
            }
        }
        return this;
    }

    /**
     * Specify a custom pool of cards from which to select
     * from.
     *
     * @param cardTypes The cardTypes of the cards to choose from
     * @return The same generator
     */
    public CardTypeGenerator only(CardType... cardTypes) {
        this.customPool = new ArrayList<>(Arrays.asList(cardTypes));
        return this;
    }

    @Override
    public CardType gen() {
        if (customPool.size() != 0) {
            return Choose.one(customPool);
        }
        return Choose.one(allCol);
    }


}
