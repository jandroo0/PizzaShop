package gui.home.employee.panel;

import gui.config.Utils;

import javax.swing.*;
import java.awt.*;

public class EditMenuContainerPanel extends JPanel {

    private final JPanel[] componentPanels;

    public EditMenuContainerPanel() {

        setBackground(Utils.getBackgroundColor());
        setBorder(BorderFactory.createEmptyBorder());

        componentPanels = new JPanel[4];

        // Set layout for the main panel
        setLayout(new GridLayout(2, 2));

//        // Add the provided component panels to the main panel
//        for (JPanel panel : componentPanels) {
//            add(panel);
//        }

        // Apply any additional styling or configurations as needed
        // ...
    }

    public void addComponentPanel(JPanel panel) {
        // Find an empty slot and add the panel
        for (int i = 0; i < componentPanels.length; i++) {
            if (componentPanels[i] == null) {
                componentPanels[i] = panel;
                add(panel);
                revalidate();
                repaint();
                break;
            }
        }
    }

    public void removeComponentPanel(JPanel panel) {
        for (int i = 0; i < componentPanels.length; i++) {
            if (componentPanels[i] == panel) {
                remove(panel);
                componentPanels[i] = null;
                revalidate();
                repaint();
                break;
            }
        }
    }
}
