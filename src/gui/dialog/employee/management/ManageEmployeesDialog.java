package gui.dialog.employee.management;

import gui.config.Utils;
import gui.dialog.employee.management.event.AddEmployeeEvent;
import gui.dialog.employee.management.listener.ManageEmployeeListener;
import gui.tools.Button;
import gui.tools.CustomList;
import gui.tools.Label;
import gui.tools.PlaceholderTextField;
import model.Employee;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class ManageEmployeesDialog extends JDialog {

    private final Label titleLabel;

    private final JPanel titlePanel;
    private final JPanel contentsPanel;
    private final JPanel employeeDisplayPanel;
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

    // manage employee buttons
    private final Button addEmployeeButton;
    private final Button removeEmployeeButton;
    private final DefaultListCellRenderer listRenderer;

    // lower buttons
    private final Button saveButton;
    private final Button cancelButton;
    private final CustomList<String> employeeList;
    private ManageEmployeeListener employeeListener;

    public ManageEmployeesDialog(Frame frame) {
        super(frame, "Manage Employees", true);
        setSize(400, 500);
        setResizable(false);
        setLocationRelativeTo(frame);

        // components
        titlePanel = new JPanel();
        contentsPanel = new JPanel();
        employeeFieldsPanel = new JPanel();
        employeeDisplayPanel = new JPanel();
        buttonsPanel = new JPanel();

        titleLabel = new Label("MANAGEMENT", 28);

        idField = new PlaceholderTextField(" ID", 80, 22, 14);
        firstNameField = new PlaceholderTextField(" First Name", 80, 22, 14);
        lastNameField = new PlaceholderTextField(" Last Name", 80, 22, 14);
        ageField = new PlaceholderTextField(" Age", 80, 22, 14);
        roleField = new PlaceholderTextField(" Job", 80, 22, 14);
        phoneNumberField = new PlaceholderTextField(" Phone #", 80, 22, 14);
        addressField = new PlaceholderTextField(" Address", 80, 22, 14);

        isAdminBox = new JCheckBox("Administrator");

        addEmployeeButton = new Button("ADD", Utils.getTextFont(), Utils.getTextColor(), Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), Utils.getButtonBorder());
        removeEmployeeButton = new Button("REMOVE", Utils.getTextFont(), Utils.getTextColor(), Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), Utils.getButtonBorder());

        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

        saveButton = new Button("SAVE", Utils.getTextFont(), Utils.getTextColor(), Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));
        cancelButton = new Button("CANCEL", Utils.getTextFont(), Utils.getTextColor(), Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        employeeList = new CustomList<String>(14, new Dimension(80, 150));

        employeeList.setPreferredSize(new Dimension(80, 150));

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

        // remove employee button
        removeEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = employeeList.getSelectedValue();

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

        // cancel button listener
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageEmployeesDialog.this.employeeListener.cancelEmployeesEvent();
            }
        });

        layoutComponents();
        styling();
    }

    private void layoutComponents() {
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));

        // contents panel
        contentsPanel.setLayout(new GridLayout(1, 2));
        contentsPanel.setBorder(BorderFactory.createEmptyBorder());
        contentsPanel.add(employeeDisplayPanel);
        contentsPanel.add(employeeFieldsPanel);

        employeeDisplayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = (new GridBagConstraints());
        gc.gridx = 0;
        gc.gridy = 0;
        employeeDisplayPanel.add(employeeList, gc); // add display list to the panel
        gc.insets = new Insets(10, 0, 0, 0);
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
        add(contentsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void setEmployeeListener(ManageEmployeeListener listener) {
        this.employeeListener = listener;
    }

    private void styling() {

        // panels
        employeeFieldsPanel.setBackground(Utils.getBackgroundColor());
        employeeDisplayPanel.setBackground(Utils.getBackgroundColor());
        buttonsPanel.setBackground(Utils.getBackgroundColor());
        titlePanel.setBackground(Utils.getBackgroundColor());

        // id field int only and 6-digit limit from doc filter
        PlainDocument idFieldDoc = (PlainDocument) idField.getDocument();
//        idFieldDoc.setDocumentFilter(new MyIntFilter(6));

        // phone # field int only and 10-digit limit from doc filter
        PlainDocument phoneFieldDoc = (PlainDocument) phoneNumberField.getDocument();
        phoneFieldDoc.setDocumentFilter(new PlaceholderTextField.MyIntFilter(10));

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
        if (idField.getText().equals("")) {
            return false;
        } else if (firstNameField.getText().equals("")) {
            return false;
        } else if (lastNameField.getText().equals("")) {
            return false;
        } else if (ageField.getText().equals("")) {
            return false;
        } else if (roleField.getText().equals("")) {
            return false;
        } else if (phoneNumberField.getText().equals("")) {
            return false;
        } else return !addressField.getText().equals("");
    }

    // refreshes employees list on text panel
    public void displayEmployees(LinkedList<Employee> employees) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Employee employee : employees) {
            listModel.addElement(employee.getID());
        }
        employeeList.setModel(listModel);
    }
}
