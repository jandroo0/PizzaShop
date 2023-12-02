package gui.employeeNewOrder.panel;

import gui.config.Utils;
import gui.employeeNewOrder.listener.EmployeeNewOrderListener;
import gui.tools.Button;
import gui.tools.MenuCustomList;
import gui.tools.PlaceholderTextField;
import model.MenuItem;
import model.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

public class OrderViewPanel extends JPanel {

    private JLabel titleLabel;

    // panels
    private JPanel titlePanel;

    private JPanel contentPanel;

    private JPanel buttonPanel;


    // order id field
    private PlaceholderTextField orderIDField;

    // current order list
    private MenuCustomList orderList;

    private LinkedList<MenuItem> orderItems;

    // price label
    private JLabel totalPriceLabel;
    private float currentTotalPrice;

    // buttons
    private Button orderButton;
    private Button cancelButton;

    private EmployeeNewOrderListener employeeNewOrderListener;
    ;

    public OrderViewPanel() {

        // title panel
        titlePanel = new JPanel();
        titleLabel = new JLabel("ORDER");

        // content panel
        contentPanel = new JPanel();

        // order id field
        orderIDField = new PlaceholderTextField("ORDER ID", 88, 26, 16);

        // order list
        orderList = new MenuCustomList(16, new Dimension(240, 120));
        orderItems = new LinkedList<MenuItem>();

        orderList.setDisplayItemCount(true);

        // price label
        totalPriceLabel = new JLabel("$ 0.00");
        currentTotalPrice = 0;

        // button panel
        buttonPanel = new JPanel();

        // order button
        orderButton = new Button("ORDER", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));
        // cancel button
        cancelButton = new Button("CANCEL", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        orderButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<MenuItem> items = orderList.getList();
                String orderID = orderIDField.getText();

                Order newOrder = new Order(orderID, items, currentTotalPrice);
                try {
                    employeeNewOrderListener.newOrderEvent(newOrder);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


        layoutComponents();
        styling();

    }


    private void layoutComponents() {
        // title panel
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));


        // contentPanel
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);
        gc.gridwidth = 2;
        contentPanel.add(orderList, gc);
        gc.gridy++;
        gc.gridwidth = 1;
        contentPanel.add(orderIDField, gc);
        gc.gridx++;
        contentPanel.add(totalPriceLabel, gc);


        // button panel
        buttonPanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 5, 10);
        buttonPanel.add(cancelButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 5, 0);
        buttonPanel.add(orderButton, gc);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 4, 5, 4),
                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));
        add(titlePanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void styling() {
        setBackground(Utils.getBackgroundColor());

        // title
        titlePanel.setBackground(Utils.getBackgroundColor());
        titleLabel.setForeground(Utils.getTextColor());
        titleLabel.setFont(Utils.getTextFont(28));

        // content panel
        contentPanel.setBackground(Utils.getBackgroundColor());

        // price label
        totalPriceLabel.setForeground(Utils.getTextColor());
        totalPriceLabel.setFont(Utils.getTextFont(24));
        totalPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalPriceLabel.setVerticalAlignment(SwingConstants.NORTH);

        // buttons panel
        buttonPanel.setBackground(Utils.getBackgroundColor());

    }

    public void addItem(MenuItem item) {
        // add up price
        currentTotalPrice += item.getPrice();
        // Format the price to display two decimal places
        String formattedPrice = String.format("%.2f", currentTotalPrice);
        totalPriceLabel.setText("$ " + formattedPrice);

        orderItems.add(item);
        orderList.addItem(item);
    }

    public void setEmployeeNewOrderListener(EmployeeNewOrderListener employeeNewOrderListener) {
        this.employeeNewOrderListener = employeeNewOrderListener;
    }
}
