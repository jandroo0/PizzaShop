package gui.home.employee.panel;

import gui.config.Utils;
import gui.editMenu.listener.NavbarListener;
import gui.employeeNewOrder.navBar.NewOrderNavPanel;
import gui.employeeNewOrder.panel.NewOrderComponentPanel;
import gui.employeeNewOrder.panel.OrderViewPanel;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class NewOrderPanel extends JPanel {


    // categories of menu items list
    LinkedList<String> categories;

    // category name of current menu
    private String currentMenuName;
    // title label
    private JLabel titleLabel;
    // title panel
    private JPanel titlePanel;

    // container panel
    private JPanel containerPanel;
    // nav buttons panel
    private NewOrderNavPanel buttonsPanel;
    // currently built order panel
    private OrderViewPanel orderViewPanel;

    // list of category panels
    private LinkedList<NewOrderComponentPanel> categoryPanels;

    public NewOrderPanel() {

        // title
        titlePanel = new JPanel();
        titleLabel = new JLabel("NEW ORDER");

        categories = new LinkedList<String>();

        // container panel
        containerPanel = new JPanel(new CardLayout());

        // category panels
        categoryPanels = new LinkedList<NewOrderComponentPanel>();


        // nav buttons
        buttonsPanel = new NewOrderNavPanel();

        // order view panel
        orderViewPanel = new OrderViewPanel();

        buttonsPanel.setNavbarListener(new NavbarListener() {

            @Override
            public void navbarClicked(String buttonName) {
                setCurrentMenuPanel(buttonName);
            }
        });


        layoutComponents();
        styling();
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        // title panel
        add(titlePanel, BorderLayout.NORTH);

        // title label
        titlePanel.add(titleLabel);

        // buttonsPanel
        add(buttonsPanel, BorderLayout.WEST);
        add(containerPanel, BorderLayout.CENTER);
        add(orderViewPanel, BorderLayout.SOUTH);

    }

    private void styling() {
        setBackground(Utils.getBackgroundColor());
        titlePanel.setBackground(Utils.getBackgroundColor());

        titleLabel.setForeground(Utils.getTextColor());
        titleLabel.setFont(Utils.getTextFont(30));

        buttonsPanel.setBackground(Utils.getBackgroundColor());

    }


    public void setItems(LinkedList<MenuItem> items) {
        for (MenuItem item : items) {
            if (!categories.contains(item.getCategory())) {
                categories.add(item.getCategory()); // add category to categories list
                NewOrderComponentPanel panel = new NewOrderComponentPanel(item.getCategory()); // create new panel with category naame
                categoryPanels.add(panel); // add panel to category panels list
                containerPanel.add(panel, item.getCategory()); // add panel to container panel with card layout, with category name as ID
                buttonsPanel.addButton(item.getCategory()); // add button to nav buttons
            }
        }

        for (NewOrderComponentPanel categoryPanel : categoryPanels) {
            categoryPanel.clearList();
            for (MenuItem item : items) {
                if (categoryPanel.getCategory().equalsIgnoreCase(item.getCategory())) {
                    categoryPanel.addItem(item);
                }
            }
        }
    }


    public void setCurrentMenuPanel(String panelName) {
        CardLayout cl = (CardLayout) containerPanel.getLayout(); // get current card/panel
        cl.show(containerPanel, panelName); // show new card/panel
        currentMenuName = panelName; // set current card/panel name
        buttonsPanel.setButtonHoverEffect(panelName); // set button hover effect for nav buttons
        buttonsPanel.setButtonColors(panelName); // set button colors for nav buttons
    }


}
