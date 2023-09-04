package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NewCustomerDialog extends JDialog {

    private CreateAccountListener createAccountListener;


    // panels
    private JPanel titlePanel;
    private JPanel infoPanels, userInfoPanel, paymentInfoPanel;
    private JPanel buttonPanel;

    private JLabel titleLabel; // title label

    private JLabel userInfoTitleLabel;


    // user info fields
    private PlaceholderTextField firstNameField, lastNameField, phoneNumberField, addressField, detailsField;


    // create account  button
    private JButton createAccountButton;

    public NewCustomerDialog(Frame frame) {
        super(frame, "Create Account", true);
        setSize(300,400);
        setResizable(false);
        setLocationRelativeTo(frame);

        // panels
        titlePanel = new JPanel();

        infoPanels = new JPanel();
        userInfoPanel = new JPanel();
//        paymentInfoPanel = new JPanel();

        buttonPanel = new JPanel();

        // title label
        titleLabel = new JLabel("CREATE ACCOUNT");

        // user info panel title label
        userInfoTitleLabel = new JLabel("USER INFO");

        //user info fields
        firstNameField = new PlaceholderTextField("First Name");
        lastNameField = new PlaceholderTextField("Last Name");
        phoneNumberField = new PlaceholderTextField("Phone");
        addressField = new PlaceholderTextField("Address");
        detailsField = new PlaceholderTextField("Other Details");

        // create account button
        createAccountButton = new JButton("CREATE ACCOUNT");


        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkFields()) { // if textFields are not empty and do not still equal the placeholders
                    CreateAccountEvent event = new CreateAccountEvent(e, phoneNumberField.getText(),firstNameField.getText(),
                            lastNameField.getText(), addressField.getText(), detailsField.getText());
                    try {
                        NewCustomerDialog.this.createAccountListener.createAccount(event);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

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

        // title panel
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        titlePanel.add(titleLabel);

        //info panels
        infoPanels.setBorder(BorderFactory.createEmptyBorder());
        infoPanels.setLayout(new GridLayout(1,0));

        infoPanels.add(userInfoPanel);
//        infoPanels.add(paymentInfoPanel);


        //user info panel
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder());
        userInfoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0,0,20,0);
        userInfoPanel.add(userInfoTitleLabel,gc);
        gc.gridy++;
        gc.insets = new Insets(0,0,10,0);
        userInfoPanel.add(firstNameField, gc);
        gc.gridy++;
        userInfoPanel.add(lastNameField, gc);
        gc.gridy++;
        userInfoPanel.add(phoneNumberField, gc);
        gc.gridy++;
        userInfoPanel.add(addressField, gc);
        gc.gridy++;
        userInfoPanel.add(detailsField, gc);

        //button panel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,0,40,0));
        buttonPanel.setLayout(new GridBagLayout());
        gc.gridy = 0;
        gc.gridx = 0;
        buttonPanel.add(createAccountButton);

    }

    private void styling() {
        setBackground(Utils.getBackgroundColor());

        // titlePanel
        titlePanel.setBackground(Utils.getBackgroundColor());

        // info panels
        userInfoPanel.setBackground(Utils.getBackgroundColor());
//        paymentInfoPanel.setBackground(Utils.getBackgroundColor());

        //button panel
        buttonPanel.setBackground(Utils.getBackgroundColor());

        // create account button
        createAccountButton.setFont(new Font(Utils.getFontString(), Font.BOLD, 14));
        createAccountButton.setBackground(Utils.getButtonBackgroundColor());
        createAccountButton.setForeground(Utils.getTextColor());
        createAccountButton.setBorder(BorderFactory.createEmptyBorder(5,8,5,8));
    }

    private boolean checkFields() {
        if((firstNameField.getText().equals(firstNameField.getPlaceholder()) || firstNameField.getText().equals(""))) {
            return false;
        }else if((lastNameField.getText().equals(lastNameField.getPlaceholder()) || lastNameField.getText().equals(""))) {
            return false;
        } else if((phoneNumberField.getText().equals(phoneNumberField.getPlaceholder()) || phoneNumberField.getText().equals(""))) {
            return false;
        } else if((addressField.getText().equals(addressField.getPlaceholder()) || addressField.getText().equals(""))) {
            return false;
        } else if((detailsField.getText().equals(detailsField.getPlaceholder()) || detailsField.getText().equals(""))) {
            return false;
        }
        return true;
    }

    public void setCreateAccountListener(CreateAccountListener listener) {
        this.createAccountListener = listener;
    }
}
