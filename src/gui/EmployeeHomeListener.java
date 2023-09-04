package gui;

import java.awt.event.ActionListener;
import java.util.EventListener;

public interface EmployeeHomeListener extends EventListener {

    void editMenuEvent(EmployeeHomeEvent e);
}
