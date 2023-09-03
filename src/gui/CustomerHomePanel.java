package gui;

import model.Customer;

import javax.swing.*;

public class CustomerHomePanel extends JPanel {

    private Customer currentCustomer;

    public CustomerHomePanel() {
        currentCustomer = null;

    }

    public Customer getCustomer() {
        return currentCustomer;
    }

    public void setCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }
}
