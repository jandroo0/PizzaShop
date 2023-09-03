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

    public void removeEmployee(int index) {this.db.removeEmployee(index);};

    public void addEmployee(Employee e) {
        this.db.addEmployee(e);
    }

    public void employeeLogin(LoginEvent e) {

    }

    public void saveToFile() throws IOException {
        this.db.saveToFile();
    }

    public void loadFromFile() throws IOException, ParseException {
        this.db.loadFromFile();
    }



}
