package gui.home.employee.panel;


import gui.config.Utils;
import gui.tools.Button;
import gui.tools.PlaceholderTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMenuComponentPanel extends JPanel {

    private final JLabel label;
    private final JList<String> itemList;
    private final DefaultListModel<String> listModel;
    private final PlaceholderTextField textField;
    private final Button addButton;
    private final Button removeButton;

    public EditMenuComponentPanel(String labelText, String placeholderText) {
        setLayout(new BorderLayout());
        setBackground(Utils.getBackgroundColor());

        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5),
                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));

        label = new JLabel(labelText);
        label.setForeground(Utils.getTextColor());
        label.setFont(Utils.getTextFont(18));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        itemList.setBackground(Utils.getButtonBackgroundColor());
        itemList.setForeground(Utils.getTextColor());
        itemList.setFont(Utils.getTextFont(16));
        itemList.setBorder(BorderFactory.createLineBorder(Utils.getButtonBackgroundColor(), 2, true));
        itemList.setPreferredSize(new Dimension(100, 100));

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Utils.getBackgroundColor());

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);

        textField = new PlaceholderTextField(placeholderText, 100, 28, 20);
        contentPanel.add(itemList, gc);

        gc.gridy++;
        contentPanel.add(textField, gc);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setBackground(Utils.getBackgroundColor());
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        removeButton = new Button("OUT", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));
        buttonsPanel.add(removeButton);

        addButton = new Button("IN", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));
        buttonsPanel.add(addButton);

        add(label, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newItem = textField.getText();
                if (!newItem.isEmpty()) {
                    listModel.addElement(newItem);
                    textField.setText("");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = itemList.getSelectedIndices();
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    listModel.removeElementAt(selectedIndices[i]);
                }
            }
        });

        applyStyling(); // Apply styling immediately
    }

    // ... Rest of the class remains the same

    // Apply styling
    private void applyStyling() {
        label.setForeground(Utils.getTextColor());
        label.setFont(Utils.getTextFont(18));

        textField.setBackground(Utils.getTextFieldColor());
        textField.setForeground(Utils.getTextColor());
        textField.setFont(Utils.getTextFont(16));
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        applyStyling();
    }
}


//public class EditMenuComponentPanel extends JPanel {
//
//    private final JLabel label;
//    private final JComboBox<String> comboBox;
//    private final PlaceholderTextField textField;
//    private final Button addButton;
//    private final Button removeButton;
//
//    public EditMenuComponentPanel(String labelText, String placeholderText) {
//        setLayout(new BorderLayout());
//        setBackground(Utils.getBackgroundColor());
//
//        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5),
//                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));
//
//        label = new JLabel(labelText);
//        label.setForeground(Utils.getTextColor());
//        label.setFont(Utils.getTextFont(18));
//        label.setHorizontalAlignment(JLabel.CENTER);
//        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
//
//        JPanel contentPanel = new JPanel(new GridBagLayout());
//        contentPanel.setBackground(Utils.getBackgroundColor());
//
//        GridBagConstraints gc = new GridBagConstraints();
//        gc.gridx = 0;
//        gc.gridy = 0;
//        gc.insets = new Insets(0, 0, 0, 10);
//
//        textField = new PlaceholderTextField(placeholderText, 100, 30, 20);
//        contentPanel.add(textField, gc);
//
//        gc.gridx++;
//        comboBox = new JComboBox<>();
//        contentPanel.add(comboBox, gc);
//
//        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        buttonsPanel.setBackground(Utils.getBackgroundColor());
//        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
//
//        addButton = new Button("ADD", Utils.getTextFont(16), Utils.getTextColor(),
//                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(),
//                BorderFactory.createEmptyBorder(5, 8, 5, 8));
//        buttonsPanel.add(addButton);
//
//        removeButton = new Button("REMOVE", Utils.getTextFont(16), Utils.getTextColor(),
//                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(),
//                BorderFactory.createEmptyBorder(5, 8, 5, 8));
//        buttonsPanel.add(removeButton);
//
//        add(label, BorderLayout.NORTH);
//        add(contentPanel, BorderLayout.CENTER);
//        add(buttonsPanel, BorderLayout.SOUTH);
//    }
//
//    // You can add any additional methods or customizations specific to this panel here
//
//    // For example, you might want a method to get the text from the text field
//    public String getTextFieldText() {
//        return textField.getText();
//    }
//
//    // If you have a combobox, you can add a method to get the selected item
//    public Object getSelectedComboBoxItem() {
//        return comboBox.getSelectedItem();
//    }
//
//    // You can also add methods to add/remove items from the combobox
//    public void addToComboBox(Object item) {
//        comboBox.addItem(item.toString());
//    }
//
//    public void removeFromComboBox(Object item) {
//        comboBox.removeItem(item.toString());
//    }
//
//    // Apply styling
//    private void applyStyling() {
//        label.setForeground(Utils.getTextColor());
//        label.setFont(Utils.getTextFont(18));
//
//        textField.setBackground(Utils.getTextFieldColor());
//        textField.setForeground(Utils.getTextColor());
//        textField.setFont(Utils.getTextFont(16));
//
//        comboBox.setBackground(Utils.getButtonBackgroundColor());
//        comboBox.setForeground(Utils.getTextColor());
//        comboBox.setFont(Utils.getTextFont(16));
//        comboBox.setBorder(BorderFactory.createLineBorder(Utils.getButtonBackgroundColor(), 2, true));
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        applyStyling();
//    }
//}
