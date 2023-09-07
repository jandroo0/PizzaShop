package gui;

import javax.swing.*;
import java.awt.*;

public class PasswordField extends JPasswordField {

    public PasswordField(int width, int height, int fontSize) {

        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createLineBorder(Utils.getTextColor()));

        setHorizontalAlignment(JTextField.CENTER);
        setForeground(Utils.getTextColor());
        setBackground(Utils.getDefaultTextFieldColor());
        setFont(Utils.getTextFont(fontSize)); // set font with size

        setEchoChar('*');

    }
}
