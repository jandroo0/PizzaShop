package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Customer {

    private String ID, phoneNumber, firstName, lastName, address, details;

    private LinkedList<Payment> payments;

    public Customer(String phoneNumber, String firstName, String lastName, String address, String details) {
        this.ID = phoneNumber;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.details = details;

        payments = new LinkedList<>();
    }

    public LinkedList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(LinkedList<Payment> payments) {
        this.payments = payments;
    }

    public String getPhoneNumber() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String generateID(Payment payment) {

        // if not the FIRST payment method for the matched customer
        if(payments.isEmpty()) {
            payment.setPaymentCounter(1); // set payment counter to 1
        }

        int numOfPayments = payments.indexOf(payment); // get index of current payment
        numOfPayments++;
        payment.setPaymentCounter(numOfPayments); //set new payment counter +1

        return payment.getPaymentID(); // return new paymentID i.e xxxxxxxxx_1, xxxxxxxx_2

    }

}
