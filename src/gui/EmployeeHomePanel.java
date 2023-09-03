package gui;

import model.Employee;

import javax.swing.*;


public class EmployeeHomePanel extends JPanel {

    private Employee currentEmployee;

    public EmployeeHomePanel() {
        currentEmployee = null;

    }

    public void setEmployee(Employee e){
        currentEmployee = e;

    }


}
