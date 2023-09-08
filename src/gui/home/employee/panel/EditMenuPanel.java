package gui.home.employee.panel;

import gui.config.Utils;
import gui.tools.Button;
import gui.tools.PlaceholderTextField;

import javax.swing.*;
import java.awt.*;

public class EditMenuPanel extends JPanel {

    // crust panels
    private final JPanel crustPanel;
    private final JPanel crustTitlePanel;
    private final JPanel crustContentsPanel;
    private final JPanel crustButtonsPanel;


    // toppings panels
    private final JPanel meatsPanel;
    private final JPanel meatsTitlePanel;
    private final JPanel meatsContentsPanel;

    private final JPanel veggiesPanel;
    private final JPanel veggiesTitlePanel;
    private final JPanel veggiesContentsPanel;

    // pizza options panel contains crust and toppings
    private final JPanel pizzaPanel;

    // other panels
    private final JPanel otherPanel;
    private final JPanel otherTitlePanel;
    private final JPanel otherContentsPanel;


    private final JLabel crustLabel;
    private final JLabel meatsLabel;
    private final JLabel veggiesLabel;
    private final JLabel otherLabel;
    private final PlaceholderTextField crustField;
    private final PlaceholderTextField meatsField;
    private final PlaceholderTextField veggiesField;
    private final PlaceholderTextField beveragesField;
    private final PlaceholderTextField dessertField;
    private final JComboBox<String> crustBox;
    private final JComboBox<String> meatsBox;
    private final JComboBox<String> veggiesBox;
    private final JComboBox<String> bevBox;
    private final JComboBox<String> dessertBox;
    private final Button addCrustButton;
    private final Button removeCrustButton;
    private final Button addMeatsButton;
    private final Button removeMeatsButton;
    private final Button addVeggiesButton;
    private final Button removeVeggiesButton;
    private final Button addBevButton;
    private final Button removeBevButton;
    private final Button addDessertButton;
    private final Button removeDessertButton;
    private final Button saveMenuButton;
    private final Button revertMenuButton;

    // title panel
    private final JPanel titlePanel;
    // edit menu panels
    private final JPanel editMenuContentPanel;
    // cancel / save button panel
    private final JPanel buttonsPanel;

    // nav panel

    private JPanel navButtonsPanel;
    // title label
    private JLabel titleLabel;

    private Button pizzaMenuButton;


    public EditMenuPanel() {

        // main title panel
        titlePanel = new JPanel();

        // switch tab buttons
        pizzaMenuButton = new Button("PIZZA OPTIONS", Utils.getTextFont(18), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));


        // edit menu panels container panel
        editMenuContentPanel = new JPanel(new CardLayout());

        //buttons panel
        buttonsPanel = new JPanel();
        navButtonsPanel = new JPanel();


        // crust panels
        crustPanel = new JPanel();
        crustTitlePanel = new JPanel();
        crustContentsPanel = new JPanel();
        crustButtonsPanel = new JPanel();

        // toppings panel
        meatsPanel = new JPanel();
        meatsTitlePanel = new JPanel();
        meatsContentsPanel = new JPanel();

        veggiesPanel = new JPanel();
        veggiesTitlePanel = new JPanel();
        veggiesContentsPanel = new JPanel();

        // pizzaPanel
        pizzaPanel = new JPanel();

        // other panels
        otherPanel = new JPanel();
        otherTitlePanel = new JPanel();
        otherContentsPanel = new JPanel();

        //labels
        titleLabel = new JLabel("EDIT MENU");

        crustLabel = new JLabel("CRUST");

        meatsLabel = new JLabel("MEATS");
        veggiesLabel = new JLabel("VEGGIES");

        otherLabel = new JLabel("MISC");

        // fields
        crustField = new PlaceholderTextField(" Crust", 100, 30, 20);

        meatsField = new PlaceholderTextField(" Meats", 100, 30, 20);
        veggiesField = new PlaceholderTextField(" Veggies", 100, 30, 20);

        beveragesField = new PlaceholderTextField(" Drinks", 100, 30, 20);
        dessertField = new PlaceholderTextField(" Sweets", 100, 30, 20);

        // combo boxes
        crustBox = new JComboBox<String>();
        meatsBox = new JComboBox<String>();
        veggiesBox = new JComboBox<String>();
        bevBox = new JComboBox<String>();
        dessertBox = new JComboBox<String>();

        // buttons
        addCrustButton = new Button("ADD", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        removeCrustButton = new Button("REMOVE", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        addMeatsButton = new Button("ADD", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        removeMeatsButton = new Button("REMOVE", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        addVeggiesButton = new Button("ADD", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        removeVeggiesButton = new Button("REMOVE", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        addBevButton = new Button("ADD", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        removeBevButton = new Button("REMOVE", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        addDessertButton = new Button("ADD", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        removeDessertButton = new Button("REMOVE", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        saveMenuButton = new Button("SAVE", Utils.getTextFont(20), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        revertMenuButton = new Button("CANCEL", Utils.getTextFont(20), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));


        layoutComponents();
        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(editMenuContentPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        styling();
    }

    private void layoutComponents() {

        // title panel
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(navButtonsPanel, BorderLayout.CENTER);

        GridBagConstraints gc = new GridBagConstraints();
        navButtonsPanel.setLayout(new GridBagLayout());
        navButtonsPanel.setBorder(BorderFactory.createEmptyBorder());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 10);
        navButtonsPanel.add(pizzaMenuButton, gc);

        editMenuContentPanel.setBorder(BorderFactory.createEmptyBorder());

//        editMenuContentPanel.add(toppingsPanel, "CURRENT_MENU");
        editMenuContentPanel.add(pizzaPanel, "PIZZA_PANEL");
        editMenuContentPanel.add(otherPanel, "OTHER_PANEL");

        //pizza panel
        pizzaPanel.setLayout(new GridLayout(2, 2));
        pizzaPanel.setBorder(BorderFactory.createEmptyBorder());
        pizzaPanel.add(crustPanel);
        pizzaPanel.add(meatsPanel);
        pizzaPanel.add(veggiesPanel);


        // crust panel
        crustPanel.setLayout(new BorderLayout());
        crustPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5),
                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));
        crustPanel.add(crustTitlePanel, BorderLayout.NORTH);
        crustPanel.add(crustContentsPanel, BorderLayout.CENTER);
        crustPanel.add(crustButtonsPanel, BorderLayout.SOUTH);


        // crust title panel
        crustTitlePanel.add(crustLabel);
        crustTitlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));


        // crust content panel
        crustContentsPanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 10);
        crustContentsPanel.add(crustBox, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 0, 0);
        crustContentsPanel.add(crustField, gc);

        // crust buttons panel
        crustButtonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        crustButtonsPanel.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.gridy = 0;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 10, 10);
        crustButtonsPanel.add(removeCrustButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 10, 0);
        crustButtonsPanel.add(addCrustButton, gc);


        // toppings panels

        // meats panel
        meatsPanel.setLayout(new BorderLayout());
        meatsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10),
                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));
        meatsPanel.add(meatsTitlePanel, BorderLayout.NORTH);
        meatsPanel.add(meatsContentsPanel, BorderLayout.CENTER);


        // meats title panel
        meatsTitlePanel.add(meatsLabel);
        meatsTitlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));


        // meats content panel
        meatsContentsPanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 10);
        meatsContentsPanel.add(meatsBox, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 10, 0);
        meatsContentsPanel.add(meatsField, gc);
        gc.gridy++;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 10);
        meatsContentsPanel.add(removeMeatsButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 0, 0);
        meatsContentsPanel.add(addMeatsButton, gc);

        // veggies panel
        veggiesPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10),
                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));
        veggiesPanel.add(veggiesTitlePanel, BorderLayout.NORTH);
        veggiesPanel.add(veggiesContentsPanel, BorderLayout.CENTER);


        // veggies title panel
        veggiesTitlePanel.add(veggiesLabel);
        veggiesTitlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // veggies content panel
        veggiesContentsPanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 10);
        veggiesContentsPanel.add(veggiesBox, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 0, 0);
        veggiesContentsPanel.add(veggiesField, gc);
        gc.gridx = 0;
        gc.gridy++;
        gc.insets = new Insets(10, 0, 0, 10);
        veggiesContentsPanel.add(removeVeggiesButton, gc);
        gc.gridx++;
        gc.insets = new Insets(10, 10, 0, 0);
        veggiesContentsPanel.add(addVeggiesButton, gc);


        // other panels
        otherPanel.setLayout(new BorderLayout());
        otherPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5),
                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));
        otherPanel.add(otherTitlePanel, BorderLayout.NORTH);
        otherPanel.add(otherContentsPanel, BorderLayout.CENTER);


        // other title panel
        otherTitlePanel.add(otherLabel);
        otherTitlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));


        // other contents panel
        otherContentsPanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 10);
        otherContentsPanel.add(bevBox, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 10, 0);
        otherContentsPanel.add(beveragesField, gc);
        gc.gridy++;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 10);
        otherContentsPanel.add(removeBevButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 0, 0);
        otherContentsPanel.add(addBevButton, gc);
        gc.gridx = 0;
        gc.gridy++;
        gc.insets = new Insets(10, 0, 0, 10);
        otherContentsPanel.add(dessertBox, gc);
        gc.gridx++;
        gc.insets = new Insets(10, 10, 0, 0);
        otherContentsPanel.add(dessertField, gc);
        gc.gridx = 0;
        gc.gridy++;
        gc.insets = new Insets(10, 0, 0, 10);
        otherContentsPanel.add(removeDessertButton, gc);
        gc.gridx++;
        gc.insets = new Insets(10, 10, 0, 0);
        otherContentsPanel.add(addDessertButton, gc);


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
        editMenuContentPanel.setBackground(Utils.getBackgroundColor());

        // navbar panel
        navButtonsPanel.setBackground(Utils.getBackgroundColor());

        //title label
        titleLabel.setForeground(Utils.getTextColor());
        titleLabel.setFont(Utils.getTextFont(24));


        // crust panels
        crustPanel.setBackground(Utils.getBackgroundColor());
        crustTitlePanel.setBackground(Utils.getBackgroundColor());
        crustContentsPanel.setBackground(Utils.getBackgroundColor());
        crustButtonsPanel.setBackground(Utils.getBackgroundColor());

        // crust panel components
        crustLabel.setForeground(Utils.getTextColor());
        crustLabel.setFont(Utils.getTextFont(18));


        // toppings panels

        pizzaPanel.setBackground(Utils.getBackgroundColor());

        // meats panel
        meatsPanel.setBackground(Utils.getBackgroundColor());
        meatsTitlePanel.setBackground(Utils.getBackgroundColor());
        meatsContentsPanel.setBackground(Utils.getBackgroundColor());

        // toppings panel components
        meatsLabel.setForeground(Utils.getTextColor());
        meatsLabel.setFont(Utils.getTextFont(18));

        // toppings panels
        veggiesPanel.setBackground(Utils.getBackgroundColor());
        veggiesTitlePanel.setBackground(Utils.getBackgroundColor());
        veggiesContentsPanel.setBackground(Utils.getBackgroundColor());

        // toppings panel components
        veggiesLabel.setForeground(Utils.getTextColor());
        veggiesLabel.setFont(Utils.getTextFont(18));

        // otherPanel
        otherPanel.setBackground(Utils.getBackgroundColor());
        otherTitlePanel.setBackground(Utils.getBackgroundColor());
        otherContentsPanel.setBackground(Utils.getBackgroundColor());

        // other panel components
        otherLabel.setForeground(Utils.getTextColor());
        otherLabel.setFont(Utils.getTextFont(18));


        // buttons panel

        buttonsPanel.setBackground(Utils.getBackgroundColor());
    }
}
