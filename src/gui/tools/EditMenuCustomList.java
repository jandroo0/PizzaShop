package gui.tools;

import gui.config.Utils;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class EditMenuCustomList extends JPanel {

    private JList<MenuItem> list;
    private DefaultListModel<MenuItem> model;
    private JScrollPane scrollPane;

    public EditMenuCustomList(int fontSize, Dimension preferredSize) {
        setLayout(new BorderLayout());
        setPreferredSize(preferredSize);

        list = new JList<>();
        scrollPane = new JScrollPane(list);

        scrollPane.setPreferredSize(preferredSize);
        scrollPane.setBorder(BorderFactory.createLineBorder(Utils.getButtonBackgroundColor(), 2, true));

        model = new DefaultListModel<>();
        list.setModel(model);
        list.setCellRenderer(new CustomListCellRenderer());

        list.setFont(Utils.getTextFont(fontSize));
        list.setBorder(BorderFactory.createLineBorder(Utils.getButtonBackgroundColor(), 2, true));
        list.setBackground(Utils.getButtonBackgroundColor());
        list.setForeground(Utils.getTextColor());
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);


        add(scrollPane, BorderLayout.CENTER);
    }


    // Add an item to the list
    public void addItem(MenuItem item) {
        DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
        model.addElement(item);
    }

    // get selected item from list
    public MenuItem getSelectedItem() {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
            return model.getElementAt(selectedIndex);
        }
        return null;
    }

    // get the list model items as a menu item linked list
    public LinkedList<MenuItem> getList() {
        DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
        LinkedList<MenuItem> items = new LinkedList<>();
        for (int i = 0; i < model.getSize(); i++) {
            items.add(model.getElementAt(i));
        }
        return items;
    }


    // Remove the selected item from the list
    public void removeSelectedItem() {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
            model.remove(selectedIndex);
        }
    }

    public void clearList() {
        DefaultListModel<MenuItem> model = (DefaultListModel<MenuItem>) list.getModel();
        model.removeAllElements();
    }

    // Custom ListCellRenderer class
    private class CustomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Customize the appearance here if needed

            return c;
        }
    }
}
