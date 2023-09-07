package gui.login.createAccount;

import gui.config.Utils;
import gui.login.createAccount.event.CreateAccountEvent;
import gui.login.createAccount.listener.CreateAccountListener;
import gui.tools.*;
import gui.tools.Button;
import gui.tools.Label;
import model.CardPayment;
import model.Payment;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class NewCustomerPanel extends JPanel {

    // panels
    private final JPanel titlePanel;
    private final JPanel infoPanels;
    private final JPanel userInfoPanel;
    private final JPanel buttonPanel;
    private final Label titleLabel; // title label

    // user info fields
    private final PlaceholderTextField firstNameField;
    private final PlaceholderTextField lastNameField;
    private final PlaceholderTextField phoneNumberField;
    private final PlaceholderTextField addressField;
    private final PlaceholderTextField detailsField;

    // payment info fields
    private final PlaceholderTextField cardNameField;
    private final PlaceholderTextField cardNumberField;
    private final PlaceholderTextField cardExpDateField;
    private final PlaceholderTextField CVCField;
    // create account  button
    private final Button createAccountButton;
    // cancel/return button
    private final Button cancelButton;
    // add payment checkBox
    private JCheckBox addPaymentBox;
    private CreateAccountListener createAccountListener;
    private JPanel paymentInfoPanel;

    public NewCustomerPanel(Frame frame) {

        // panels
        titlePanel = new JPanel();

        infoPanels = new JPanel();
        userInfoPanel = new JPanel();
        paymentInfoPanel = new JPanel();

        buttonPanel = new JPanel();

        // title label
        titleLabel = new Label("CREATE ACCOUNT", 26);


        //user info fields
        firstNameField = new PlaceholderTextField(" First", 140, 30, 20);
        lastNameField = new PlaceholderTextField(" Last", 140, 30, 20);
        phoneNumberField = new PlaceholderTextField(" Phone", 140, 30, 20,true,10);
        addressField = new PlaceholderTextField(" Address", 140, 30, 20);
        detailsField = new PlaceholderTextField(" Other", 140, 30, 20,30);

        //payment info fields
        cardNameField = new PlaceholderTextField(" Name on Card ", 140, 30, 20);
        cardNumberField = new PlaceholderTextField(" Number", 140, 30, 20,true,20);
        cardExpDateField = new PlaceholderTextField(" Exp Date 00/00 ", 140, 30, 20);
        CVCField = new PlaceholderTextField(" CVC", 140, 30, 20,true,3);


        addPaymentBox = new JCheckBox("Add Payment");

        // create account button
        createAccountButton = new Button("REGISTER", Utils.getTextFont(20), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        // cancel button
        cancelButton = new Button("CANCEL", Utils.getTextFont(20), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));


        // create account action
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!fieldsEmpty()) { // if textFields are not empty and do not still equal the placeholders
                    String details;
                    // in case details field is left empty, however since there is a placeHolder text -- "Other Details" , it needs to check in order to send the proper info
                    if (detailsField.getText().equals(" Other Info")) {
                        details = ""; // if detailsField still = Other Details
                    } else details = detailsField.getText();

                    CreateAccountEvent event;
                    if(addPaymentBox.isSelected()) {
                        Payment newPayment = new CardPayment(phoneNumberField.getText(), cardNameField.getText(), cardNumberField.getText(),
                                cardExpDateField.getText(), CVCField.getText());
                        event = new CreateAccountEvent(e, phoneNumberField.getText(), firstNameField.getText(),
                                lastNameField.getText(), addressField.getText(), details, newPayment);
                    } else {
                        event = new CreateAccountEvent(e, phoneNumberField.getText(), firstNameField.getText(),
                                lastNameField.getText(), addressField.getText(), details, null);
                    }

                    try {
                        clearFields();
                        NewCustomerPanel.this.createAccountListener.createAccount(event);
                    } catch (IOException | BadLocationException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Info fields can not be left empty", "Empty Fields", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clearFields();
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                NewCustomerPanel.this.createAccountListener.cancelEvent();

            }
        });

        switchPaymentFields();


        addPaymentBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                styling();
            }
        });

        layoutComponents();
        styling();


        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(infoPanels, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void layoutComponents() {
        GridBagConstraints gc = new GridBagConstraints();

        // title panel
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        titlePanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);
        titlePanel.add(titleLabel, gc);
        gc.gridy++;
        titlePanel.add(addPaymentBox, gc);

        //info panels
        infoPanels.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        infoPanels.setLayout(new GridLayout(0, 1));

        infoPanels.add(userInfoPanel);


        //user info panel
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        userInfoPanel.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);
        userInfoPanel.add(firstNameField, gc);
        gc.gridy++;
        userInfoPanel.add(lastNameField, gc);
        gc.gridy++;
        userInfoPanel.add(phoneNumberField, gc);
        gc.gridy++;
        userInfoPanel.add(addressField, gc);
        gc.gridy++;
        userInfoPanel.add(detailsField, gc);


        //payment info panel
        paymentInfoPanel.setBorder(BorderFactory.createCompoundBorder());

        paymentInfoPanel.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);
        paymentInfoPanel.add(cardNameField, gc);
        gc.gridy++;
        paymentInfoPanel.add(cardNumberField, gc);
        gc.gridy++;
        paymentInfoPanel.add(cardExpDateField, gc);
        gc.gridy++;
        paymentInfoPanel.add(CVCField, gc);


        //button panel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        buttonPanel.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.gridy = 0;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 10, 10);
        buttonPanel.add(cancelButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 10, 0);
        buttonPanel.add(createAccountButton, gc);



        // check box hover effect
        addPaymentBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                addPaymentBox.setForeground(Utils.getButtonBackgroundColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (!addPaymentBox.isSelected()) {
                    addPaymentBox.setForeground(Utils.getTextColor());
                }
            }


        });


    }

    private void styling() {
        setBackground(Utils.getBackgroundColor());
        infoPanels.setBackground(Utils.getBackgroundColor());

        // titlePanel
        titlePanel.setBackground(Utils.getBackgroundColor());

        // info panels
        userInfoPanel.setBackground(Utils.getBackgroundColor());
        paymentInfoPanel.setBackground(Utils.getBackgroundColor());

        // add payment checkbox
        addPaymentBox.setBackground(Utils.getBackgroundColor());
        addPaymentBox.setBorder(BorderFactory.createEmptyBorder());
        addPaymentBox.setFont(Utils.getTextFont(18));


        //button panel
        buttonPanel.setBackground(Utils.getBackgroundColor());

        switchPaymentFields();
    }

    // for each placeholder textField, check whether it still contains the placeholder, or nothing
    private boolean fieldsEmpty() {
        if (addPaymentBox.isSelected()) {
            if (cardNameField.getText().equals("")) {
                return true;
            } else if (cardNumberField.getText().equals("")) {
                return true;
            } else if (cardExpDateField.getText().equals("")) {
                return true;
            } else return CVCField.getText().equals("");
        }

        if (firstNameField.getText().equals("")) {
            return true;
        } else if (lastNameField.getText().equals("")) {
            return true;
        } else if (phoneNumberField.getText().equals("")) {
            return true;
        } else return addressField.getText().equals("");
    }

    private void switchPaymentFields() {
        if (addPaymentBox.isSelected()) {
            infoPanels.add(paymentInfoPanel);
        } else infoPanels.remove(paymentInfoPanel);

    }

    // clear textFields
    public void clearFields() throws BadLocationException {
        firstNameField.setText("");
        lastNameField.setText("");
        phoneNumberField.getDocument().remove(0, phoneNumberField.getText().length());
        addressField.setText("");
        detailsField.setText("");

        cardNameField.setText("");
        cardNumberField.getDocument().remove(0, cardNumberField.getText().length());
        cardExpDateField.setText("");
        CVCField.getDocument().remove(0, CVCField.getText().length());

        addPaymentBox.setSelected(false);
        switchPaymentFields();
    }

    public void setCreateAccountListener(CreateAccountListener listener) {
        this.createAccountListener = listener;
    }

    public void setPhoneNumber(String phoneNumber) {
        phoneNumberField.setText(phoneNumber);

    }
}
