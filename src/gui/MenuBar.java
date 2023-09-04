package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {

    JMenu viewMenu;
    JMenu employeeMenu;
    JMenu customerMenu;
    JMenuItem manageEmployeesItem;
    JMenuItem employeeViewItem;
    JMenuItem customerViewItem;

    JMenuItem logoutItem;

    JMenuItem exitItem = new JMenuItem("Exit");

    public MenuBar(MainFrame frame) {
        // menu
        viewMenu = new JMenu("View");
        employeeMenu = new JMenu("Employee");
        customerMenu = new JMenu("Customer");

        // sub items
        customerViewItem = new JMenuItem("Customer View");
        employeeViewItem = new JMenuItem("Employee View");


        logoutItem = new JMenuItem("Logout"); // logout menu item

    // employee items
        manageEmployeesItem = new JMenuItem("Manage Employees");


        // when employee view is selected
        employeeViewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("EMPLOYEE VIEW");
                CardLayout cl = (CardLayout) frame.getLoginPanels().getLayout(); // grab cardLayout from main frame
                cl.show(frame.getLoginPanels(), "EMPLOYEE_LOGIN"); // switch to employeeLoginPanel using cardLayout
                viewMenu.add(customerViewItem); // add the customer view item to the menu
                viewMenu.remove(employeeViewItem); // remove the employee view item

            }
        });

        // when customer view is selected
        customerViewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("CUSTOMER VIEW");
                CardLayout cl = (CardLayout) frame.getLoginPanels().getLayout(); // grab cardLayout from main frame
                cl.show(frame.getLoginPanels(), "CUSTOMER_LOGIN"); // switch to customerLoginPanel using cardLayout
                viewMenu.add(employeeViewItem); // add the employee view item to the menu
                viewMenu.remove(customerViewItem); // remove the customer view item
            }
        });

        // manage employees action
        manageEmployeesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getManageEmployeesDialog().setVisible(true);
            }
        });

        // employee logout action
        logoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) frame.getContainerPanel().getLayout();
                cl.show(frame.getContainerPanel(), "LOGIN");

                frame.getEmployeeHomePanel().setEmployee(null);
                frame.getCustomerHomePanel().setCustomer(null);
                System.out.println("LOGGED OUT");

                loginPanelView();

            }
        });

        add(viewMenu);
        viewMenu.add(employeeViewItem);

        styling();
    }

    // if logged in employee is admin, display manage employee menu item
    public void employeeView() {
        add(employeeMenu);
        remove(viewMenu);
        employeeMenu.add(logoutItem);
    }

    public void customerView() {
        add(customerMenu);
        remove(viewMenu);
        customerMenu.add(logoutItem);
    }

    public void loginPanelView() {
        remove(employeeMenu);
        remove(customerMenu);
        employeeMenu.remove(manageEmployeesItem);
        add(viewMenu);
    }

    public void isAdmin() {
        employeeMenu.add(manageEmployeesItem);
    }

    public void styling() {
        setBorder(BorderFactory.createMatteBorder(0,0,2,0, Utils.getTextColor()));
        setBackground(Utils.getBackgroundColor());
        setForeground(Utils.getTextColor());

        viewMenu.setForeground(Utils.getTextColor());
        viewMenu.setFont(Utils.getDefaultFont());

        customerMenu.setForeground(Utils.getTextColor());
        customerMenu.setFont(Utils.getDefaultFont());

        employeeMenu.setForeground(Utils.getTextColor());
        employeeMenu.setFont(Utils.getDefaultFont());

        employeeViewItem.setForeground(Utils.getTextColor());
        employeeViewItem.setFont(Utils.getDefaultFont());

        customerViewItem.setForeground(Utils.getTextColor());
        customerViewItem.setFont(Utils.getDefaultFont());
    }


}
