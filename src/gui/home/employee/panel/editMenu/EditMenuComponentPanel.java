package gui.home.employee.panel.editMenu;


import gui.config.Utils;
import gui.home.employee.listener.EditMenuListener;
import gui.tools.Button;
import gui.tools.EditMenuCustomList;
import gui.tools.PlaceholderTextField;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditMenuComponentPanel extends JPanel {

    private final JLabel label;
    private final String category;
    private final EditMenuCustomList itemList;

    private final PlaceholderTextField textField;
    private final PlaceholderTextField priceTextField;
    private final Button addButton;
    private final Button removeButton;
    private EditMenuListener listener;

    public EditMenuComponentPanel(String label, String placeholderText) {
        setLayout(new BorderLayout());
        setBackground(Utils.getBackgroundColor());

        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));

        this.category = label;
        this.label = new JLabel(label);
        this.label.setForeground(Utils.getTextColor());
        this.label.setFont(Utils.getTextFont(20));
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        itemList = new EditMenuCustomList(13, new Dimension(140, 100));
        itemList.setBorder(BorderFactory.createLineBorder(Utils.getButtonBackgroundColor(), 2, true));


        textField = new PlaceholderTextField(placeholderText, 88, 26, 16);
        priceTextField = new PlaceholderTextField("$", 42, 26, 16);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Utils.getBackgroundColor());

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);

        gc.gridwidth = 2;
        contentPanel.add(itemList, gc);

        gc.gridy++;
        gc.gridwidth = 1;
        gc.insets = new Insets(0, 0, 0, 5);
        contentPanel.add(textField, gc);

        gc.gridx++;
        gc.insets = new Insets(0, 0, 0, 0);
        contentPanel.add(priceTextField, gc);

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

        add(this.label, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = textField.getText();
                String priceText = priceTextField.getText();
                if (!item.isEmpty()) {
                    try {
                        float price = Float.parseFloat(priceText);
                        MenuItem itemToAdd = new MenuItem(category, item, price);


                        itemList.addItem(itemToAdd);
                        textField.setText("");
                        priceTextField.setText("");

                        try {
                            listener.addMenuItemEvent(itemToAdd);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    } catch (NumberFormatException ex) {
                        // Handle the case where the input is not a valid float
                        // You might want to display a message to the user
                        System.out.println("Invalid price input: " + priceText);
                        // Optionally, display a message dialog to inform the user
                        JOptionPane.showMessageDialog(null, "Please enter a valid price.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });


        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemList.removeSelectedItem();
            }
        });

        applyStyling(); // Apply styling immediately
    }

    public void clearList() {
        itemList.clearList();
    }

    public void addItem(MenuItem item) {
        itemList.addItem(item);
    }


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

    public String getID() {
        return label.getText();
    }

    public void setEditMenuListener(EditMenuListener listener) {
        this.listener = listener;
    }

    public void resetFields() {
        textField.setText("");
        priceTextField.setText("");
    }
}

