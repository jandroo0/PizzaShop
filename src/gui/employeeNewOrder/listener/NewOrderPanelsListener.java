package gui.employeeNewOrder.listener;

import model.MenuItem;

import java.util.EventListener;

public interface NewOrderPanelsListener extends EventListener {

    void itemAdded(MenuItem item);

}
