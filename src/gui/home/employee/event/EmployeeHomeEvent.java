package gui.home.employee.event;

import model.Employee;

import java.util.EventObject;

public class EmployeeHomeEvent extends EventObject {

    Employee currentEmployee;

    public EmployeeHomeEvent(Object source, Employee currentEmployee) {
        super(source);

        this.currentEmployee = currentEmployee;
    }

    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employee currentEmployee) {
        this.currentEmployee = currentEmployee;
    }
}
