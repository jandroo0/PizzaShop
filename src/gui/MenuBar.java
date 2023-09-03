package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {

    JMenu employeeMenu;
    JMenuItem manageEmployeesItem;
    JMenuItem manageCustomerItem;

    JMenuItem exitItem = new JMenuItem("Exit");

    public MenuBar(MainFrame frame) {
        // menu
        employeeMenu = new JMenu("Employee");

        // sub items
        manageEmployeesItem = new JMenuItem("Manage Employees");
        manageCustomerItem = new JMenuItem("Manage Customers");

        employeeMenu.add(manageEmployeesItem);
//        employeeMenu.add(manageCustomerItem);


        manageEmployeesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getManageEmployeesDialog().setVisible(true);

            }
        });

        manageCustomerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.getManageCustomersDialog().setVisible(true);
            }
        });

        add(employeeMenu);

        styling();
    }

    private void styling() {

    }


}
