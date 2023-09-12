package Controller;

import gui.login.event.LoginEvent;
import model.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.LinkedList;

public class Controller {
    Database db = new Database();

    public Controller() {

    }

    public void saveInventory() throws IOException {
        this.db.saveIngredients();
    }

    public void addIngredient(Ingredient ingredient) {
        this.db.addIngredient(ingredient);
    }

    public void loadInventory() throws ParseException, IOException {
        this.db.loadIngredients();
    }

    public void saveMenu() throws IOException {
        this.db.saveMenu();
    }

    public void addMenuItem(MenuItem e) {
        this.db.addMenuItem(e);
    }

    public void addPrebuiltPizza(PrebuiltPizza e) {
        this.db.addPrebuiltPizza(e);
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

    public void loadMenu() throws IOException, ParseException {
        this.db.loadMenu();
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

    public LinkedList<MenuItem> getMenu() {
        return this.db.getMenu();
    }

    public LinkedList<Ingredient> getInventory() {
        return this.db.getInventory();
    }

    public LinkedList<PrebuiltPizza> getPrebuiltPizzas() {
        return this.db.getPrebuiltPizzas();
    }

    public void removeIngredient(Ingredient selectedItem) {
        this.db.removeIngredient(selectedItem);
    }

    public void removeMenuItem(MenuItem selectedItem) {
        this.db.removeMenuItem(selectedItem);
    }

    public void removePizza(PrebuiltPizza pizza) {
        this.db.removePizza(pizza);
    }
}