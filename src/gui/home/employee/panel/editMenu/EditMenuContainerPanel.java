package gui.home.employee.panel.editMenu;

import gui.config.Utils;
import gui.home.employee.listener.EditMenuListener;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class EditMenuContainerPanel extends JPanel {

    String ID;
    private LinkedList<EditMenuComponentPanel> componentPanels;
    private EditMenuListener listener;

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

    public void setListItems(LinkedList<MenuItem> items) {
        for (EditMenuComponentPanel panel : componentPanels) {
            for (MenuItem item : items) {
                if (panel.getID().equalsIgnoreCase(item.getCategory())) {
                    panel.addItem(item);

                }
            }
        }
    }

    public void addComponentPanel(String panelName) {
        EditMenuComponentPanel panel = new EditMenuComponentPanel(panelName.toUpperCase(), panelName.toUpperCase()); // Create a new EditMenuComponentPanel
        if (!componentPanels.contains(panel)) {
            componentPanels.add(panel);
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


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public void setEditMenuListener(EditMenuListener listener) {
        for (EditMenuComponentPanel panel : componentPanels) {
            panel.setEditMenuListener(listener);
        }
    }

    public void resetFields() {
        for (EditMenuComponentPanel panel : componentPanels) {
            panel.resetFields();
        }
    }

    public void clearItems() {
        for (EditMenuComponentPanel panel : componentPanels) {
            panel.clearList();
        }
    }
}
