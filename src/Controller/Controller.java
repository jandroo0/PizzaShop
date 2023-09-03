package Controller;

import gui.LoginEvent;
import model.Database;
import model.Employee;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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

    public void saveToFile() throws IOException {
        this.db.saveToFile();
    }

    public void loadFromFile() throws IOException, ParseException {
        this.db.loadFromFile();
    }



}