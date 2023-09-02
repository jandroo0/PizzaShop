package gui;

import java.util.EventObject;


/** Event object for the employee/customer login event which will take the given ID in the JTextField on the login panel **/
public class LoginEvent extends EventObject {

    private int ID;
    public LoginEvent(Object source, int ID) {
        super(source);
        this.ID = ID;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
