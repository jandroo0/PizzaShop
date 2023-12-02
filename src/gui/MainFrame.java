package gui;

import Controller.Controller;
import gui.dialog.employee.management.ManageEmployeesDialog;
import gui.dialog.employee.management.event.AddEmployeeEvent;
import gui.dialog.employee.management.listener.ManageEmployeeListener;
import gui.editMenu.listener.EditMenuListener;
import gui.employeeNewOrder.listener.EmployeeNewOrderListener;
import gui.home.customer.CustomerHomePanel;
import gui.home.employee.listener.EmployeeHomeListener;
import gui.home.employee.panel.EmployeeHomePanel;
import gui.login.createAccount.NewCustomerPanel;
import gui.login.createAccount.event.CreateAccountEvent;
import gui.login.createAccount.listener.CreateAccountListener;
import gui.login.createAccount.listener.NewCustomerListener;
import gui.login.event.LoginEvent;
import gui.login.listener.LoginListener;
import gui.login.panel.CustomerLoginPanel;
import gui.login.panel.EmployeeLoginPanel;
import gui.title.TitlePanel;
import gui.tools.CustomMessageDialog;
import model.MenuItem;
import model.*;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private static final int WIDTH = 600, HEIGHT = 800; // window size

    private final Controller controller;

    // components

    private final MenuBar menuBar;
    private final TitlePanel titlePanel; // application title panel

    //container panel
    private final JPanel containerPanel; // contains loginPanels and homePanels

    //login panels
    private final JPanel loginPanels;
    private final EmployeeLoginPanel employeeLoginPanel; // panel containing the employee login
    private final CustomerLoginPanel customerLoginPanel; // panel containing the employee login

    private final JPanel homePanels;

    private final EmployeeHomePanel employeeHomePanel;
    private final CustomerHomePanel customerHomePanel;
    private final NewCustomerPanel newCustomerPanel;


    // employee menu dialogs
    private final ManageEmployeesDialog manageEmployeesDialog; // dialog box for managing employees

    // option dialog
    CustomMessageDialog customMessageDialog;


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

        // add login panels to the loginPanels panel with cardLayout
//        loginPanels.add(customerLoginPanel, "CUSTOMER_LOGIN");
        loginPanels.add(employeeLoginPanel, "EMPLOYEE_LOGIN");

        //home panels
        homePanels = new JPanel(new CardLayout());
        employeeHomePanel = new EmployeeHomePanel();
        customerHomePanel = new CustomerHomePanel();
        newCustomerPanel = new NewCustomerPanel(this);

        // add home panels to the homePanel panel with cardLayout
        homePanels.add(employeeHomePanel, "EMPLOYEE_HOME");
        homePanels.add(customerHomePanel, "CUSTOMER_HOME");

        // container panel for home panels, login panels, and the new customer form panel
        containerPanel = new JPanel(new CardLayout());
        containerPanel.add(homePanels, "HOME");
        containerPanel.add(loginPanels, "LOGIN");
        containerPanel.add(newCustomerPanel, "NEW_CUSTOMER");

        CardLayout cl = (CardLayout) containerPanel.getLayout();
        cl.show(containerPanel, "LOGIN"); // set the default to the login
        CardLayout hl = (CardLayout) employeeHomePanel.getContainerPanel().getLayout();
        hl.show(employeeHomePanel.getContainerPanel(), "HOME");

        // dialog
        manageEmployeesDialog = new ManageEmployeesDialog(this);

        // custom option dialog
        customMessageDialog = new CustomMessageDialog(this);

        controller = new Controller(); // MVC


//        manageEmployeesDialog.setVisible(true); // set manage employees dialog visible


        // set employee login panel invisible by default
        // menu bar
        setJMenuBar(menuBar);

        // handle events

        // employee login event
        this.employeeLoginPanel.setEmployeeLoginListener(new LoginListener() {
            @Override
            public void loginEvent(LoginEvent e) throws ParseException, IOException { // login event
                if (MainFrame.this.controller.employeeLogin(e) != null) { // if the returned employee is not null
                    Employee employee = MainFrame.this.controller.employeeLogin(e); // set employee to logged in employee
                    if (employee.isAdmin())
                        menuBar.isAdmin();//if logged in employee is admin, call isAdmin in menu bar to add manage employee menuItem for ADMIN ACCESS
                    menuBar.employeeHomeView(); // set menuBar to employee view
                    employeeHomePanel.setEmployee(employee); // set the employeeHomePanel employee

                    cl.show(containerPanel, "HOME"); // switch to the homePanels

                    CardLayout hl = (CardLayout) homePanels.getLayout();
                    hl.show(homePanels, "EMPLOYEE_HOME"); // in the homePanels switch to the employeeHome

                    System.out.println("EMPLOYEE : " + employee.getID() + " : " + employee.getFirstName() + " | LOGGED IN"); // print log in messsage

                } else {
                    customMessageDialog.errorDialog("Invalid ID", "No employee linked to ID"); // if unknown ID show error message
                }
            }
        });

        // customer login event
        this.customerLoginPanel.setLoginListener(new LoginListener() {
            @Override
            public void loginEvent(LoginEvent e) throws ParseException, IOException { // login event
                if (MainFrame.this.controller.customerLogin(e) != null) { // if the returned customer is not null
                    Customer customer = MainFrame.this.controller.customerLogin(e); // set customer to logged in customer
                    menuBar.customerHomeView(); // set menuBar to customer view
                    customerHomePanel.setCustomer(customer); // set the employeeHomePanel employee

                    cl.show(containerPanel, "HOME"); // switch to the homePanels

                    CardLayout hl = (CardLayout) homePanels.getLayout();
                    hl.show(homePanels, "CUSTOMER_HOME"); // in the homePanels switch to the employeeHome

                    System.out.println("CUSTOMER : " + customer.getID() + " : " + customer.getFirstName() + " | LOGGED IN"); // print log in messsage

                } else {
                    customMessageDialog.errorDialog("Account not found", "No account linked to phone number"); // if unknown phoneNumber show error message
                }
            }
        });

        // new customer? event in loginPanel
        this.customerLoginPanel.setNewCustomerListener(new NewCustomerListener() {
            @Override
            public void newCustomerEvent() {
                cl.show(containerPanel, "NEW_CUSTOMER");
                menuBar.setVisible(false);
                newCustomerPanel.setPhoneNumber(MainFrame.this.customerLoginPanel.getIdField().getText());
            }
        });

        //

        // create customer event in newCustomerDialog
        this.newCustomerPanel.setCreateAccountListener(new CreateAccountListener() {
            @Override
            public void createAccount(CreateAccountEvent e) throws IOException {
                if (!MainFrame.this.controller.existingCustomer(e.getPhoneNumber())) { // if there is a customer with this number already
                    Customer newCustomer = new Customer(e.getPhoneNumber(), e.getFirstName(), e.getLastName(), e.getAddress(), e.getDetails());
                    MainFrame.this.controller.addCustomer(newCustomer);

                    if (e.getPayment() != null) {
                        newCustomer.getPayments().add(e.getPayment());
                        MainFrame.this.controller.savePayments();
                        System.out.println("CUSTOMER CREATED: " + newCustomer.getID() + ":" + newCustomer.getFirstName() + " PAYMENT METHOD SAVED: " + e.getPayment().toString());
                    } else
                        System.out.println("CUSTOMER CREATED: " + newCustomer.getID() + ":" + newCustomer.getFirstName());


                    cl.show(containerPanel, "LOGIN"); //switch to loginPanel
                    menuBar.setVisible(true);


                    // show confirm message
                    customMessageDialog.errorDialog("Account Registered", "New account registered!");

                } else {
                    customMessageDialog.errorDialog("Phone # in use", "\"" + e.getPhoneNumber() + "\" is already in use.");
                    cl.show(containerPanel, "LOGIN");
                }

            }

            @Override
            public void cancelEvent() {
                cl.show(containerPanel, "LOGIN");
                menuBar.setVisible(true);
            }
        });


        // employee home panel events
        this.employeeHomePanel.setEmployeeHomeListener(new EmployeeHomeListener() {

            @Override
            public void newOrderEvent(Employee currentEmployee) throws IOException, ParseException {
                MainFrame.this.controller.loadMenu();
                employeeHomePanel.setNewOrderMenuItems(MainFrame.this.controller.getMenu(), MainFrame.this.controller.getPrebuiltPizzas()); // set the new order items
                CardLayout hl = (CardLayout) employeeHomePanel.getContainerPanel().getLayout(); // set hl to the employee home panel container layout
                hl.show(employeeHomePanel.getContainerPanel(), "NEW_ORDER"); // change the cardPanel in the container layout to display the newOrderPanel
                menuBar.employeeNewOrderView(); // set the menuBar to the employee new order view
            }

            @Override
            public void editMenuEvent(Employee currentEmployee) throws ParseException, IOException {
                MainFrame.this.controller.loadInventory();
                MainFrame.this.controller.loadMenu();
                employeeHomePanel.setEditMenuItems(MainFrame.this.controller.getMenu(), MainFrame.this.controller.getInventory(), MainFrame.this.controller.getPrebuiltPizzas()); // set the edit menu items
                CardLayout hl = (CardLayout) employeeHomePanel.getContainerPanel().getLayout(); // set hl to the employee home panel container layout
                hl.show(employeeHomePanel.getContainerPanel(), "EDIT_MENU"); // change the cardPanel in the container layout to display the editMenuPanel
                menuBar.employeeEditMenuView(); // set the menuBar to the employee edit menu view
            }
        });

        // handles the employee new order panel events
        this.employeeHomePanel.setNewOrderListener(new EmployeeNewOrderListener() {
            @Override
            public void newOrderEvent(Order newOrder) throws IOException {
                MainFrame.this.controller.createOrder(newOrder); // add the order to the database
                MainFrame.this.controller.saveOrders(); // save the orders to the database
                employeeHomePanel.setNewOrderMenuItems(MainFrame.this.controller.getMenu(), MainFrame.this.controller.getPrebuiltPizzas()); // set the new order items
            }
        });

        // handles the employee edit menu panel events
        this.employeeHomePanel.setEditMenuListener(new EditMenuListener() {
            @Override
            public void saveMenuEvent() throws IOException {
                MainFrame.this.controller.saveMenu(); // save the menu to the database
                MainFrame.this.controller.saveInventory(); // save the inventory to the database
                employeeHomePanel.setEditMenuItems(MainFrame.this.controller.getMenu(), MainFrame.this.controller.getInventory(), MainFrame.this.controller.getPrebuiltPizzas()); // set the edit menu items
                CardLayout hl = (CardLayout) employeeHomePanel.getContainerPanel().getLayout(); // set hl to the employee home panel container layout
                hl.show(employeeHomePanel.getContainerPanel(), "HOME"); // change the cardPanel in the container layout to display the homePanel
            }

            @Override
            public void addIngredientEvent(Ingredient ingredient) {
                MainFrame.this.controller.addIngredient(ingredient); // add the ingredient to the database
                employeeHomePanel.setEditMenuItems(MainFrame.this.controller.getMenu(), MainFrame.this.controller.getInventory(), MainFrame.this.controller.getPrebuiltPizzas()); // set the edit menu items

            }

            @Override
            public void addMenuItemEvent(MenuItem menuItem) throws IOException {
                MainFrame.this.controller.addMenuItem(menuItem); // add the menuItem to the database
                employeeHomePanel.setEditMenuItems(MainFrame.this.controller.getMenu(), MainFrame.this.controller.getInventory(), MainFrame.this.controller.getPrebuiltPizzas()); // set the edit menu items

            }

            @Override
            public void editMenuCancelEvent() throws IOException, ParseException {
                MainFrame.this.controller.loadMenu(); // load the menu from the database;
                MainFrame.this.controller.loadInventory(); // load the inventory from the database;
                CardLayout hl = (CardLayout) employeeHomePanel.getContainerPanel().getLayout(); // set hl to the employee home panel container layout
                hl.show(employeeHomePanel.getContainerPanel(), "HOME"); // change the cardPanel in the container layout to display the homePanel

            }

            @Override
            public void addNewPrebuiltPizzaEvent(PrebuiltPizza newPrebuiltPizza) {
                MainFrame.this.controller.addPrebuiltPizza(newPrebuiltPizza);
            }

            @Override
            public void removeIngredientEvent(Ingredient selectedItem) {
                MainFrame.this.controller.removeIngredient(selectedItem); // remove the ingredient from the database
                employeeHomePanel.setEditMenuItems(MainFrame.this.controller.getMenu(), MainFrame.this.controller.getInventory(), MainFrame.this.controller.getPrebuiltPizzas()); // set the edit menu items

            }

            @Override
            public void removeMenuItemEvent(MenuItem selectedItem) {
                MainFrame.this.controller.removeMenuItem(selectedItem); // remove the menuItem from the database
                employeeHomePanel.setEditMenuItems(MainFrame.this.controller.getMenu(), MainFrame.this.controller.getInventory(), MainFrame.this.controller.getPrebuiltPizzas()); // set the edit menu items

            }

            @Override
            public void removePizzaEvent(PrebuiltPizza pizza) {
                MainFrame.this.controller.removePizza(pizza); // remove the prebuilt pizza from the database
            }
        });

        //handles the employee management dialog events
        this.manageEmployeesDialog.setEmployeeListener(new ManageEmployeeListener() {
            @Override
            public void addEmployeeEvent(AddEmployeeEvent e) { // on add employee
                if (!MainFrame.this.controller.existingEmployee(e.getID())) { //  if there is not an existing employee
                    Employee newHire = new Employee(e.isAdmin(), e.getID(), e.getFirstName(), e.getLastName(), e.getAge(), e.getRole(), e.getPhoneNumber(), e.getAddress()); // create new employee
                    MainFrame.this.controller.addEmployee(newHire); // add to employee list
                    manageEmployeesDialog.displayEmployees(MainFrame.this.controller.getEmployees());

                    System.out.println("EMPLOYEE CREATED: " + newHire.getID() + ":" + newHire.getFirstName());
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "User ID \"" + e.getID() + "\" is already in use.", "User ID in use", JOptionPane.ERROR_MESSAGE); // if unknown phoneNumber show error message

                }

            }

            @Override
            public void removeEmployeeEvent(String ID) {
                if (ID == null) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Select an Employee.", "No Employee Selected", JOptionPane.ERROR_MESSAGE);
                } else {
                    MainFrame.this.controller.removeEmployee(ID);
                    manageEmployeesDialog.displayEmployees(MainFrame.this.controller.getEmployees());
                }
            }

            @Override
            public void saveEmployeesEvent() throws IOException {// on save click
                MainFrame.this.controller.saveEmployees();
                manageEmployeesDialog.setVisible(false);
            }

            @Override
            public void cancelEmployeesEvent() {
                manageEmployeesDialog.setVisible(false); // on cancel click set dialog visible to false
                try {
                    MainFrame.this.controller.loadEmployees(); // load employees from file since the current list is different from the file
                    manageEmployeesDialog.displayEmployees(MainFrame.this.controller.getEmployees()); // display the employees
                } catch (ParseException | IOException e) {
                    e.printStackTrace();
                }

            }
        });


        setLayout(new BorderLayout()); // set layout to a border layout

        add(titlePanel, BorderLayout.NORTH);
        add(containerPanel, BorderLayout.CENTER);
        load(); // load customers and employees and update values

    }

    private void load() throws IOException, ParseException {
        MainFrame.this.controller.loadEmployees();
        MainFrame.this.controller.loadCustomers();
        MainFrame.this.controller.loadOrders();
        MainFrame.this.controller.loadPayments();
        MainFrame.this.controller.loadMenu();
        MainFrame.this.controller.loadInventory();

        manageEmployeesDialog.displayEmployees(MainFrame.this.controller.getEmployees());
        employeeHomePanel.setEditMenuItems(MainFrame.this.controller.getMenu(), MainFrame.this.controller.getInventory(), MainFrame.this.controller.getPrebuiltPizzas());
        employeeHomePanel.setNewOrderMenuItems(MainFrame.this.controller.getMenu(), MainFrame.this.controller.getPrebuiltPizzas());
    }


    // handles when user/employee logs out
    public void logOut() {
        // set the MAINFRAME containerPanel to show the LOGIN panel
        CardLayout cl = (CardLayout) containerPanel.getLayout();
        cl.show(containerPanel, "LOGIN");

        // set the employeeHomePanel containerPanel to show the employeeHome, so that when an employee logs back in, it will display the homePanel and not the current panel on LOGOUT
        cl = (CardLayout) employeeHomePanel.getContainerPanel().getLayout();
        cl.show(employeeHomePanel.getContainerPanel(), "HOME");

//        cl = (CardLayout) customerHomePanel.getContainerPanel().getLayout();
//        cl.show(customerHomePanel.getContainerPanel(), "HOME");

        employeeHomePanel.setEmployee(null);
        customerHomePanel.setCustomer(null);


        System.out.println("LOGGED OUT");


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

    public CustomerHomePanel getCustomerHomePanel() {
        return customerHomePanel;
    }

    public ManageEmployeesDialog getManageEmployeesDialog() {
        return manageEmployeesDialog;
    }

}


