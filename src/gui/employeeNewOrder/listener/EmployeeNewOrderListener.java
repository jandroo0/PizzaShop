package gui.employeeNewOrder.listener;

import model.Order;

import java.io.IOException;
import java.util.EventListener;

public interface EmployeeNewOrderListener extends EventListener {
    void newOrderEvent(Order order) throws IOException;
}
