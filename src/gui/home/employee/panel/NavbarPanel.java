package gui.home.employee.panel;

import gui.config.Utils;
import gui.tools.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NavbarPanel extends JPanel {

    private final List<Button> buttons;

    public NavbarPanel() {
        buttons = new ArrayList<>();
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.LIGHT_GRAY);
    }

    public void addButton(String text) {
        Button button = new Button(text, Utils.getTextFont(18), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(3, 6, 3, 6));
        buttons.add(button);
        add(button);
        revalidate();
        repaint();
    }

    public void removeButton(String text) {
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if (button.getText().equals(text)) {
                buttons.remove(i);
                remove(button);
                revalidate();
                repaint();
                break;
            }
        }
    }

    public void removeAllButtons() {
        buttons.clear();
        removeAll();
        revalidate();
        repaint();
    }

    public void addButtonActionListener(String text, ActionListener actionListener) {
        for (JButton button : buttons) {
            if (button.getText().equals(text)) {
                button.addActionListener(actionListener);
                break;
            }
        }
    }
}
