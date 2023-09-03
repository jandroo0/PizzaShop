package Controller;

import gui.LoginEvent;
import model.Customer;
import model.Database;
import model.Employee;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.LinkedList;

public class Controller {
    Database db = new Database();

    public Controller() {

    }

    public LinkedList<Employee> getEmployees() {return this.db.getEmployees();}

    public void removeEmployee(String ID) {this.db.removeEmployee(ID);};

    public void addEmployee(Employee e) {
        this.db.addEmployee(e);
    }

    public Employee employeeLogin(LoginEvent e) {
        return this.db.employeeLogin(e);
    }

    public Customer customerLogin(LoginEvent e) {
        return this.db.customerLogin(e);
    }

    public void saveEmployees() throws IOException {
        this.db.saveEmployees();
    }

    public void saveCustomers() throws IOException {
        this.db.saveCustomers();
    }

    public void loadEmployees() throws IOException, ParseException {
        this.db.loadEmployees();
    }
    public void loadCustomers() throws IOException, ParseException {
        this.db.loadCustomers();
    }



}