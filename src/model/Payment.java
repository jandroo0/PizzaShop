package model;

public class Payment {

    protected String ID;
    protected boolean firstPayment = true;
    private int paymentCounter;

    public Payment(String ID) {
        this.ID = ID;
        paymentCounter = 1;
    }

    public String getPaymentID() {
        return ID + "_" + paymentCounter;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isFirstPayment() {
        return firstPayment;
    }

    public void setFirstPayment(boolean firstPayment) {
        this.firstPayment = firstPayment;
    }

    public int getPaymentCounter() {
        return paymentCounter;
    }

    public void setPaymentCounter(int paymentCounter) {
        this.paymentCounter = paymentCounter;
    }
}
