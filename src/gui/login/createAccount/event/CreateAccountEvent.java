package gui.login.createAccount.event;

import model.Payment;

import java.util.EventObject;

public class CreateAccountEvent extends EventObject {

    private String ID, phoneNumber, firstName, lastName, address, details;
    private Payment payment;

    public CreateAccountEvent(Object source, String phoneNumber, String firstName, String lastName, String address, String details, Payment payment) {
        super(source);
        this.ID = phoneNumber;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.details = details;

        this.payment = payment;
    }

    public String getPhoneNumber() {
        return ID;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getID() {
        return phoneNumber;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
