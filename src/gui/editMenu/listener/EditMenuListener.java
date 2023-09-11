package gui.editMenu.listener;

import model.Ingredient;
import model.MenuItem;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.EventListener;

public interface EditMenuListener extends EventListener {

    void saveMenuEvent() throws IOException;

    void addIngredientEvent(Ingredient ingredient) throws IOException;

    void addMenuItemEvent(MenuItem menuItem) throws IOException;

    void editMenuCancelEvent() throws IOException, ParseException;
}