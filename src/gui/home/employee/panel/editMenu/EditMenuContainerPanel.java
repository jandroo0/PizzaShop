package gui.home.employee.panel.editMenu;

import gui.config.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class EditMenuContainerPanel extends JPanel {

    String ID;
    private LinkedList<EditMenuComponentPanel> componentPanels;

    public EditMenuContainerPanel(String ID, LinkedList<String> subPanels) {
        this.ID = ID;

        setBackground(Utils.getBackgroundColor());
        setBorder(BorderFactory.createEmptyBorder());


        // Set layout for the main panel
        setLayout(new GridLayout(2, 2));

        componentPanels = new LinkedList<>();

        // Add the provided component panels to the main panel
        for (String panelName : subPanels) {
            addComponentPanel(panelName);
        }

        // Apply any additional styling or configurations as needed
        // ...
    }

    public void addComponentPanel(String panelName) {
        EditMenuComponentPanel panel = new EditMenuComponentPanel(panelName.toUpperCase(), panelName.toUpperCase());
        if (!componentPanels.contains(panel)) {
            add(panel);
            revalidate();
            repaint();
        }

    }

    public void removeComponentPanel(EditMenuComponentPanel panel) {
        for (EditMenuComponentPanel i : componentPanels) {
            if (i == panel) {
                remove(panel);
                i = null;
                revalidate();
                repaint();
                break;
            }
        }
    }

    public LinkedList<EditMenuComponentPanel> getComponentPanels() {
        return componentPanels;
    }

    public EditMenuComponentPanel getComponentPanel(String ID) {
        for (EditMenuComponentPanel panel : componentPanels) {
            if (panel.getID().equals(ID)) {
                return panel;
            }
        }
        return null;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


}
