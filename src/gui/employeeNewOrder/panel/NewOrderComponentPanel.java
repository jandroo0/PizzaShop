package gui.employeeNewOrder.panel;

import gui.config.Utils;
import gui.employeeNewOrder.listener.NewOrderPanelsListener;
import gui.tools.Button;
import gui.tools.MenuCustomList;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class NewOrderComponentPanel extends JPanel {

    String category;

    private JLabel titleLabel;
    private JPanel titlePanel;

    private JPanel listPanel;

    private MenuCustomList itemList;

    private Button addButton;

    private LinkedList<MenuItem> items;

    private NewOrderPanelsListener newOrderPanelsListener;

    public NewOrderComponentPanel(String category) {

        this.category = category;

        // title
        titleLabel = new JLabel(category);
        titlePanel = new JPanel();

        // list panel
        listPanel = new JPanel();

        // category items
        items = new LinkedList<MenuItem>();

        // items list
        itemList = new MenuCustomList(13, new Dimension(150, 200));
        itemList.setAlignCenter(true);

        // add button
        addButton = new Button("ADD", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                newOrderPanelsListener.itemAdded(itemList.getSelectedItem());
            }
        });


        layoutComponents();
        styling();

    }

    public void setEmployeeNewOrderListener(NewOrderPanelsListener listener) {
        this.newOrderPanelsListener = listener;
    }

    public void setEmployeeNewOrderListener() {
    }


    private void layoutComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 2, 0, 4),
                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));

        // title panel
        titlePanel.add(titleLabel);

        //list panel
        listPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);
        listPanel.add(itemList, gc);
        gc.gridy++;
        listPanel.add(addButton, gc);


        add(titlePanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
    }

    private void styling() {
        setBackground(Utils.getBackgroundColor());

        titlePanel.setBackground(Utils.getBackgroundColor());

        titleLabel.setForeground(Utils.getTextColor());
        titleLabel.setFont(Utils.getTextFont(30));

        listPanel.setBackground(Utils.getBackgroundColor());


    }

    public void addItem(MenuItem item) {
        items.add(item);
        itemList.addItem(item);
    }

    public void clearList() {
        itemList.clearList();
    }


    // get category
    public String getCategory() {
        return category;
    }
}
