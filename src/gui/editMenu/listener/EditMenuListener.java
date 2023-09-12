package gui.editMenu.listener;

import model.Ingredient;
import model.MenuItem;
import model.PrebuiltPizza;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.EventListener;

public interface EditMenuListener extends EventListener {

    void saveMenuEvent() throws IOException;

    void addIngredientEvent(Ingredient ingredient) throws IOException;

    void addMenuItemEvent(MenuItem menuItem) throws IOException;

    void editMenuCancelEvent() throws IOException, ParseException;

    void addNewPrebuiltPizzaEvent(PrebuiltPizza newPrebuiltPizza);

    void removeIngredientEvent(Ingredient selectedItem);

    void removeMenuItemEvent(MenuItem selectedItem);

    void removePizzaEvent(PrebuiltPizza pizza);
}
