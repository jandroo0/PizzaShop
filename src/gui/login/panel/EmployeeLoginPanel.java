package gui.login.panel;

import gui.tools.Button;
import gui.config.Utils;
import gui.login.event.LoginEvent;
import gui.login.listener.LoginListener;
import gui.tools.Label;
import gui.tools.MyIntFilter;
import gui.tools.PasswordField;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class EmployeeLoginPanel extends JPanel {
    private final Label idLabel; // id label
    private final PasswordField idField; // id text field
    private final Button submitButton; // form submit button
    private LoginListener employeeLoginListener; // event listener on login event

    public EmployeeLoginPanel() {
        idLabel = new Label("EMPLOYEE ID", 24); // set text for idLabel

        idField = new PasswordField(144, 44, 28); // idField

        submitButton = new Button("LOGIN", Utils.getTextFont(20), Utils.getTextColor(), Utils.getButtonBackgroundColor(),
                Utils.getButtonHoverColor(), Utils.getButtonBorder()); // submit button

        submitButton.addActionListener(new ActionListener() { // submit button action listener, runs when submit button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                if (EmployeeLoginPanel.this.idField.getPassword() != null) { // if something is in the textfield
                    String ID = EmployeeLoginPanel.this.idField.getText(); // on submit, take idField contents

                    LoginEvent event = new LoginEvent(e, ID); // create an employee login event with the ID
                    if (EmployeeLoginPanel.this.employeeLoginListener != null) { // if there is a loginListener
                        try {
                            EmployeeLoginPanel.this.employeeLoginListener.loginEvent(event);

                            idField.getDocument().remove(0, ID.length()); // clear textField doc after submit
                        } catch (ParseException | BadLocationException | IOException ex) {
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

        PlainDocument doc = (PlainDocument) idField.getDocument();
        doc.setDocumentFilter(new MyIntFilter(6));


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
