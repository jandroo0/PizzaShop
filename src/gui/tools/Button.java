package gui.tools;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton {


    public Button(String text, Font font, Color textColor, Color backgroundColor, Color hoverColor, Border borderType) {
        super(text);

        setFont(font);
        setBackground(backgroundColor);
        setForeground(textColor);
        setBorder(borderType);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                setForeground(backgroundColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setBackground(backgroundColor);
                setForeground(textColor);
            }
        });
    }

    @Override
    public String getText() {
        return super.getText();
    }
}
