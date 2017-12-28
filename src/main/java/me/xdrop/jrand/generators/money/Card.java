package me.xdrop.jrand.generators.money;

public class Card {
    private String cardNumber;
    private String cardType;
    private String cvv;
    private String expiryDate;
    private String issueDate;
    private String name;
    private String country;
    private String billingAddress;
    private String postcode;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return  "[Name]: " + name + "\n" +
                "[CardType]: " + cardType +"\n" +
                "[CardNo]: " + cardNumber + "\n" +
                "[Address]: " + billingAddress + "\n" +
                "[Postcode]: " + postcode + "\n" +
                "[Country]: " + country + "\n" +
                "[IssueDate]: " + issueDate + "\n" +
                "[ExpiryDate]: " + expiryDate + "\n" +
                "[CVV]: " + cvv+ "\n";
    }
}
