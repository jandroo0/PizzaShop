package Controller;

import gui.LoginEvent;
import model.Database;
import model.Employee;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    Database db = new Database();

    public Controller() {

    }

    public List<Employee> getEmployees() {return this.db.getEmployees();}

    public void removeEmployee(int index) {this.db.removeEmployee(index);};

    public void addEmployee(LoginEvent e) {

    }

    public void saveToFile(File file) throws IOException {
        this.db.saveToFile(file);
    }

    public void loadFromFile(File file) throws IOException {
        this.db.loadFromFile(file);
    }



}
