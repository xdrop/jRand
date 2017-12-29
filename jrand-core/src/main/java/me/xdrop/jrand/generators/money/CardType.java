package me.xdrop.jrand.generators.money;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum CardType {


    VISA(Collections.singletonList(IINRange.from(4)),
            "Visa",
            "Visa",
            16),

    VISA_ELECTRON(Arrays.asList(IINRange.from(4026),
            IINRange.from(417500),
            IINRange.from(4508),
            IINRange.from(4844),
            IINRange.from(4913),
            IINRange.from(4917)),
            "Visa",
            "Visa Electron",
            16),

    MASTERCARD(Collections.singletonList(IINRange.from(51, 55)),
            "MC",
            "Mastercard",
            16),

    CHINA_UNIONPAY(Arrays.asList(
            IINRange.from(622126, 622925),
            IINRange.from(624, 626),
            IINRange.from(6282, 6288)),
            "CUP",
            "China UnionPay",
            16, 17, 18, 19),

    MAESTRO(Arrays.asList(
            IINRange.from(5018),
            IINRange.from(5020),
            IINRange.from(5038),
            IINRange.from(6304),
            IINRange.from(6759),
            IINRange.from(6761),
            IINRange.from(6763)),
            "Maes",
            "Maestro",
            12, 13, 14, 15, 16, 17, 18, 19),

    AMERICAN_EXPRESS(Arrays.asList(
            IINRange.from(34),
            IINRange.from(37)),
            "AmEx",
            "American Express",
            15),

    DISCOVER(Arrays.asList(
            IINRange.from(6011),
            IINRange.from(622126, 622925),
            IINRange.from(644, 649),
            IINRange.from(65)),
            "Disc",
            "Discover",
            16),

    JCB(Collections.singletonList(IINRange.from(3528, 3589)),
            "JCB",
            "JCB",
            16),

    DINERS_CLUB_CARTE_BLANCHE(Collections.singletonList(IINRange.from(300, 305)),
            "DC-CB",
            "Diners Club Carte Blanche",
            14),

    DINERS_CLUB_INTL(Collections.singletonList(IINRange.from(36)),
            "DC-Int",
            "Diners Club International",
            14),

    DINERS_CLUB_US_CAN(Arrays.asList(IINRange.from(54), IINRange.from(55)),
            "DC-UC",
            "Diners Club United States & Canada",
            16),

    INSTAPAYMENT(Collections.singletonList(IINRange.from(637, 639)),
            "IPI",
            "InstaPayment",
            16),

    LASER(Arrays.asList(IINRange.from(6304), IINRange.from(6706), IINRange.from(6771), IINRange.from(6709)),
            "Lasr",
            "Laser",
            16, 17, 18, 19),

    SOLO(Arrays.asList(IINRange.from(6334), IINRange.from(6767)),
            "Solo",
            "Solo",
            16, 18, 19),

    SWITCH(Arrays.asList(IINRange.from(4903),
            IINRange.from(4905),
            IINRange.from(4911),
            IINRange.from(4936),
            IINRange.from(564182),
            IINRange.from(633110),
            IINRange.from(6333),
            IINRange.from(6759)),
            "Swch",
            "Switch",
            16, 18, 19);


    private List<IINRange> iinRange;
    private int[] lengths;
    private String symbol;
    private String fullname;

    CardType(List<IINRange> iinRange, String symbol, String fullname, int... lengths) {
        this.iinRange = iinRange;
        this.symbol = symbol;
        this.fullname = fullname;
        this.lengths = lengths;
    }

    public List<IINRange> getIinRange() {
        return iinRange;
    }

    public int[] getLengths() {
        return lengths;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getFullname() {
        return fullname;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);

        sb.append(getFullname());
        sb.append(" [").append(getSymbol()).append("] ");
        sb.append("Prefixes: ");
        for (IINRange range : getIinRange()){
            sb.append(range.toString());
        }
        return sb.toString();
    }
}
