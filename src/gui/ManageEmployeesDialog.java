package gui;

import model.Employee;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.LinkedList;

public class ManageEmployeesDialog extends JDialog {

    private JLabel titleLabel;

    private JPanel titlePanel;
    private JPanel employeeDisplayPanel;
    private EmployeesTextPanel employeeTextPane;
    private JPanel employeeFieldsPanel;

    private PlaceholderTextField idField;
    private PlaceholderTextField firstNameField;
    private PlaceholderTextField lastNameField;
    private PlaceholderTextField ageField;
    private PlaceholderTextField roleField;
    private PlaceholderTextField phoneNumberField;
    private PlaceholderTextField addressField;

    private JButton addEmployeeButton;
    private JButton removeEmployeeButton;

    private ManageEmployeeListener employeeListener;

    public ManageEmployeesDialog(JFrame frame) {
        super(frame, "Manage Employees", true);
        setSize(400,500);
        setResizable(false);
        setLocationRelativeTo(frame);

        // components
        titlePanel = new JPanel();
        employeeTextPane = new EmployeesTextPanel();
        employeeFieldsPanel = new JPanel();
        employeeDisplayPanel = new JPanel();

        titleLabel = new JLabel("MANAGE EMPLOYEES");

        idField = new PlaceholderTextField(" ID");
        firstNameField = new PlaceholderTextField(" First Name");
        lastNameField = new PlaceholderTextField(" Last Name");
        ageField = new PlaceholderTextField(" Age");
        roleField = new PlaceholderTextField(" Job Desc");
        phoneNumberField = new PlaceholderTextField(" Phone #");
        addressField = new PlaceholderTextField(" Address");

        addEmployeeButton = new JButton("ADD");
        removeEmployeeButton = new JButton("TERMINATE");



        // handle events


        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ID = Integer.parseInt(idField.getText());
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String role = roleField.getText();
                String phoneNumber = phoneNumberField.getText();
                String address = addressField.getText();

                AddEmployeeEvent event = new AddEmployeeEvent(e,ID, firstName, lastName, age, role, phoneNumber, address);
                if(ManageEmployeesDialog.this.employeeListener != null) {
                    try {
                        ManageEmployeesDialog.this.employeeListener.addEmployeeEvent(event);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }

            }
        });

        addEmployeeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addEmployeeButton.setBackground(Utils.getButtonHoverColor());
                addEmployeeButton.setForeground(Utils.getButtonBackgroundColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                addEmployeeButton.setBackground(Utils.getButtonBackgroundColor());
                addEmployeeButton.setForeground(Utils.getTextColor());
            }
        });

        layoutComponents();
        styling();

    }


    private void layoutComponents() {
        titlePanel.add(titleLabel);


        employeeDisplayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = (new GridBagConstraints());
        gc.gridx = 0;
        gc.gridy = 0;
        employeeDisplayPanel.add(employeeTextPane, gc); // add display text pane to the pane
        gc.insets = new Insets(10,0,0,0);
        gc.gridy++;
        employeeDisplayPanel.add(removeEmployeeButton, gc);

        // employee fields panel(right panel) ----------------------
        Border border = BorderFactory.createEmptyBorder(90,10,50,50);
        employeeFieldsPanel.setLayout(new GridBagLayout());
        employeeFieldsPanel.setBorder(border);
        gc.insets = new Insets(0,0,10,0);
        gc.gridy = 0;
        gc.gridx = 0;
        gc.weightx = .1;
        employeeFieldsPanel.add(idField, gc);
        gc.gridy++;
        employeeFieldsPanel.add(firstNameField, gc);
        gc.gridy++;
        employeeFieldsPanel.add(lastNameField, gc);
        gc.gridy++;
        employeeFieldsPanel.add(ageField, gc);
        gc.gridy++;
        employeeFieldsPanel.add(roleField, gc);
        gc.gridy++;
        employeeFieldsPanel.add(phoneNumberField, gc);
        gc.gridy++;
        employeeFieldsPanel.add(addressField, gc);
        gc.gridy++;
        employeeFieldsPanel.add(addEmployeeButton, gc);



        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(employeeDisplayPanel, BorderLayout.WEST);
        add(employeeFieldsPanel, BorderLayout.CENTER);

    }

    void setEmployeeListener(ManageEmployeeListener listener) {this.employeeListener = listener;}

    private void styling() {

        // panels
        employeeFieldsPanel.setBackground(Utils.getBackgroundColor());
        employeeDisplayPanel.setBackground(Utils.getBackgroundColor());
        titlePanel.setBackground(Utils.getBackgroundColor());

        //fields

        //buttons
        addEmployeeButton.setFont(new Font(Utils.getFontString(), Font.BOLD, 14));
        addEmployeeButton.setBackground(Utils.getButtonBackgroundColor());
        addEmployeeButton.setForeground(Utils.getTextColor());
        addEmployeeButton.setBorder(BorderFactory.createEmptyBorder(5,8,5,8));

        removeEmployeeButton.setFont(new Font(Utils.getFontString(), Font.BOLD, 14));
        removeEmployeeButton.setBackground(Utils.getButtonBackgroundColor());
        removeEmployeeButton.setForeground(Utils.getTextColor());
        removeEmployeeButton.setBorder(BorderFactory.createEmptyBorder(5,8,5,8));
    }
    // refreshes employees list on text panel
    public void displayEmployees(LinkedList<Employee> employees) {
        employeeTextPane.displayEmployees(employees);
    }

}