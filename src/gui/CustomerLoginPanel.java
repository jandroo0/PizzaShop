package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CustomerLoginPanel extends JPanel{
    private LoginListener loginListener; // event listener on login event

    private JLabel idLabel; // id label

    private JTextField idField; // id text field

    private JButton submitButton; // form submit button

    public CustomerLoginPanel() {
        idLabel = new JLabel("PHONE NUMBER"); // set text for idLabel

        idField = new JTextField(); // idField

        submitButton = new JButton("LOGIN"); // submit button

        submitButton.addActionListener(new ActionListener() { // submit button action listener, runs when submit button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CustomerLoginPanel.this.idField != null) { // if something is in the textfield
                    String ID = CustomerLoginPanel.this.idField.getText(); // on submit, take idField contents
                    CustomerLoginPanel.this.idField.setText("");
                    LoginEvent event = new LoginEvent(e, ID); // create an employee login event with the ID
                    if (CustomerLoginPanel.this.loginListener != null) { // if there is a loginListener
                        CustomerLoginPanel.this.loginListener.loginEvent(event);

                    }
                }
            }
        });

        // on mouse hover over change colors
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                submitButton.setBackground(Utils.getButtonHoverColor());
                submitButton.setForeground(Utils.getButtonBackgroundColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                submitButton.setBackground(Utils.getButtonBackgroundColor());
                submitButton.setForeground(Utils.getTextColor());
            }
        });

        layoutComponents();
        styling();
    }

    public void setLoginListener(LoginListener listener){
        this.loginListener = listener;
    }


    // component styling
    void styling() {
        setBackground(Utils.getBackgroundColor());

        //idLabel
        idLabel.setFont(Utils.getLoginFont());
        idLabel.setForeground(Utils.getTextColor());


        // idField
        idField.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // set  border to empty
        idField.setDocument(new JTextFieldLimit(10)); // using gui.JTextFieldLimit class set character limit to 6
        idField.setPreferredSize(new Dimension(140,44)); // set  size
        idField.setHorizontalAlignment(JTextField.CENTER);
        idField.setFont(Utils.getLoginFont()); // set font

        idField.setForeground(Utils.getTextColor());
        idField.setBackground(Utils.getDefaultTextFieldColor());

        // submit/login button
        submitButton.setFont(new Font(Utils.getFontString(), Font.BOLD, 14));
        submitButton.setBackground(Utils.getButtonBackgroundColor());
        submitButton.setForeground(Utils.getTextColor());
        submitButton.setBorder(BorderFactory.createEmptyBorder(5,8,5,8));

    }

    public void layoutComponents() {
        Border border = BorderFactory.createEmptyBorder(0,10,200,10);
        this.setBorder(border);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(0,0,10,0);
        gc.gridy = 0;
        gc.gridx = 0;
        add(idLabel,gc);
        gc.gridy++;
        add(idField, gc);
        gc.gridy++;
        add(submitButton, gc);
    }

}
