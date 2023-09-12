package gui.editMenu.panel;

import gui.config.Utils;
import gui.editMenu.listener.EditMenuListener;
import gui.tools.Button;
import gui.tools.EditMenuCustomList;
import gui.tools.PlaceholderTextField;
import model.Ingredient;
import model.MenuItem;
import model.PrebuiltPizza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class PizzaBuilderPanel extends JPanel {

    private final EditMenuCustomList availableIngredientsList;
    private final EditMenuCustomList selectedIngredientsList;
    private final Button removePizzaButton;
    private final EditMenuCustomList pizzasList;
    private final PlaceholderTextField pizzaNameField;
    private final PlaceholderTextField pizzaPriceField;
    private final Button addButton;
    private final Button removeButton;
    private final Button savePizzaButton;
    private LinkedList<Ingredient> originalIngredientsList;

    private JLabel titleLabel;
    private JPanel titlePanel;
    private JPanel contentsPanel;
    private JPanel ingredientsPanel;
    private JPanel pizzaPanel;
    private EditMenuListener editMenuListener;

    public PizzaBuilderPanel() {
        setLayout(new BorderLayout());
        setBackground(Utils.getBackgroundColor());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Utils.getTextColor(), 2, true)));

        // panels
        titlePanel = new JPanel();
        contentsPanel = new JPanel();
        ingredientsPanel = new JPanel();
        pizzaPanel = new JPanel();

        titleLabel = new JLabel("Pizza Builder", SwingConstants.CENTER);

        availableIngredientsList = new EditMenuCustomList(13, new Dimension(220, 160));
        availableIngredientsList.setBorder(BorderFactory.createLineBorder(Utils.getButtonBackgroundColor(), 2, true));

        selectedIngredientsList = new EditMenuCustomList(13, new Dimension(220, 160));
        selectedIngredientsList.setBorder(BorderFactory.createLineBorder(Utils.getButtonBackgroundColor(), 2, true));

        availableIngredientsList.setDisplayInSpecialFormat(true);
        selectedIngredientsList.setDisplayInSpecialFormat(true);

        pizzasList = new EditMenuCustomList(13, new Dimension(150, 100));
        pizzasList.setBorder(BorderFactory.createLineBorder(Utils.getButtonBackgroundColor(), 2, true));

        addButton = new Button("Add", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        removeButton = new Button("Remove", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        savePizzaButton = new Button("BUILD", Utils.getTextFont(16), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        removePizzaButton = new Button("Remove", Utils.getTextFont(14), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        pizzaNameField = new PlaceholderTextField("NEW PIZZA", 120, 24, 16);
        pizzaPriceField = new PlaceholderTextField("$", 50, 24, 16);


        layoutComponents();

        add(contentsPanel, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ingredient selectedIngredient = (Ingredient) availableIngredientsList.getSelectedItem();
                if (selectedIngredient != null) {
                    selectedIngredientsList.addItem(selectedIngredient);
                    availableIngredientsList.removeSelectedItem();

                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ingredient selectedIngredient = (Ingredient) selectedIngredientsList.getSelectedItem();
                if (selectedIngredient != null) {
                    availableIngredientsList.addItem(selectedIngredient);
                    selectedIngredientsList.removeSelectedItem();
                }
            }
        });

        savePizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<MenuItem> selectedIngredients = selectedIngredientsList.getList();
                String pizzaName = pizzaNameField.getText();
                if (!pizzaName.isEmpty() && !selectedIngredients.isEmpty()) {
                    try {
                        float price = Float.parseFloat(pizzaPriceField.getText());
                        String crustType = "Original";
                        LinkedList<Ingredient> ingredients = new LinkedList<Ingredient>();
                        for (MenuItem ingredient : selectedIngredients) {
                            if (ingredient.getCategory().equalsIgnoreCase("crust")) {
                                crustType = ingredient.getItemName();
                            } else ingredients.add((Ingredient) ingredient);
                        }
                        PrebuiltPizza newPrebuiltPizza = new PrebuiltPizza("MENU_ITEM", "PREBUILT", pizzaName, price, crustType, ingredients);
                        selectedIngredientsList.clearList();
                        availableIngredientsList.clearList();
                        for (Ingredient ingredient : originalIngredientsList) {
                            availableIngredientsList.addItem(ingredient);
                        }

                        pizzaNameField.setText("");

                        pizzasList.addItem(newPrebuiltPizza);

                        editMenuListener.addNewPrebuiltPizzaEvent(newPrebuiltPizza);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid price");
                        return;
                    }
                }
            }
        });


        removePizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrebuiltPizza selectedPizza = (PrebuiltPizza) pizzasList.getSelectedItem();
                if (selectedPizza != null) {
                    pizzasList.removeSelectedItem();
                    editMenuListener.removePizzaEvent(selectedPizza);
                }
            }
        });

        applyStyling(); // Apply styling immediately
    }

    private void layoutComponents() {

        // titlePanel
        titlePanel.add(titleLabel);

        // constraints for gridbaglayout
        GridBagConstraints gc = new GridBagConstraints();

        // ingredients panel
        ingredientsPanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 10);
        ingredientsPanel.add(availableIngredientsList, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 10, 0);
        ingredientsPanel.add(selectedIngredientsList, gc);
        gc.gridx = 0;
        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 10);
        ingredientsPanel.add(addButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 0, 0);
        ingredientsPanel.add(removeButton, gc);


        // pizza panel
        pizzaPanel.setLayout(new GridBagLayout());
        pizzaPanel.setBorder(BorderFactory.createEmptyBorder());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.insets = new Insets(0, 0, 10, 0);
        pizzaPanel.add(pizzasList, gc);
        gc.gridy++;
        pizzaPanel.add(removePizzaButton, gc);
        gc.gridy++;
        gc.gridwidth = 1;
        pizzaPanel.add(pizzaNameField, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 5, 10, 0);
        pizzaPanel.add(pizzaPriceField, gc);
        gc.gridx = 0;
        gc.gridy++;
        gc.gridwidth = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        pizzaPanel.add(savePizzaButton, gc);


        // contents panel
        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setVgap(0);
        contentsPanel.setLayout(gridLayout);
        contentsPanel.setBorder(BorderFactory.createEmptyBorder());
        contentsPanel.add(pizzaPanel);
        contentsPanel.add(ingredientsPanel);


        add(titlePanel, BorderLayout.NORTH);
        add(contentsPanel, BorderLayout.CENTER);

    }


    private void applyStyling() {
        // title
        titlePanel.setBackground(Utils.getBackgroundColor());

        titleLabel.setFont(Utils.getTextFont(30));
        titleLabel.setForeground(Utils.getTitleColor());

        // contents panel
        contentsPanel.setBackground(Utils.getBackgroundColor());

        // ingredients panel
        ingredientsPanel.setBackground(Utils.getBackgroundColor());

        // pizzaPanel
        pizzaPanel.setBackground(Utils.getBackgroundColor());
    }

    public void setAvailableIngredients(LinkedList<Ingredient> ingredients) {
        this.originalIngredientsList = ingredients;
        availableIngredientsList.clearList();
        for (Ingredient ingredient : ingredients) {
            availableIngredientsList.addItem(ingredient);
        }
    }

    public void setPizzasList(LinkedList<PrebuiltPizza> pizzas) {
        pizzasList.clearList();
        for (PrebuiltPizza pizza : pizzas) {
            pizzasList.addItem(pizza);
        }
    }

    public LinkedList<MenuItem> getSelectedIngredients() {
        return selectedIngredientsList.getList();
    }

    public LinkedList<MenuItem> getPizzas() {
        return pizzasList.getList();
    }


    public void setEditMenuListener(EditMenuListener listener) {
        this.editMenuListener = listener;
    }
}