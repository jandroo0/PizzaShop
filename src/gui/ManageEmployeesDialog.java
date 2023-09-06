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

    private final Label titleLabel;

    private final JPanel titlePanel;
    private final JPanel employeeDisplayPanel;
    private final EmployeesTextPanel employeeTextPane;
    private final JPanel employeeFieldsPanel;
    private final JPanel buttonsPanel;

    private final PlaceholderTextField idField;
    private final PlaceholderTextField firstNameField;
    private final PlaceholderTextField lastNameField;
    private final PlaceholderTextField ageField;
    private final PlaceholderTextField roleField;
    private final PlaceholderTextField phoneNumberField;
    private final PlaceholderTextField addressField;
    private final JCheckBox isAdminBox;

    //remove employee combo box
    private final JComboBox<String> removeEmployeeBox;
    private final DefaultComboBoxModel<String> employeeModel;

    // manage employee buttons
    private final Button addEmployeeButton;
    private final Button removeEmployeeButton;
    private final DefaultListCellRenderer listRenderer;

    // lower buttons
    private final Button saveButton;
    private final Button cancelButton;

    private ManageEmployeeListener employeeListener;

    public ManageEmployeesDialog(Frame frame) {
        super(frame, "Manage Employees", true);
        setSize(400, 500);
        setResizable(false);
        setLocationRelativeTo(frame);

        // components
        titlePanel = new JPanel();
        employeeTextPane = new EmployeesTextPanel();
        employeeFieldsPanel = new JPanel();
        employeeDisplayPanel = new JPanel();
        buttonsPanel = new JPanel();

        titleLabel = new Label("MANAGEMENT", 28);

        idField = new PlaceholderTextField(" ID");
        firstNameField = new PlaceholderTextField(" First Name");
        lastNameField = new PlaceholderTextField(" Last Name");
        ageField = new PlaceholderTextField(" Age");
        roleField = new PlaceholderTextField(" Job");
        phoneNumberField = new PlaceholderTextField(" Phone #");
        addressField = new PlaceholderTextField(" Address");

        isAdminBox = new JCheckBox("Administrator");

        addEmployeeButton = new Button("ADD", Utils.getTextFont(), Utils.getTextColor(), Utils.getButtonBackgroundColor(),
                Utils.getButtonHoverColor(), Utils.getButtonBorder());
        removeEmployeeButton = new Button("REMOVE", Utils.getTextFont(), Utils.getTextColor(), Utils.getButtonBackgroundColor(),
                Utils.getButtonHoverColor(), Utils.getButtonBorder());

        // remove student combo box
        removeEmployeeBox = new JComboBox<String>();
        // remove student combo box model
        employeeModel = new DefaultComboBoxModel<String>();
        removeEmployeeBox.setModel(employeeModel);

        //list renderer for combo box
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        removeEmployeeBox.setRenderer(listRenderer);

        saveButton = new Button("SAVE", Utils.getTextFont(), Utils.getTextColor(), Utils.getButtonBackgroundColor(),
                Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        cancelButton = new Button("CANCEL", Utils.getTextFont(), Utils.getTextColor(), Utils.getButtonBackgroundColor(),
                Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));


        // handle events

        // add employee listener
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFields()) {
                    String ID = idField.getText();
                    if (ID.length() == 6) { // ID can only be 6 digits
                        String firstName = firstNameField.getText();
                        String lastName = lastNameField.getText();
                        String age = ageField.getText();
                        String role = roleField.getText();
                        String phoneNumber = phoneNumberField.getText();
                        String address = addressField.getText();

                        boolean isAdmin = isAdminBox.isSelected();

                        AddEmployeeEvent event = new AddEmployeeEvent(e, isAdmin, ID, firstName, lastName, age, role, phoneNumber, address);
                        if (ManageEmployeesDialog.this.employeeListener != null) {
                            try {
                                ManageEmployeesDialog.this.employeeListener.addEmployeeEvent(event);
                            } catch (Exception ex1) {
                                ex1.printStackTrace();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "ID must be 6 digits.", "Invalid ID", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Info fields can not be left empty", "Empty Fields", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        //remove employee button
        removeEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = (String) removeEmployeeBox.getSelectedItem();

                if (employeeListener != null) {
                    employeeListener.removeEmployeeEvent(ID);
                }
            }
        });

        // save employee listener
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ManageEmployeesDialog.this.employeeListener != null) {
                    try {
                        ManageEmployeesDialog.this.employeeListener.saveEmployeesEvent();
                    } catch (Exception ex1) {
                        ex1.printStackTrace();
                    }
                }

            }
        });

        layoutComponents();
        styling();
    }

    private void layoutComponents() {
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));

        employeeDisplayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = (new GridBagConstraints());
        gc.gridx = 0;
        gc.gridy = 0;
        employeeDisplayPanel.add(employeeTextPane, gc); // add display text pane to the pane
        gc.insets = new Insets(10, 0, 0, 0);
        gc.gridy++;
        employeeDisplayPanel.add(removeEmployeeBox, gc);
        gc.gridy++;
        employeeDisplayPanel.add(removeEmployeeButton, gc);

        // employee fields panel(right panel) ----------------------
        Border fieldsBorder = BorderFactory.createEmptyBorder(30, 0, 0, 30);
        employeeFieldsPanel.setLayout(new GridBagLayout());
        employeeFieldsPanel.setBorder(fieldsBorder);
        gc.insets = new Insets(8, 0, 0, 0);
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
        employeeFieldsPanel.add(isAdminBox, gc);
        gc.gridy++;
        gc.insets = new Insets(10, 0, 0, 0);
        employeeFieldsPanel.add(addEmployeeButton, gc);


        Border buttonsBorder = BorderFactory.createEmptyBorder(10, 0, 0, 0);
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBorder(buttonsBorder);
        gc.gridy = 0;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 20, 20);
        buttonsPanel.add(cancelButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 20, 20, 0);
        buttonsPanel.add(saveButton, gc);

        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(employeeDisplayPanel, BorderLayout.WEST);
        add(employeeFieldsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    void setEmployeeListener(ManageEmployeeListener listener) {
        this.employeeListener = listener;
    }

    private void styling() {

        // panels
        employeeFieldsPanel.setBackground(Utils.getBackgroundColor());
        employeeDisplayPanel.setBackground(Utils.getBackgroundColor());
        buttonsPanel.setBackground(Utils.getBackgroundColor());
        titlePanel.setBackground(Utils.getBackgroundColor());

        // is admin checkbox
        isAdminBox.setBackground(Utils.getBackgroundColor());
        isAdminBox.setBorder(BorderFactory.createEmptyBorder());
        isAdminBox.setForeground(Utils.getTextColor());
        isAdminBox.setFont(Utils.getTextFont(12));

        isAdminBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                isAdminBox.setForeground(Utils.getButtonBackgroundColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (!isAdminBox.isSelected()) {
                    isAdminBox.setForeground(Utils.getTextColor());
                }
            }
        });

    }

    // for each placeholder textField, check whether it still contains the placeholder, or nothing
    private boolean checkFields() {
        if ((idField.getText().equals(idField.getPlaceHolderText()) || idField.getText().equals(""))) {
            return false;
        } else if ((firstNameField.getText().equals(firstNameField.getPlaceHolderText()) || firstNameField.getText().equals(""))) {
            return false;
        } else if ((lastNameField.getText().equals(lastNameField.getPlaceHolderText()) || lastNameField.getText().equals(""))) {
            return false;
        } else if ((ageField.getText().equals(ageField.getPlaceHolderText()) || ageField.getText().equals(""))) {
            return false;
        } else if ((roleField.getText().equals(roleField.getPlaceHolderText()) || roleField.getText().equals(""))) {
            return false;
        } else if ((phoneNumberField.getText().equals(phoneNumberField.getPlaceHolderText()) || phoneNumberField.getText().equals(""))) {
            return false;
        } else
            return !addressField.getText().equals(addressField.getPlaceHolderText()) && !addressField.getText().equals("");
    }

    // refreshes employees list on text panel
    public void displayEmployees(LinkedList<Employee> employees) {
        employeeTextPane.displayEmployees(employees);
    }

    public void setComboBox(LinkedList<Employee> employees) {
        employeeModel.removeAllElements();
        for (Employee employee : employees) {
            employeeModel.addElement(employee.getID());
        }
    }

}
