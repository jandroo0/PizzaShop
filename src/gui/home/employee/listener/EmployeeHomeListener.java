package gui.home.employee.listener;

import model.Employee;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.EventListener;

public interface EmployeeHomeListener extends EventListener {

    void newOrderEvent(Employee currentEmployee) throws IOException, ParseException;

    void editMenuEvent(Employee currentEmployee) throws ParseException, IOException;
}
