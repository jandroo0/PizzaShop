package gui;

import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class EmployeeLoginPanel extends JPanel {
    private LoginListener employeeLoginListener; // event listener on login event

    private final Label idLabel; // id label

    private final JPasswordField idField; // id text field

    private final Button submitButton; // form submit button

    public EmployeeLoginPanel() {
        idLabel = new Label("EMPLOYEE ID", 24); // set text for idLabel

        idField = new JPasswordField(); // idField

        submitButton = new Button("LOGIN", Utils.getTextFont(20), Utils.getTextColor(), Utils.getButtonBackgroundColor(),
                Utils.getButtonHoverColor(), Utils.getButtonBorder()); // submit button

        submitButton.addActionListener(new ActionListener() { // submit button action listener, runs when submit button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                if (EmployeeLoginPanel.this.idField != null) { // if something is in the textfield
                    String ID = EmployeeLoginPanel.this.idField.getText(); // on submit, take idField contents
                    EmployeeLoginPanel.this.idField.setText("");
                    LoginEvent event = new LoginEvent(e, ID); // create an employee login event with the ID
                    if (EmployeeLoginPanel.this.employeeLoginListener != null) { // if there is a loginListener
                        try {
                            EmployeeLoginPanel.this.employeeLoginListener.loginEvent(event);
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }
            }
        });

        layoutComponents();
        styling();
    }

    public void setEmployeeLoginListener(LoginListener listener) {
        this.employeeLoginListener = listener;
    }


    // component styling
    void styling() {
        setBackground(Utils.getBackgroundColor());

        // idField
        idField.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // set  border to empty
        idField.setDocument(new JTextFieldLimit(6)); // using gui.JTextFieldLimit class set character limit to 6
        idField.setPreferredSize(new Dimension(114, 44)); // set  size
        idField.setHorizontalAlignment(JTextField.CENTER); // center text
        idField.setFont(Utils.getTextFont()); // set font


        idField.setForeground(Utils.getTextColor());
        idField.setBackground(Utils.getDefaultTextFieldColor());

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

    }

}
