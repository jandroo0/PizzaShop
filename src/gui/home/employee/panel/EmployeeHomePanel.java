package gui.home.employee.panel;

import gui.config.Utils;
import gui.home.employee.event.EmployeeHomeEvent;
import gui.home.employee.listener.EmployeeHomeListener;
import gui.home.employee.panel.editMenu.EditMenuPanel;
import gui.tools.Button;
import model.Employee;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EmployeeHomePanel extends JPanel {

    private final Button newOrderButton;
    private final Button orderHistoryButton;
    private final Button editMenuButton;

    private final JPanel containerPanel;


    private final JPanel homePanel;
    private final EditMenuPanel editMenuPanel;
    private NewOrderPanel newOrderPanel;
    private OrderHistoryPanel orderHistoryPanel;
    private Employee currentEmployee;

    private EmployeeHomeListener employeeHomeListener;

    public EmployeeHomePanel() {
        currentEmployee = null;

        // home panel buttons
        newOrderButton = new Button("NEW ORDER", Utils.getTextFont(40), Utils.getTextColor(), Utils.getButtonBackgroundColor(),
                Utils.getButtonHoverColor(), Utils.getButtonBorder());
        orderHistoryButton = new Button("VIEW ORDERS", Utils.getTextFont(40), Utils.getTextColor(), Utils.getButtonBackgroundColor(),
                Utils.getButtonHoverColor(), Utils.getButtonBorder());
        editMenuButton = new Button("EDIT MENU", Utils.getTextFont(40), Utils.getTextColor(), Utils.getButtonBackgroundColor(),
                Utils.getButtonHoverColor(), Utils.getButtonBorder());

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
        Border border = BorderFactory.createEmptyBorder(0, 0, 100, 0);
        homePanel.setBorder(border);
        homePanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 20, 0);
        homePanel.add(newOrderButton, gc);
        gc.gridy++;
        homePanel.add(orderHistoryButton, gc);
        gc.gridy++;
        homePanel.add(editMenuButton, gc);

    }

    private void styling() {
        // home panel
        homePanel.setBackground(Utils.getBackgroundColor());


    }

    public void setEmployeeHomeListener(EmployeeHomeListener listener) {
        this.employeeHomeListener = listener;
    }

    public void setEmployee(Employee e) {
        currentEmployee = e;

    }

    public JPanel getContainerPanel() {
        return containerPanel;
    }
}
