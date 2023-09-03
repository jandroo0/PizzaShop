package model;

public class Employee {
    private String ID, firstName, lastName, age, role, address, phoneNumber;
    private boolean admin;

    public Employee(String ID, String firstName, String lastName, String age, String role, String phoneNumber, String address) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Employee(boolean admin, String ID, String firstName, String lastName, String age, String role, String phoneNumber, String address) {
        this.admin = admin;
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean isAdmin()  {
        return admin;
    }

    public void setAdmin() {
        this.admin = true;
    }

    public void revokeAdmin() {
        this.admin = false;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "Employee{" +
                "ID='" + ID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", role='" + role + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
