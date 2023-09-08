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
    private final JPanel toppingsPanel;
    private final JPanel toppingsTitlePanel;
    private final JPanel toppingsContentsPanel;

    // other panels
    private final JPanel otherPanel;
    private final JPanel otherTitlePanel;
    private final JPanel otherContentsPanel;


    private final JLabel crustLabel;
    private final JLabel toppingsLabel;
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
    // title label
    private JLabel titleLabel;


    public EditMenuPanel() {

        // main title panel
        titlePanel = new JPanel();

        // edit menu panels container panel
        editMenuContentPanel = new JPanel();

        //buttons panel
        buttonsPanel = new JPanel();


        // crust panels
        crustPanel = new JPanel();
        crustTitlePanel = new JPanel();
        crustContentsPanel = new JPanel();
        crustButtonsPanel = new JPanel();

        // toppings panel
        toppingsPanel = new JPanel();
        toppingsTitlePanel = new JPanel();
        toppingsContentsPanel = new JPanel();

        // other panels
        otherPanel = new JPanel();
        otherTitlePanel = new JPanel();
        otherContentsPanel = new JPanel();

        //labels
        titleLabel = new JLabel("EDIT MENU");

        crustLabel = new JLabel("CRUST");

        toppingsLabel = new JLabel("TOPPINGS");

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
        addCrustButton = new Button("ADD", Utils.getTextFont(18), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        removeCrustButton = new Button("REMOVE", Utils.getTextFont(18), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        addMeatsButton = new Button("ADD", Utils.getTextFont(18), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        removeMeatsButton = new Button("REMOVE", Utils.getTextFont(18), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        addVeggiesButton = new Button("ADD", Utils.getTextFont(18), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        removeVeggiesButton = new Button("REMOVE", Utils.getTextFont(18), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        addBevButton = new Button("ADD", Utils.getTextFont(18), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        removeBevButton = new Button("REMOVE", Utils.getTextFont(18), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        addDessertButton = new Button("ADD", Utils.getTextFont(18), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        removeDessertButton = new Button("REMOVE", Utils.getTextFont(18), Utils.getTextColor(),
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
        editMenuContentPanel.setLayout(new GridLayout(2, 2));
        editMenuContentPanel.setBorder(BorderFactory.createEmptyBorder());

        editMenuContentPanel.add(crustPanel);
        editMenuContentPanel.add(toppingsPanel);
        editMenuContentPanel.add(otherPanel);

        titlePanel.add(titleLabel);

        GridBagConstraints gc = new GridBagConstraints();

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
        toppingsPanel.setLayout(new BorderLayout());
        toppingsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10),
                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));
        toppingsPanel.add(toppingsTitlePanel, BorderLayout.NORTH);
        toppingsPanel.add(toppingsContentsPanel, BorderLayout.CENTER);


        // toppings title panel
        toppingsTitlePanel.add(toppingsLabel);
        toppingsTitlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));


        // toppings content panel
        toppingsContentsPanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 10);
        toppingsContentsPanel.add(meatsBox, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 10, 0);
        toppingsContentsPanel.add(meatsField, gc);
        gc.gridy++;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 10);
        toppingsContentsPanel.add(removeMeatsButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 0, 0);
        toppingsContentsPanel.add(addMeatsButton, gc);
        gc.gridx = 0;
        gc.gridy++;
        gc.insets = new Insets(10, 0, 0, 10);
        toppingsContentsPanel.add(veggiesBox, gc);
        gc.gridx++;
        gc.insets = new Insets(10, 10, 0, 0);
        toppingsContentsPanel.add(veggiesField, gc);
        gc.gridx = 0;
        gc.gridy++;
        gc.insets = new Insets(10, 0, 0, 10);
        toppingsContentsPanel.add(removeVeggiesButton, gc);
        gc.gridx++;
        gc.insets = new Insets(10, 10, 0, 0);
        toppingsContentsPanel.add(addVeggiesButton, gc);


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
        toppingsPanel.setBackground(Utils.getBackgroundColor());
        toppingsTitlePanel.setBackground(Utils.getBackgroundColor());
        toppingsContentsPanel.setBackground(Utils.getBackgroundColor());

        // toppings panel components
        toppingsLabel.setForeground(Utils.getTextColor());
        toppingsLabel.setFont(Utils.getTextFont(18));

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
