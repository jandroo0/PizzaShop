package gui.home.employee.listener;

import gui.home.employee.event.EditMenuEvent;

import java.util.EventListener;

public interface EditMenuListener extends EventListener {

    void onEditMenu(EditMenuEvent event);
}
