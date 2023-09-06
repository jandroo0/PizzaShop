package gui;

import javax.swing.*;
import java.awt.*;

public class EditMenuPanel extends JPanel {
    private final JPanel crustPanel;
    private final JPanel toppingsPanel;
    private final JPanel otherPanel;
    private final JPanel buttonsPanel;


    private final JLabel crustLabel;
    private final JLabel toppingsLabel;
    private final JLabel meatsLabel;
    private final JLabel veggiesLabel;
    private final JLabel beveragesLabel;
    private final JLabel dessertsLabel;


    private final JTextField crustField;
    private final JTextField meatsField;
    private final JTextField veggiesField;
    private final JTextField beveragesField;
    private final JTextField dessertField;

    private final JComboBox<String> crustBox;
    private final JComboBox<String> meatsBox;
    private final JComboBox<String> veggiesBox;
    private final JComboBox<String> bevBox;
    private final JComboBox<String> dessertBox;

    private final JButton addCrustButton;
    private final JButton removeCrustButton;
    private final JButton addMeatsButton;
    private final JButton removeMeatsButton;
    private final JButton addVeggiesButton;
    private final JButton removeVeggiesButton;
    private final JButton addBevButton;
    private final JButton removeBevButton;
    private final JButton addDessertButton;
    private final JButton removeDessertButton;
    private final JButton saveMenuButton;
    private final JButton revertMenuButton;


    public EditMenuPanel() {

        crustPanel = new JPanel();
        toppingsPanel = new JPanel();
        otherPanel = new JPanel();
        buttonsPanel = new JPanel();

        //labels
        crustLabel = new JLabel("CRUST");

        toppingsLabel = new JLabel("TOPPINGS");
        meatsLabel = new JLabel("MEATS");
        veggiesLabel = new JLabel("VEGGIES");

        beveragesLabel = new JLabel("VEGGIES");
        dessertsLabel = new JLabel("DESSERTS");

        // fields
        crustField = new JTextField();

        meatsField = new JTextField();
        veggiesField = new JTextField();

        beveragesField = new JTextField();
        dessertField = new JTextField();

        // combo boxes
        crustBox = new JComboBox<String>();
        meatsBox = new JComboBox<String>();
        veggiesBox = new JComboBox<String>();
        bevBox = new JComboBox<String>();
        dessertBox = new JComboBox<String>();

        // buttons
        addCrustButton = new JButton("ADD");
        removeCrustButton = new JButton("REMOVE");

        addMeatsButton = new JButton("ADD");
        removeMeatsButton = new JButton("REMOVE");
        addVeggiesButton = new JButton("ADD");
        removeVeggiesButton = new JButton("REMOVE");

        addBevButton = new JButton("ADD");
        removeBevButton = new JButton("REMOVE");
        addDessertButton = new JButton("ADD");
        removeDessertButton = new JButton("REMOVE");

        saveMenuButton = new JButton("SAVE MENU");
        revertMenuButton = new JButton("REVERT MENU");


        setLayout(new GridLayout(2, 2));
        setBorder(BorderFactory.createEmptyBorder());
        add(crustPanel);
        add(toppingsPanel);
        add(otherPanel);
        add(buttonsPanel);
        layoutComponents();
        styling();
    }

    private void layoutComponents() {
        crustPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createLineBorder(Utils.getTextColor(), 1, true)));
        crustPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 0);
        crustPanel.add(crustLabel, gc);
        gc.gridy++;
        crustPanel.add(crustBox, gc);
        gc.gridy++;
        crustPanel.add(crustField, gc);
        gc.gridy++;
        crustPanel.add(addCrustButton, gc);
        gc.gridx++;
        crustPanel.add(removeCrustButton, gc);

    }


    private void styling() {
        crustPanel.setBackground(Utils.getBackgroundColor());
    }
}
