package gui;

import javax.swing.*;
import java.awt.*;

public class NewCustomerDialog extends JDialog {


    // panels
    private JPanel titlePanel, userInfoPanel, paymentInfoPanel, buttonPanel;

    private JLabel titleLabel; // title label

    // user info labels
    private JLabel firstNameLabel, lastNameLabel, phoneNumberLabel, addressLabel, detailsLabel ;


    // user info fields
    private JTextField firstNameField, lastNameField, phoneNumberField, addressField, detailsField;


    // create account  button
    private JButton createAccountButton;

    public NewCustomerDialog(Frame frame) {
        super(frame, "Create Account", true);
        setSize(600,800);
        setResizable(false);
        setLocationRelativeTo(frame);

        // panels
        titlePanel = new JPanel();
        userInfoPanel = new JPanel();
        paymentInfoPanel = new JPanel();
        buttonPanel = new JPanel();

        // title label
        titleLabel = new JLabel("CREATE ACCOUNT");

        //user info labels
        firstNameLabel = new JLabel("FIRST NAME");
        lastNameLabel = new JLabel("LAST NAME");
        phoneNumberLabel = new JLabel("PHONE");
        addressLabel = new JLabel("ADDRESS");
        detailsLabel = new JLabel("OTHER DETAILS");


        //user info textfields
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        phoneNumberField = new JTextField();
        addressField = new JTextField();
        detailsField = new JTextField();










    }
}
