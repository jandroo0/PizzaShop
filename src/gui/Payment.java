package gui;

public class Payment {

    private String cardName, cardNumber, expDate, CVC;
    private String cashAmount;


    public Payment(String cardName, String cardNumber, String expDate, String CVC, String cashAmount) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.CVC = CVC;
        this.cashAmount = cashAmount;
    }

    public String getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(String cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "cardName='" + cardName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expDate='" + expDate + '\'' +
                ", CVC='" + CVC + '\'' +
                ", cashAmount='" + cashAmount + '\'' +
                '}';
    }
}
