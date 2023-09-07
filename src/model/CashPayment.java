package model;

public class CashPayment extends Payment {

    private float cashAmount;
    private float changeAmount;

    public CashPayment(String ID, float priceAmount, float cashAmount, float changeAmount) {
        super(ID);
        this.cashAmount = cashAmount;
        this.changeAmount = changeAmount;
    }


    public float getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(float cashAmount) {
        this.cashAmount = cashAmount;
    }


}
