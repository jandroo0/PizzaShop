package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

import Controller.Controller;
import model.Customer;
import model.Employee;
import org.json.simple.parser.ParseException;

public class MainFrame extends JFrame {

    private static final int WIDTH = 600, HEIGHT = 800; // window size

    private Controller controller;

    // components

    private MenuBar menuBar;
    private TitlePanel titlePanel; // application title panel

    //container panel
    private JPanel containerPanel; // contains loginPanels and homePanels

    //login panels
    private JPanel loginPanels;
    private EmployeeLoginPanel employeeLoginPanel; // panel containing the employee login
    private CustomerLoginPanel customerLoginPanel; // panel containing the employee login

    private JPanel homePanels;

    private EmployeeHomePanel employeeHomePanel;
    private CustomerHomePanel customerHomePanel;


    // employee menu dialogs
    private ManageEmployeesDialog employeesDialog; // dialog box for managing employees


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

        //login panels
        loginPanels = new JPanel(new CardLayout()); // create login panel with cardLayout --- allows interchange between panels
        employeeLoginPanel = new EmployeeLoginPanel();
        customerLoginPanel = new CustomerLoginPanel();

        loginPanels.add(customerLoginPanel, "CUSTOMER_LOGIN");
        loginPanels.add(employeeLoginPanel, "EMPLOYEE_LOGIN");

        //home panels
        homePanels = new JPanel(new CardLayout());
        employeeHomePanel = new EmployeeHomePanel();
        customerHomePanel = new CustomerHomePanel();

        homePanels.add(employeeHomePanel, "EMPLOYEE_HOME");
        homePanels.add(customerHomePanel, "CUSTOMER_HOME");

        // container panel for home and login panels
        containerPanel = new JPanel(new CardLayout());
        containerPanel.add(homePanels, "HOME");
        containerPanel.add(loginPanels, "LOGIN");

        CardLayout cl = (CardLayout) containerPanel.getLayout();
        cl.show(containerPanel, "LOGIN");

        // dialog
        employeesDialog = new ManageEmployeesDialog(this);

        controller = new Controller(); // MVC


        // set employee login panel invisible by default
        // menu bar
        setJMenuBar(menuBar);

        // handle events

        // employee login event
        this.employeeLoginPanel.setLoginListener(new LoginListener() {
            @Override
            public void loginEvent(LoginEvent e){ // login event
                if(MainFrame.this.controller.employeeLogin(e) != null) { // if the returned employee is not null
                    Employee employee = MainFrame.this.controller.employeeLogin(e); // set employee to logged in employee
                    if(employee.isAdmin()) menuBar.isAdmin();//if logged in employee is admin, call isAdmin in menu bar to add manage employee menuItem for ADMIN ACCESS
                    menuBar.employeeView(); // set menuBar to employee view
                    employeeHomePanel.setEmployee(employee); // set the employeeHomePanel employee

                    cl.show(containerPanel, "HOME"); // switch to the homePanels

                    CardLayout hl = (CardLayout) homePanels.getLayout();
                    hl.show(homePanels, "EMPLOYEE_HOME"); // in the homePanels switch to the employeeHome

                    System.out.println(employee.getID() + ":" + employee.getFirstName() + " LOGGED IN"); // print log in messsage

                } else {
                    JOptionPane.showMessageDialog(MainFrame.this,"Invalid ID", "Invalid ID", JOptionPane.ERROR_MESSAGE); // if unknown ID show error message
                }
            }
        });

        //handles the employee management dialog events
        this.getManageEmployeesDialog().setEmployeeListener(new ManageEmployeeListener() {
            @Override
            public void addEmployeeEvent(AddEmployeeEvent e) { // on add employee
                Employee newHire = new Employee(e.isAdmin(), e.getID(), e.getFirstName(), e.getLastName(), e.getAge(), e.getRole(), e.getPhoneNumber(), e.getAddress());
                MainFrame.this.controller.addEmployee(newHire);
                employeesDialog.displayEmployees(MainFrame.this.controller.getEmployees());
                employeesDialog.setComboBox(MainFrame.this.controller.getEmployees());
            }

            @Override
            public void removeEmployeeEvent(String ID) {
                if(ID == null) {
                    JOptionPane.showMessageDialog(MainFrame.this,"Select an Employee.", "No Employee Selected", JOptionPane.ERROR_MESSAGE);
                }else {
                    MainFrame.this.controller.removeEmployee(ID);
                    employeesDialog.displayEmployees(MainFrame.this.controller.getEmployees());
                    employeesDialog.setComboBox(MainFrame.this.controller.getEmployees());
                }
            }

            @Override
            public void saveEmployeesEvent() throws IOException {// on save click
                MainFrame.this.controller.saveToFile();
            }
        });




        setLayout(new BorderLayout()); // set layout to a border layout

        add(titlePanel, BorderLayout.NORTH);
        add(containerPanel, BorderLayout.CENTER);
        load();

    }

    private void load() throws IOException, ParseException {
        MainFrame.this.controller.loadFromFile();
        employeesDialog.displayEmployees(MainFrame.this.controller.getEmployees());
        employeesDialog.setComboBox(MainFrame.this.controller.getEmployees());
    }


    public JPanel getContainerPanel() {
        return containerPanel;
    }
    public JPanel getLoginPanels() {
        return loginPanels;
    }



    public CustomerLoginPanel getCustomerLoginPanel() {
        return customerLoginPanel;
    }

    public EmployeeHomePanel getEmployeeHomePanel() {
        return employeeHomePanel;
    }

    public ManageEmployeesDialog getManageEmployeesDialog() {
        return employeesDialog;
    }

}


