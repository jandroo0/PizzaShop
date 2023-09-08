package Controller;

import gui.login.event.LoginEvent;
import model.Customer;
import model.Database;
import model.Employee;
import model.MenuItem;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.LinkedList;

public class Controller {
    Database db = new Database();

    public Controller() {

    }

    public void addMenuItem(MenuItem e) {
        this.db.addMenuItem(e);
    }

    public void addEmployee(Employee e) {
        this.db.addEmployee(e);
    }

    public void addCustomer(Customer c) throws IOException {
        this.db.addCustomer(c);
    }

    public void removeEmployee(String ID) {
        this.db.removeEmployee(ID);
    }

    public LinkedList<Employee> getEmployees() {
        return this.db.getEmployees();
    }


    public Employee employeeLogin(LoginEvent e) throws ParseException, IOException {
        return this.db.employeeLogin(e);
    }

    public Customer customerLogin(LoginEvent e) throws ParseException, IOException {
        return this.db.customerLogin(e);
    }

    public void saveEmployees() throws IOException {
        this.db.saveEmployees();
    }

    public void saveCustomers() throws IOException {
        this.db.saveCustomers();
    }

    public void savePayments() throws IOException {
        this.db.savePayments();
    }

    public void loadPayments() throws IOException, ParseException {
        this.db.loadPayments();
    }


    public void loadEmployees() throws IOException, ParseException {
        this.db.loadEmployees();
    }

    public void loadCustomers() throws IOException, ParseException {
        this.db.loadCustomers();
    }

    public boolean existingCustomer(String phoneNumber) {
        return this.db.existingCustomer(phoneNumber);
    }

    public boolean existingEmployee(String ID) {
        return this.db.existingEmployee(ID);
    }


}