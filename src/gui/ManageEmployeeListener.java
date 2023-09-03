package gui;

import java.io.IOException;
import java.util.EventListener;

public interface ManageEmployeeListener extends EventListener {
    void addEmployeeEvent(AddEmployeeEvent var1);
    void saveEmployeesEvent() throws IOException;
}
