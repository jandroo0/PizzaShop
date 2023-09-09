package gui.home.employee.panel;

import gui.config.Utils;
import gui.tools.Button;

import javax.swing.*;
import java.awt.*;

public class EditMenuPanel extends JPanel {

    // title panel
    private final JPanel titlePanel;
    // edit menu panel contents panel
    private final JPanel menuContentsPanel;
    //    private final JPanel pizzaPanel;
    // cancel / save button panel
    private final JPanel buttonsPanel;
    // buttons
    private final Button saveMenuButton;
    private final Button revertMenuButton;
    // pizza options panel contains crust and toppings
    private EditMenuContainerPanel pizzaPanel;
    // nav panel
    private NavbarPanel navButtonsPanel;
    // title label
    private JLabel titleLabel;
    private Button pizzaMenuButton;
    // default panels
    private JPanel newMeatsPanel;
    private JPanel newVeggiesPanel;
    private JPanel newCrustPanel;


    public EditMenuPanel() {

        // main title panel
        titlePanel = new JPanel();

        // switch tab buttons
        pizzaMenuButton = new Button("PIZZA", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        String[] initialItems = {"Item 1", "Item 2", "Item 3"};
        newMeatsPanel = new EditMenuComponentPanel("MEATS", "Meats");
        newVeggiesPanel = new EditMenuComponentPanel("MEATS", "Meats");
        newCrustPanel = new EditMenuComponentPanel("MEATS", "Meats");

//        newMeatsPanel = new EditMenuComponentPanel("MEATS", "Meats");
//        newVeggiesPanel = new EditMenuComponentPanel("VEGGIES", "Veggies");
//        newCrustPanel = new EditMenuComponentPanel("CRUST", "Crust");

        // edit menu panels container panel
        menuContentsPanel = new JPanel(new CardLayout());

        //buttons panel
        buttonsPanel = new JPanel();
        navButtonsPanel = new NavbarPanel();


        // pizza

        // pizzaPanel
        pizzaPanel = new EditMenuContainerPanel();


        //labels
        titleLabel = new JLabel("EDIT MENU");

        saveMenuButton = new Button("SAVE", Utils.getTextFont(20), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        revertMenuButton = new Button("CANCEL", Utils.getTextFont(20), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));


        layoutComponents();
        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(menuContentsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        styling();
    }

    private void layoutComponents() {

        // title panel
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(navButtonsPanel, BorderLayout.CENTER);

        // nav panel
        navButtonsPanel.addButton("Pizza");

        GridBagConstraints gc = new GridBagConstraints();
//        navButtonsPanel.setLayout(new GridBagLayout());
//        navButtonsPanel.setBorder(BorderFactory.createEmptyBorder());
//        gc.gridx = 0;
//        gc.gridy = 0;
//        gc.insets = new Insets(0, 0, 0, 10);
//        navButtonsPanel.add(pizzaMenuButton, gc);

        menuContentsPanel.setBorder(BorderFactory.createEmptyBorder());

        menuContentsPanel.add(pizzaPanel, "PIZZA_PANEL");

        pizzaPanel.addComponentPanel(newMeatsPanel);
        pizzaPanel.addComponentPanel(newVeggiesPanel);
        pizzaPanel.addComponentPanel(newCrustPanel);

        //pizza panel
//        pizzaPanel.setLayout(new GridLayout(2, 2));
//        pizzaPanel.setBorder(BorderFactory.createEmptyBorder());
//        pizzaPanel.add(newMeatsPanel);
//        pizzaPanel.add(newVeggiesPanel);
//        pizzaPanel.add(newCrustPanel);

        // buttons panel
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        buttonsPanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 10);
        buttonsPanel.add(revertMenuButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 0, 0);
        buttonsPanel.add(saveMenuButton, gc);

    }


    private void styling() {
        setBackground(Utils.getBackgroundColor());

        // container panels
        titlePanel.setBackground(Utils.getBackgroundColor());
        menuContentsPanel.setBackground(Utils.getBackgroundColor());

        // navbar panel
        navButtonsPanel.setBackground(Utils.getBackgroundColor());

        //title label
        titleLabel.setForeground(Utils.getTextColor());
        titleLabel.setFont(Utils.getTextFont(24));


        // toppings panels

        pizzaPanel.setBackground(Utils.getBackgroundColor());

        // buttons panel

        buttonsPanel.setBackground(Utils.getBackgroundColor());
    }
}


//        pizzaPanel.add(crustPanel);
//        pizzaPanel.add(meatsPanel);
//        pizzaPanel.add(veggiesPanel);


//        // crust panel
//        crustPanel.setLayout(new BorderLayout());
//        crustPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5),
//                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));
//        crustPanel.add(crustTitlePanel, BorderLayout.NORTH);
//        crustPanel.add(crustContentsPanel, BorderLayout.CENTER);
//        crustPanel.add(crustButtonsPanel, BorderLayout.SOUTH);
//
//
//        // crust title panel
//        crustTitlePanel.add(crustLabel);
//        crustTitlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
//
//
//        // crust content panel
//        crustContentsPanel.setLayout(new GridBagLayout());
//        gc.gridx = 0;
//        gc.gridy = 0;
//        gc.insets = new Insets(0, 0, 0, 10);
//        crustContentsPanel.add(crustBox, gc);
//        gc.gridx++;
//        gc.insets = new Insets(0, 10, 0, 0);
//        crustContentsPanel.add(crustField, gc);
//
//        // crust buttons panel
//        crustButtonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
//        crustButtonsPanel.setLayout(new GridBagLayout());
//        gc = new GridBagConstraints();
//        gc.gridy = 0;
//        gc.gridx = 0;
//        gc.insets = new Insets(0, 0, 10, 10);
//        crustButtonsPanel.add(removeCrustButton, gc);
//        gc.gridx++;
//        gc.insets = new Insets(0, 10, 10, 0);
//        crustButtonsPanel.add(addCrustButton, gc);
//
//
//        // toppings panels
//
//        // meats panel
//        meatsPanel.setLayout(new BorderLayout());
//        meatsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10),
//                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));
//        meatsPanel.add(meatsTitlePanel, BorderLayout.NORTH);
//        meatsPanel.add(meatsContentsPanel, BorderLayout.CENTER);
//        meatsPanel.add(meatsButtonsPanel, BorderLayout.SOUTH);
//
//
//        // meats title panel
//        meatsTitlePanel.add(meatsLabel);
//        meatsTitlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
//
//
//        // meats content panel
//        meatsContentsPanel.setLayout(new GridBagLayout());
//        gc.gridx = 0;
//        gc.gridy = 0;
//        gc.insets = new Insets(0, 0, 0, 10);
//        meatsContentsPanel.add(meatsBox, gc);
//        gc.gridx++;
//        gc.insets = new Insets(0, 10, 0, 0);
//        meatsContentsPanel.add(meatsField, gc);
//
//        // meats buttons panel
//        meatsButtonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
//        meatsButtonsPanel.setLayout(new GridBagLayout());
//        gc = new GridBagConstraints();
//        gc.gridy = 0;
//        gc.gridx = 0;
//        gc.insets = new Insets(0, 0, 10, 10);
//        meatsButtonsPanel.add(removeMeatsButton, gc);
//        gc.gridx++;
//        gc.insets = new Insets(0, 10, 10, 0);
//        meatsButtonsPanel.add(addMeatsButton, gc);
//
//
//        // veggies panel
//        veggiesPanel.setLayout(new BorderLayout());
//        veggiesPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5),
//                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));
//        veggiesPanel.add(veggiesTitlePanel, BorderLayout.NORTH);
//        veggiesPanel.add(veggiesContentsPanel, BorderLayout.CENTER);
//        veggiesPanel.add(veggiesButtonsPanel, BorderLayout.SOUTH);
//
//
//        // veggies title panel
//        veggiesTitlePanel.add(veggiesLabel);
//        veggiesTitlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
//
//        // veggies content panel
//        veggiesContentsPanel.setLayout(new GridBagLayout());
//        gc.gridx = 0;
//        gc.gridy = 0;
//        gc.insets = new Insets(0, 0, 0, 10);
//        veggiesContentsPanel.add(veggiesBox, gc);
//        gc.gridx++;
//        gc.insets = new Insets(0, 10, 0, 0);
//        veggiesContentsPanel.add(veggiesField, gc);
//
//        // veggies buttons panel
//        veggiesButtonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
//        veggiesButtonsPanel.setLayout(new GridBagLayout());
//        gc = new GridBagConstraints();
//        gc.gridy = 0;
//        gc.gridx = 0;
//        gc.insets = new Insets(0, 0, 10, 10);
//        veggiesButtonsPanel.add(removeVeggiesButton, gc);
//        gc.gridx++;
//        gc.insets = new Insets(0, 10, 10, 0);
//        veggiesButtonsPanel.add(addVeggiesButton, gc);
//
//
//        // other panels
//        otherPanel.setLayout(new BorderLayout());
//        otherPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5),
//                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));
//        otherPanel.add(otherTitlePanel, BorderLayout.NORTH);
//        otherPanel.add(otherContentsPanel, BorderLayout.CENTER);
//
//
//        // other title panel
//        otherTitlePanel.add(otherLabel);
//        otherTitlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
//
//
//        // other contents panel
//        otherContentsPanel.setLayout(new GridBagLayout());
//        gc.gridx = 0;
//        gc.gridy = 0;
//        gc.insets = new Insets(0, 0, 10, 10);
//        otherContentsPanel.add(bevBox, gc);
//        gc.gridx++;
//        gc.insets = new Insets(0, 10, 10, 0);
//        otherContentsPanel.add(beveragesField, gc);
//        gc.gridy++;
//        gc.gridx = 0;
//        gc.insets = new Insets(0, 0, 0, 10);
//        otherContentsPanel.add(removeBevButton, gc);
//        gc.gridx++;
//        gc.insets = new Insets(0, 10, 0, 0);
//        otherContentsPanel.add(addBevButton, gc);
//        gc.gridx = 0;
//        gc.gridy++;
//        gc.insets = new Insets(10, 0, 0, 10);
//        otherContentsPanel.add(dessertBox, gc);
//        gc.gridx++;
//        gc.insets = new Insets(10, 10, 0, 0);
//        otherContentsPanel.add(dessertField, gc);
//        gc.gridx = 0;
//        gc.gridy++;
//        gc.insets = new Insets(10, 0, 0, 10);
//        otherContentsPanel.add(removeDessertButton, gc);
//        gc.gridx++;
//        gc.insets = new Insets(10, 10, 0, 0);
//        otherContentsPanel.add(addDessertButton, gc);
