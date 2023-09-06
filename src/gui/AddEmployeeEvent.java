package gui;

import java.util.EventObject;


/**
 * Event object for the employee/customer login event which will take the given ID in the JTextField on the login panel
 **/
public class AddEmployeeEvent extends EventObject {

    private String ID, firstName, lastName, age, address, role, phoneNumber;
    private boolean isAdmin;

    public AddEmployeeEvent(Object source, boolean isAdmin, String ID, String firstName, String lastName, String age, String role, String phoneNumber, String address) {
        super(source);
        this.isAdmin = isAdmin;
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
