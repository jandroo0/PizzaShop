package gui;

import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


public class CustomerLoginPanel extends JPanel {

    private final Label idLabel; // id label
    private final JTextField idField; // id text field
    private final JButton newCustomerButton; // new customer button
    private final Button submitButton; // form submit button
    private LoginListener loginListener; // event listener on login event
    private NewCustomerListener newCustomerListener; // event listener on login event

    public CustomerLoginPanel() {

        idLabel = new Label("PHONE NUMBER", 24); // set text for idLabel

        idField = new JTextField(); // idField

        submitButton = new Button("LOGIN", Utils.getTextFont(20), Utils.getTextColor(), Utils.getButtonBackgroundColor(),
                Utils.getButtonHoverColor(), Utils.getButtonBorder()); // submit button

        newCustomerButton = new JButton("NEW CUSTOMER?"); // new customer button


        // submit button action listener, runs when submit button is pressed
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CustomerLoginPanel.this.idField != null) { // if something is in the textfield
                    String ID = CustomerLoginPanel.this.idField.getText(); // on submit, take idField contents
                    CustomerLoginPanel.this.idField.setText("");
                    LoginEvent event = new LoginEvent(e, ID); // create an employee login event with the ID
                    if (CustomerLoginPanel.this.loginListener != null) { // if there is a loginListener
                        try {
                            CustomerLoginPanel.this.loginListener.loginEvent(event);
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }
            }
        });

        // new customer actionListener
        newCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerLoginPanel.this.newCustomerListener.newCustomerEvent();

            }
        });


        // mouse listener for hover effects
        newCustomerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                newCustomerButton.setForeground(Utils.getTextColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                newCustomerButton.setForeground(Color.WHITE);
            }
        });

        layoutComponents();
        styling();
    }

    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }

    public void setNewCustomerListener(NewCustomerListener listener) {
        this.newCustomerListener = listener;
    }

    // component styling
    void styling() {
        setBackground(Utils.getBackgroundColor());


        // idField
        idField.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // set  border to empty
        idField.setDocument(new JTextFieldLimit(10)); // using gui.JTextFieldLimit class set character limit to 6
        idField.setPreferredSize(new Dimension(140, 44)); // set  size
        idField.setHorizontalAlignment(JTextField.CENTER);
        idField.setFont(Utils.getTextFont()); // set font

        idField.setForeground(Utils.getTextColor());
        idField.setBackground(Utils.getDefaultTextFieldColor());

        // newCustomer button
        newCustomerButton.setFont(new Font(Utils.getFontString(), Font.BOLD, 14));
        newCustomerButton.setBackground(Utils.getBackgroundColor());
        newCustomerButton.setForeground(Color.WHITE);
        newCustomerButton.setBorder(Utils.getButtonBorder());


    }

    public void layoutComponents() {
        Border border = BorderFactory.createEmptyBorder(0, 10, 200, 10);
        this.setBorder(border);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(0, 0, 10, 0);
        gc.gridy = 0;
        gc.gridx = 0;
        add(idLabel, gc);
        gc.gridy++;
        add(idField, gc);
        gc.gridy++;
        add(submitButton, gc);
        gc.gridy++;
        add(newCustomerButton, gc);
    }

}
