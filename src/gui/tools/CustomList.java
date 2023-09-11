package gui.tools;

import gui.config.Utils;

import javax.swing.*;
import java.awt.*;

public class CustomList extends JPanel {

    private JList<Object> list;
    private DefaultListModel<Object> model;
    private JScrollPane scrollPane;

    public CustomList(int fontSize, Dimension preferredSize) {
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

    // Get the JList
    public JList<Object> getCustomList() {
        return list;
    }

    // Get the data model for the JList
    public ListModel<Object> getCustomModel() {
        return list.getModel();
    }

    // Set the data model for the JList
    public void setCustomModel(ListModel<Object> model) {
        list.setModel(model);
    }

    // Add an item to the list
    public void addItem(Object item) {
        DefaultListModel<Object> model = (DefaultListModel<Object>) list.getModel();
        model.addElement(item);
    }


    // Remove the selected item from the list
    public void removeSelectedItem() {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            DefaultListModel<Object> model = (DefaultListModel<Object>) list.getModel();
            model.remove(selectedIndex);
        }
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
