package gui.home.employee.listener;

import gui.home.employee.event.EmployeeHomeEvent;

import java.util.EventListener;

public interface EmployeeHomeListener extends EventListener {

    void editMenuEvent(EmployeeHomeEvent e);
}
