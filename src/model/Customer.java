package model;

public class Customer {

    private String ID, phoneNumber, firstName, lastName, address, details;

    public Customer(String phoneNumber, String firstName, String lastName, String address, String details) {
        this.ID = phoneNumber;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.details = details;
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

}
