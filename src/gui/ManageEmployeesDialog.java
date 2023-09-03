package gui;

import model.Employee;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class ManageEmployeesDialog extends JDialog {

    private JLabel titleLabel;

    private JPanel titlePanel;
    private JPanel employeeDisplayPanel;
    private EmployeesTextPanel employeeTextPane;
    private JPanel employeeFieldsPanel;
    private JPanel buttonsPanel;

    private PlaceholderTextField idField;
    private PlaceholderTextField firstNameField;
    private PlaceholderTextField lastNameField;
    private PlaceholderTextField ageField;
    private PlaceholderTextField roleField;
    private PlaceholderTextField phoneNumberField;
    private PlaceholderTextField addressField;
    private JCheckBox isAdminBox;

    //remove employee combo box
    private JComboBox<String> removeEmployeeBox;
    private DefaultComboBoxModel<String> employeeModel;

    // manage employee buttons
    private JButton addEmployeeButton;
    private JButton removeEmployeeButton;
    private DefaultListCellRenderer listRenderer;

    // lower buttons
    private JButton saveButton;
    private JButton cancelButton;

    private ManageEmployeeListener employeeListener;

    public ManageEmployeesDialog(Frame frame) {
        super(frame, "Manage Employees", true);
        setSize(400,500);
        setResizable(false);
        setLocationRelativeTo(frame);

        // components
        titlePanel = new JPanel();
        employeeTextPane = new EmployeesTextPanel();
        employeeFieldsPanel = new JPanel();
        employeeDisplayPanel = new JPanel();
        buttonsPanel = new JPanel();

        titleLabel = new JLabel("MANAGE EMPLOYEES");

        idField = new PlaceholderTextField(" ID");
        firstNameField = new PlaceholderTextField(" First Name");
        lastNameField = new PlaceholderTextField(" Last Name");
        ageField = new PlaceholderTextField(" Age");
        roleField = new PlaceholderTextField(" Job Desc");
        phoneNumberField = new PlaceholderTextField(" Phone #");
        addressField = new PlaceholderTextField(" Address");

        isAdminBox = new JCheckBox("Administrator");

        addEmployeeButton = new JButton("ADD");
        removeEmployeeButton = new JButton("REMOVE");

        // remove student combo box
        removeEmployeeBox = new JComboBox<String>();
        // remove student combo box model
        employeeModel = new DefaultComboBoxModel<String>();
        removeEmployeeBox.setModel(employeeModel);

        //list renderer for combo box
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        removeEmployeeBox.setRenderer(listRenderer);



        saveButton = new JButton("SAVE");
        cancelButton = new JButton("CANCEL");


        // handle events

        // add employee listener
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = idField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String age = ageField.getText();
                String role = roleField.getText();
                String phoneNumber = phoneNumberField.getText();
                String address = addressField.getText();

                boolean isAdmin = isAdminBox.isSelected();

                AddEmployeeEvent event = new AddEmployeeEvent(e,isAdmin, ID, firstName, lastName, age, role, phoneNumber, address);
                if(ManageEmployeesDialog.this.employeeListener != null) {
                    try {
                        ManageEmployeesDialog.this.employeeListener.addEmployeeEvent(event);
                    } catch (Exception ex1) {
                        ex1.printStackTrace();
                    }
                }

            }
        });

        //remove employee button

        removeEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = (String) removeEmployeeBox.getSelectedItem();

                if(employeeListener != null) {
                    employeeListener.removeEmployeeEvent(ID);
                }
            }
        });


        // save employee listener
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ManageEmployeesDialog.this.employeeListener != null) {
                    try {
                        ManageEmployeesDialog.this.employeeListener.saveEmployeesEvent();
                    } catch (Exception ex1) {
                        ex1.printStackTrace();
                    }
                }

            }
        });




        // TODO---- make JButton Class
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
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20,30,0,0));


        employeeDisplayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = (new GridBagConstraints());
        gc.gridx = 0;
        gc.gridy = 0;
        employeeDisplayPanel.add(employeeTextPane, gc); // add display text pane to the pane
        gc.insets = new Insets(10,0,0,0);
        gc.gridy++;
        employeeDisplayPanel.add(removeEmployeeBox, gc);
        gc.gridy++;
        employeeDisplayPanel.add(removeEmployeeButton, gc);

        // employee fields panel(right panel) ----------------------
        Border fieldsBorder = BorderFactory.createEmptyBorder(30,0,0,30);
        employeeFieldsPanel.setLayout(new GridBagLayout());
        employeeFieldsPanel.setBorder(fieldsBorder);
        gc.insets = new Insets(8,0,0,0);
        gc.gridy = 0;
        gc.gridx = 0;
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
        employeeFieldsPanel.add(isAdminBox,gc);
        gc.gridy++;
        gc.insets = new Insets(10,0,0,0);
        employeeFieldsPanel.add(addEmployeeButton, gc);


        Border buttonsBorder = BorderFactory.createEmptyBorder(10,0,10,0);
        buttonsPanel.setLayout(new GridBagLayout());
        gc.gridy = 0;
        gc.gridx = 0;
        gc.insets = new Insets(0,0,20,20);
        buttonsPanel.add(cancelButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0,20,20,0);
        buttonsPanel.add(saveButton, gc);




        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(employeeDisplayPanel, BorderLayout.WEST);
        add(employeeFieldsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

    }

    void setEmployeeListener(ManageEmployeeListener listener) {this.employeeListener = listener;}

    private void styling() {

        // panels
        employeeFieldsPanel.setBackground(Utils.getBackgroundColor());
        employeeDisplayPanel.setBackground(Utils.getBackgroundColor());
        buttonsPanel.setBackground(Utils.getBackgroundColor());
        titlePanel.setBackground(Utils.getBackgroundColor());

        //fields

        isAdminBox.setBackground(Utils.getBackgroundColor());
        isAdminBox.setBorder(BorderFactory.createEmptyBorder());

        //buttons
        addEmployeeButton.setFont(new Font(Utils.getFontString(), Font.BOLD, 14));
        addEmployeeButton.setBackground(Utils.getButtonBackgroundColor());
        addEmployeeButton.setForeground(Utils.getTextColor());
        addEmployeeButton.setBorder(BorderFactory.createEmptyBorder(5,8,5,8));

        removeEmployeeButton.setFont(new Font(Utils.getFontString(), Font.BOLD, 14));
        removeEmployeeButton.setBackground(Utils.getButtonBackgroundColor());
        removeEmployeeButton.setForeground(Utils.getTextColor());
        removeEmployeeButton.setBorder(BorderFactory.createEmptyBorder(5,8,5,8));


        saveButton.setFont(new Font(Utils.getFontString(), Font.BOLD, 14));
        saveButton.setBackground(Utils.getButtonBackgroundColor());
        saveButton.setForeground(Utils.getTextColor());
        saveButton.setBorder(BorderFactory.createEmptyBorder(5,8,5,8));

        cancelButton.setFont(new Font(Utils.getFontString(), Font.BOLD, 14));
        cancelButton.setBackground(Utils.getButtonBackgroundColor());
        cancelButton.setForeground(Utils.getTextColor());
        cancelButton.setBorder(BorderFactory.createEmptyBorder(5,8,5,8));
    }
    // refreshes employees list on text panel
    public void displayEmployees(LinkedList<Employee> employees) {
        employeeTextPane.displayEmployees(employees);
    }
    public void setComboBox(LinkedList<Employee> employees) {
        employeeModel.removeAllElements();
        for(Employee employee : employees) {
            employeeModel.addElement(employee.getID());
        }
    }

}
