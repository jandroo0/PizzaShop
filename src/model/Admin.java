package model;


//admin class which extends employee
public class Admin extends Employee{

    private boolean hasPerms;

    public Admin(String ID, String firstName, String lastName, String age, String role, String phoneNumber, String address) {
        super(ID, firstName, lastName, age, role, phoneNumber, address);

        hasPerms = true;
    }

    public boolean getHasPerms() {
        return hasPerms;
    }

    public void setHasPerms(boolean hasPerms) {
        this.hasPerms = hasPerms;
    }
}
