package gui.login.createAccount;

import gui.tools.Button;
import gui.config.Utils;
import gui.login.createAccount.event.CreateAccountEvent;
import gui.login.createAccount.listener.CreateAccountListener;
import gui.tools.Label;
import gui.tools.MyIntFilter;
import gui.tools.PlaceholderTextField;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NewCustomerPanel extends JPanel {

    // panels
    private final JPanel titlePanel;
    private final JPanel infoPanels;
    private final JPanel userInfoPanel;
    private final JPanel buttonPanel;
    private final Label titleLabel; // title label
    private final Label userInfoTitleLabel;
    // user info fields
    private final PlaceholderTextField firstNameField;
    private final PlaceholderTextField lastNameField;
    private final PlaceholderTextField phoneNumberField;
    private final PlaceholderTextField addressField;
    private final PlaceholderTextField detailsField;
    // create account  button
    private final Button createAccountButton;
    private CreateAccountListener createAccountListener;
    private JPanel paymentInfoPanel;

    public NewCustomerPanel(Frame frame) {

        // panels
        titlePanel = new JPanel();

        infoPanels = new JPanel();
        userInfoPanel = new JPanel();
//        paymentInfoPanel = new JPanel();

        buttonPanel = new JPanel();

        // title label
        titleLabel = new Label("CREATE ACCOUNT", 26);

        // user info panel title label
        userInfoTitleLabel = new Label("USER INFO", 22);

        //user info fields
        firstNameField = new PlaceholderTextField(" First", 140, 30, 20);
        lastNameField = new PlaceholderTextField(" Last", 140, 30, 20);
        phoneNumberField = new PlaceholderTextField(" Phone", 140, 30, 20);
        addressField = new PlaceholderTextField(" Address", 140, 30, 20);
        detailsField = new PlaceholderTextField(" Other", 140, 30, 20);

        // create account button
        createAccountButton = new Button("CREATE ACCOUNT", Utils.getTextFont(14), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));


        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFields()) { // if textFields are not empty and do not still equal the placeholders
                    String details;
                    // in case details field is left empty, however since there is a placeHolder text -- "Other Details" , it needs to check in order to send the proper info
                    if (detailsField.getText().equals(" Other Info")) {
                        details = ""; // if detailsField still = Other Details
                    } else details = detailsField.getText();

                    CreateAccountEvent event = new CreateAccountEvent(e, phoneNumberField.getText(), firstNameField.getText(),
                            lastNameField.getText(), addressField.getText(), details);
                    try {
                        NewCustomerPanel.this.createAccountListener.createAccount(event);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Info fields can not be left empty", "Empty Fields", JOptionPane.ERROR_MESSAGE);

                }

            }
        });

        layoutComponents();
        styling();


        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(infoPanels, BorderLayout.CENTER);
    }

    private void layoutComponents() {

        // title panel
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        titlePanel.add(titleLabel);

        //info panels
        infoPanels.setBorder(BorderFactory.createEmptyBorder());
        infoPanels.setLayout(new BoxLayout(infoPanels, BoxLayout.Y_AXIS));

        infoPanels.add(userInfoPanel);
//        infoPanels.add(paymentInfoPanel);


        //user info panel
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        userInfoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 20, 0);
        userInfoPanel.add(userInfoTitleLabel, gc);
        gc.gridy++;
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
        gc.gridy++;
        userInfoPanel.add(createAccountButton, gc);


//
//        //button panel
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
//        buttonPanel.setLayout(new GridBagLayout());
//        gc.gridy = 0;
//        gc.gridx = 0;


    }

    private void styling() {
        setBackground(Utils.getBackgroundColor());

        // titlePanel
        titlePanel.setBackground(Utils.getBackgroundColor());

        // info panels
        userInfoPanel.setBackground(Utils.getBackgroundColor());
//        paymentInfoPanel.setBackground(Utils.getBackgroundColor());

        // phone number field
        PlainDocument doc = (PlainDocument) phoneNumberField.getDocument();
        doc.setDocumentFilter(new MyIntFilter(10));


        //button panel
        buttonPanel.setBackground(Utils.getBackgroundColor());
    }

    // for each placeholder textField, check whether it still contains the placeholder, or nothing
    private boolean checkFields() {
        if (firstNameField.getText().equals("")) {
            return false;
        } else if (lastNameField.getText().equals("")) {
            return false;
        } else if (phoneNumberField.getText().equals("")) {
            return false;
        } else return !addressField.getText().equals("");
    }

    public void setCreateAccountListener(CreateAccountListener listener) {
        this.createAccountListener = listener;
    }

    public void setPhoneNumber(String phoneNumber) {
        phoneNumberField.setText(phoneNumber);
    }
}
