package gui;

import javax.swing.*;
import java.awt.*;

import Controller.Controller;

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
    public MainFrame() {
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
            public void loginEvent(LoginEvent e) {
//                MainFrame.this.controller.employeeLogin(e);
            }
        });



        setLayout(new BorderLayout()); // set layout to a border layout

        add(titlePanel, BorderLayout.NORTH);
        add(employeeLoginPanel, BorderLayout.CENTER);


    }


    public ManageEmployeesDialog getManageEmployeesDialog() {
        return employeesDialog;
    }

    public ManageCustomersDialog getManageCustomersDialog() {
        return customersDialog;
    }
}


