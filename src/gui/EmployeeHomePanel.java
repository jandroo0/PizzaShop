package gui;

import model.Employee;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class EmployeeHomePanel extends JPanel {

    private JButton newOrderButton;
    private JButton orderHistoryButton;
    private JButton editMenuButton;

    private JPanel containerPanel;


    private JPanel homePanel;
    private NewOrderPanel newOrderPanel;
    private OrderHistoryPanel orderHistoryPanel;
    private EditMenuPanel editMenuPanel;

    private Employee currentEmployee;

    private EmployeeHomeListener employeeHomeListener;

    public EmployeeHomePanel() {
        currentEmployee = null;

        // home panel buttons
        newOrderButton = new JButton("NEW ORDER");
        orderHistoryButton = new JButton("VIEW ORDERS");
        editMenuButton = new JButton("EDIT MENU");

        //panels
        homePanel = new JPanel();
        editMenuPanel = new EditMenuPanel();

        containerPanel = new JPanel(new CardLayout());
        containerPanel.add(homePanel, "HOME");
        containerPanel.add(editMenuPanel, "EDIT_MENU");

        editMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeHomeEvent event = new EmployeeHomeEvent(e, currentEmployee);
                EmployeeHomePanel.this.employeeHomeListener.editMenuEvent(event);
            }
        });





        layoutComponents();
        styling();
        setLayout(new BorderLayout());
        add(containerPanel, BorderLayout.CENTER);

    }

    private void layoutComponents() {


        // employee homePanel
        Border border = BorderFactory.createEmptyBorder(0,0,60,0);
        homePanel.setBorder(border);
        homePanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0;
        gc.gridx = 0;
        gc.insets = new Insets(0,0,20,0);
        homePanel.add(newOrderButton, gc);
        gc.gridy++;
        homePanel.add(orderHistoryButton, gc);
        gc.gridy++;
        homePanel.add(editMenuButton, gc);

    }

    private void styling() {

        setBackground(Utils.getBackgroundColor());

        homePanel.setBackground(Utils.getBackgroundColor());

        containerPanel.setBackground(Utils.getBackgroundColor());


        // buttons
        newOrderButton.setFont(Utils.getHomeButtonsFont());
        newOrderButton.setBackground(Utils.getButtonBackgroundColor());
        newOrderButton.setForeground(Utils.getTextColor());
        newOrderButton.setBorder(BorderFactory.createEmptyBorder(5,8,5,8));

        orderHistoryButton.setFont(Utils.getHomeButtonsFont());
        orderHistoryButton.setBackground(Utils.getButtonBackgroundColor());
        orderHistoryButton.setForeground(Utils.getTextColor());
        orderHistoryButton.setBorder(BorderFactory.createEmptyBorder(5,8,5,8));

        editMenuButton.setFont(Utils.getHomeButtonsFont());
        editMenuButton.setBackground(Utils.getButtonBackgroundColor());
        editMenuButton.setForeground(Utils.getTextColor());
        editMenuButton.setBorder(BorderFactory.createEmptyBorder(5,8,5,8));


        // on mouse hover over change colors
        newOrderButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                newOrderButton.setBackground(Utils.getButtonHoverColor());
                newOrderButton.setForeground(Utils.getButtonBackgroundColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                newOrderButton.setBackground(Utils.getButtonBackgroundColor());
                newOrderButton.setForeground(Utils.getTextColor());
            }
        });

        orderHistoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                orderHistoryButton.setBackground(Utils.getButtonHoverColor());
                orderHistoryButton.setForeground(Utils.getButtonBackgroundColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                orderHistoryButton.setBackground(Utils.getButtonBackgroundColor());
                orderHistoryButton.setForeground(Utils.getTextColor());
            }
        });

        editMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                editMenuButton.setBackground(Utils.getButtonHoverColor());
                editMenuButton.setForeground(Utils.getButtonBackgroundColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                editMenuButton.setBackground(Utils.getButtonBackgroundColor());
                editMenuButton.setForeground(Utils.getTextColor());
            }
        });

    }

    public void setEmployeeHomeListener(EmployeeHomeListener listener) {
        this.employeeHomeListener = listener;
    }
    public void setEmployee(Employee e){
        currentEmployee = e;

    }

    public JPanel getContainerPanel() {
        return containerPanel;
    }
}
