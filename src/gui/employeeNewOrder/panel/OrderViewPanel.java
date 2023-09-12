package gui.employeeNewOrder.panel;

import gui.config.Utils;
import gui.tools.Button;
import gui.tools.MenuCustomList;
import gui.tools.PlaceholderTextField;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class OrderViewPanel extends JPanel {

    private JLabel titleLabel;

    // panels
    private JPanel titlePanel;

    private JPanel contentPanel;

    private JPanel buttonPanel;
    private JPanel orderPanel;

    // order id field
    private PlaceholderTextField orderIDField;

    // current order list
    private MenuCustomList orderList;

    private LinkedList<MenuItem> orderItems;

    // buttons
    private Button orderButton;
    private Button cancelButton;

    public OrderViewPanel() {

        // title panel
        titlePanel = new JPanel();
        titleLabel = new JLabel("ORDER");

        // content panel
        contentPanel = new JPanel();

        // order id field
        orderIDField = new PlaceholderTextField("ORDER ID", 88, 26, 16);

        // order list
        orderList = new MenuCustomList(16, new Dimension(200, 100));
        orderItems = new LinkedList<MenuItem>();

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
        contentPanel.add(orderList, gc);
        gc.gridy++;
        contentPanel.add(orderIDField, gc);


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
        titleLabel.setFont(Utils.getTextFont(24));

        // content panel
        contentPanel.setBackground(Utils.getBackgroundColor());

        // buttons panel
        buttonPanel.setBackground(Utils.getBackgroundColor());

    }
}
