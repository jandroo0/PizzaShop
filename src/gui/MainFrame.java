package gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import Controller.Controller;
import model.Employee;
import org.json.simple.parser.ParseException;

public class MainFrame extends JFrame {

    private static final int WIDTH = 600, HEIGHT = 800; // window size

    private Controller controller;

    // components

    private MenuBar menuBar;
    private TitlePanel titlePanel; // application title panel

    private EmployeeLoginPanel employeeLoginPanel; // panel containing the employee login


    // employee menu dialogs
    private ManageEmployeesDialog employeesDialog; // dialog box for managing employees
    private ManageCustomersDialog customersDialog; // dialog box for managing customers


    // gui.MainFrame Constructor
    public MainFrame() throws IOException, ParseException {
        super("DANS SLICES 0.1");

        setSize(WIDTH, HEIGHT); // set frame size
        setResizable(false); // set resizable true
        setLocationRelativeTo(null); // centers window to screen when opened
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes when clicking 'X'
        setVisible(true); // set window visible


        // initialiaze components

        menuBar = new MenuBar(this);

        titlePanel = new TitlePanel(); // application logo panel
        employeeLoginPanel = new EmployeeLoginPanel();

        employeesDialog = new ManageEmployeesDialog(this);
        customersDialog = new ManageCustomersDialog(this);

        controller = new Controller(); // MVC


        // menu bar
        setJMenuBar(menuBar);

        // handle events

        // employee login event
        this.employeeLoginPanel.setLoginListener(new LoginListener() {
            @Override
            public void loginEvent(LoginEvent e) throws IOException, ParseException {
//                MainFrame.this.controller.employeeLogin(e); == true ->>

            }
        });

        //handles the employee managemement dialog events
        this.getManageEmployeesDialog().setEmployeeListener(new ManageEmployeeListener() {
            @Override
            public void addEmployeeEvent(AddEmployeeEvent e) { // on add employee
                Employee newHire = new Employee(e.getID(), e.getFirstName(), e.getLastName(), e.getAge(), e.getRole(), e.getPhoneNumber(), e.getAddress());
                MainFrame.this.controller.addEmployee(newHire);
                employeesDialog.displayEmployees(MainFrame.this.controller.getEmployees());
            }

            @Override
            public void saveEmployeesEvent() throws IOException {// on save click
                MainFrame.this.controller.saveToFile();
            }
        });




        setLayout(new BorderLayout()); // set layout to a border layout

        add(titlePanel, BorderLayout.NORTH);
        add(employeeLoginPanel, BorderLayout.CENTER);
        load();

    }

    private void load() throws IOException, ParseException {
        MainFrame.this.controller.loadFromFile();
        employeesDialog.displayEmployees(MainFrame.this.controller.getEmployees());
    }


    public ManageEmployeesDialog getManageEmployeesDialog() {
        return employeesDialog;
    }

    public ManageCustomersDialog getManageCustomersDialog() {
        return customersDialog;
    }
}


