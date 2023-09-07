package gui.dialog.employee.management.listener;

import gui.dialog.employee.management.event.AddEmployeeEvent;

import java.io.IOException;
import java.util.EventListener;

public interface ManageEmployeeListener extends EventListener {
    void addEmployeeEvent(AddEmployeeEvent var1);

    void removeEmployeeEvent(String ID);

    void saveEmployeesEvent() throws IOException;
}
