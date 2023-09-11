package gui.editMenu.panel;

import gui.config.Utils;
import gui.editMenu.listener.EditMenuListener;
import gui.editMenu.listener.NavbarListener;
import gui.editMenu.navbar.NavbarPanel;
import gui.tools.Button;
import model.Ingredient;
import model.MenuItem;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

public class EditMenuPanel extends JPanel {

    // title panel
    private final JPanel titlePanel;
    // edit menu panel contents panel
    private final JPanel currentMenuPanel;
    // cancel / save button panel
    private final JPanel buttonsPanel;
    // buttons
    private final Button saveMenuButton;
    private final Button revertMenuButton;
    private LinkedList<EditMenuContainerPanel> containerPanels;
    private String currentMenuName;
    private LinkedList<String> menuNames;


    // DEFAULT PANEL COMPONENTS

    private PizzaBuilderPanel pizzaBuilderPanel;
    private LinkedList<String> pizzaPanelMenuComponents;
    private LinkedList<String> otherPanelMenuComponents;
    // nav panel
    private NavbarPanel navButtonsPanel;
    // title label
    private JLabel titleLabel;

    // editMenu listener
    private EditMenuListener editMenuListener;


    // container panels list to contain the container panels (i.e pizzaPanel)


    public EditMenuPanel() {

        // main title panel
        titlePanel = new JPanel();

        // edit menu panels container panel
        currentMenuPanel = new JPanel(new CardLayout());
        menuNames = new LinkedList<String>();
        containerPanels = new LinkedList<EditMenuContainerPanel>();

        //pizza builder panel
        pizzaBuilderPanel = new PizzaBuilderPanel();


        // pizza ingredients
        pizzaPanelMenuComponents = new LinkedList<String>();

        pizzaPanelMenuComponents.add("MEATS");
        pizzaPanelMenuComponents.add("VEGGIES");
        pizzaPanelMenuComponents.add("CRUST");
        pizzaPanelMenuComponents.add("SAUCE");

        // otherPanel
        otherPanelMenuComponents = new LinkedList<String>();

        otherPanelMenuComponents.add("DRINK");
        otherPanelMenuComponents.add("DESSERT");


        //buttons panel
        buttonsPanel = new JPanel();
        navButtonsPanel = new NavbarPanel();


        //labels
        titleLabel = new JLabel("EDIT MENU");

        saveMenuButton = new Button("SAVE", Utils.getTextFont(20), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        revertMenuButton = new Button("CANCEL", Utils.getTextFont(20), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));


        // navbar listener
        navButtonsPanel.setNavbarListener(new NavbarListener() {
            @Override
            public void navbarClicked(String buttonName) {
                setCurrentMenuPanel(buttonName);
            }
        });


        saveMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
                try {
                    editMenuListener.saveMenuEvent();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        revertMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    resetFields();
                    editMenuListener.editMenuCancelEvent();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        addPizzaBuilderPanel();
        newContainerPanel("INGREDIENT", "PIZZA", pizzaPanelMenuComponents);
        newContainerPanel("MENU_ITEM", "MISC", otherPanelMenuComponents);

        layoutComponents();
        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(currentMenuPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        styling();
    }

    public void resetFields() {
        for (EditMenuContainerPanel panel : containerPanels) {
            panel.resetFields();
        }
    }

    public void clearItems() {
        for (EditMenuContainerPanel panel : containerPanels) {
            panel.clearItems();
        }
    }

    public void setItems(LinkedList<MenuItem> items, LinkedList<Ingredient> ingredients) {
        pizzaBuilderPanel.setAvailableIngredients(ingredients);
        for (EditMenuContainerPanel panel : containerPanels) {

            for (MenuItem item : items) {
                if (panel.getType().equals("MENU_ITEM")) {
                    panel.addMenuItem(item);
                }
            }

            for (Ingredient ingredient : ingredients) {
                if (panel.getType().equals("INGREDIENT")) {
                    panel.addMenuItem(ingredient);
                }
            }

        }
    }


    public void setEditMenuListener(EditMenuListener listener) {
        this.editMenuListener = listener;
        for (EditMenuContainerPanel panel : containerPanels) {
            panel.setEditMenuListener(listener);
        }
    }

    private void layoutComponents() {

        // title panel
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(navButtonsPanel, BorderLayout.CENTER);


        GridBagConstraints gc = new GridBagConstraints();


        currentMenuPanel.setBorder(BorderFactory.createEmptyBorder());

        // current card/panel shown
        CardLayout cl = (CardLayout) currentMenuPanel.getLayout();
        cl.first(currentMenuPanel);
        currentMenuName = menuNames.get(0);


        // buttons panel
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        buttonsPanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        buttonsPanel.add(revertMenuButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 5, 0, 0);
        buttonsPanel.add(saveMenuButton, gc);

    }


    private void styling() {
        setBackground(Utils.getBackgroundColor());

        // container panels
        titlePanel.setBackground(Utils.getBackgroundColor());
        currentMenuPanel.setBackground(Utils.getBackgroundColor());
        buttonsPanel.setBackground(Utils.getBackgroundColor());

        // navbar
        navButtonsPanel.setButtonHoverEffect(currentMenuName);

        CardLayout cl = (CardLayout) currentMenuPanel.getLayout();
        navButtonsPanel.setButtonColors(currentMenuName);


        //title label
        titleLabel.setForeground(Utils.getTextColor());
        titleLabel.setFont(Utils.getTextFont(24));


    }

    public void setCurrentMenuPanel(String panelName) {
        CardLayout cl = (CardLayout) currentMenuPanel.getLayout(); // get current card/panel
        cl.show(currentMenuPanel, panelName); // show new card/panel
        currentMenuName = panelName; // set current card/panel name
        navButtonsPanel.setButtonHoverEffect(panelName); // set button hover effect for nav buttons
        navButtonsPanel.setButtonColors(panelName); // set button colors for nav buttons
    }

    public void newContainerPanel(String panelType, String panelName, LinkedList<String> subPanels) {
        EditMenuContainerPanel panel = new EditMenuContainerPanel(panelType, panelName, subPanels); // create new container panel with list of sub panels
        containerPanels.add(panel); // add new panel to container panels list
        currentMenuPanel.add(panel, panel.getID()); // add panel to current card/panel
        menuNames.add(panelName); // add panel name to menu names list
        navButtonsPanel.addButton(panel.getID()); // add button to nav buttons panel
    }

    public void addPizzaBuilderPanel() {
        currentMenuPanel.add(pizzaBuilderPanel, "BUILDER");
        menuNames.add("BUILDER");
        navButtonsPanel.addButton("BUILDER");
    }

    public void removeContainerPanel(EditMenuComponentPanel panel) {
        currentMenuPanel.remove(panel);

        navButtonsPanel.removeButton(panel.getID());
    }


}
