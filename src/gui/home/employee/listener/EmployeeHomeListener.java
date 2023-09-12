package gui.home.employee.listener;

import gui.home.employee.event.EmployeeHomeEvent;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.EventListener;

public interface EmployeeHomeListener extends EventListener {

    void editMenuEvent(EmployeeHomeEvent e) throws ParseException, IOException;
}
