package gui.employeeNewOrder.navBar;

import gui.config.Utils;
import gui.editMenu.listener.NavbarListener;
import gui.tools.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class NewOrderNavPanel extends JPanel {

    private final List<Button> buttons;
    private JPanel buttonsPanel;
    private NavbarListener listener;
    private GridBagConstraints gc;

    public NewOrderNavPanel() {
        buttons = new ArrayList<>();
        setLayout(new BorderLayout());
        setBackground(Utils.getBackgroundColor());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 4, 0, 2),
                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));
//        setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 2));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.insets = new Insets(0, 0, 12, 0);
        buttonsPanel.setBackground(Utils.getBackgroundColor());

        add(buttonsPanel, BorderLayout.CENTER);

    }


    public void setButtonHoverEffect(String currentMenu) {

        for (Button button : buttons) {
            button.setCustomMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (button.getText().equals(currentMenu)) {
                        button.setBackground(Utils.getButtonBackgroundColor());
                        button.setForeground(Utils.getTextColor());
                    } else {
                        button.setBackground(Utils.getButtonHoverColor());
                        button.setForeground(Utils.getButtonBackgroundColor());
                    }

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!button.getText().equals(currentMenu)) {
                        button.setBackground(Utils.getButtonBackgroundColor());
                        button.setForeground(Utils.getTextColor());
                    } else {
                        button.setBackground(Utils.getButtonHoverColor());
                        button.setForeground(Utils.getButtonBackgroundColor());
                    }

                }
            });
        }
    }

    public void addButton(String text) {

        Button button = new Button(text, Utils.getTextFont(12), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(3, 6, 3, 6));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.navbarClicked(e.getActionCommand());

            }
        });
        buttons.add(button);
        gc.gridy++;
        buttonsPanel.add(button, gc);
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


    public void setButtonColors(String panelName) {
        for (Button button : buttons) {

            if (button.getText().equals(panelName)) {
                button.setBackground(Utils.getNavButtonSelectedColor());
                button.setForeground(Utils.getButtonBackgroundColor());
            } else {
                button.setBackground(Utils.getButtonBackgroundColor());
                button.setForeground(Utils.getTextColor());
            }
        }
    }

    public void removeAllButtons() {
        buttons.clear();
        removeAll();
        revalidate();
        repaint();
    }


    public void setNavbarListener(NavbarListener listener) {
        this.listener = listener;
    }

}
