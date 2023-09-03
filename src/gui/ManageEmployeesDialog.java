package gui;

import model.Employee;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.LinkedList;

public class ManageEmployeesDialog extends JDialog {

    private JLabel titleLabel;

    private JPanel titlePanel;
    private EmployeesTextPanel employeeListPanel;
    private JPanel employeeFieldsPanel;

    private PlaceholderTextField idField;
    private PlaceholderTextField firstNameField;
    private PlaceholderTextField lastNameField;
    private PlaceholderTextField ageField;
    private PlaceholderTextField roleField;
    private PlaceholderTextField phoneNumberField;
    private PlaceholderTextField addressField;

    public ManageEmployeesDialog(JFrame frame) {
        super(frame, "Manage Employees", true);
        setSize(400,500);
        setLocationRelativeTo(frame);

        // components
        titleLabel = new JLabel("MANAGE EMPLOYEES");



        employeeListPanel = new EmployeesTextPanel();
        employeeFieldsPanel = new JPanel();

        idField = new PlaceholderTextField();
        firstNameField = new PlaceholderTextField();
        lastNameField = new PlaceholderTextField();
        ageField = new PlaceholderTextField();
        roleField = new PlaceholderTextField();
        phoneNumberField = new PlaceholderTextField();
        addressField = new PlaceholderTextField();

        layoutComponents();
        styling();

    }


    private void layoutComponents() {
        titlePanel = new JPanel();
        employeeListPanel = new EmployeesTextPanel();
        employeeFieldsPanel = new JPanel();

        Border border = BorderFactory.createEmptyBorder(10,10,10,10);

        setLayout(new BorderLayout());



        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
        add(employeeListPanel, BorderLayout.WEST);
        add(employeeFieldsPanel, BorderLayout.CENTER);



    }

    private void styling() {
        employeeFieldsPanel.setBackground(Utils.getBackgroundColor());
        titlePanel.setBackground(Utils.getBackgroundColor());
    }
    // refreshes employees list on text panel
    public void displayEmployees(LinkedList<Employee> employees) {
        employeeListPanel.displayEmployees(employees);
    }

}
