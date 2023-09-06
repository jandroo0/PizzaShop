package gui;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {

    public Label(String text, int size) {
        super(text);

        setForeground(Utils.getTextColor());
        setFont(Utils.getTextFont(size));
    }

    public Label(String text, Color textColor, int size) {
        super(text);

        setForeground(textColor);
        setFont(Utils.getTextFont(size));
    }

    public Label(String text, Color textColor, Font font) {
        super(text);

        setForeground(textColor);
        setFont(font);
    }
}
