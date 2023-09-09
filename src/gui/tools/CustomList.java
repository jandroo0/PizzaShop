package gui.tools;

import gui.config.Utils;

import javax.swing.*;
import java.awt.*;

public class CustomList<T> extends JList<T> {

    public CustomList(int fontSize, Dimension preferredSize) {
        setFont(Utils.getTextFont(fontSize));
        setBorder(BorderFactory.createLineBorder(Utils.getButtonBackgroundColor(), 2, true));
        setBackground(Utils.getButtonBackgroundColor());
        setForeground(Utils.getTextColor());
//        setBorder(BorderFactory.createLineBorder(Utils.getButtonBackgroundColor(), 2, true));
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setPreferredSize(preferredSize);

        setCellRenderer(new CustomListCellRenderer());
    }

    // Set the data model for the JList
    public void setCustomModel(ListModel<T> model) {
        setModel(model);
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
