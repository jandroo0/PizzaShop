package model;

import gui.LoginEvent;
import gui.Payment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.LinkedList;


public class Database {
    private LinkedList<Employee> employees; // create list of employees
    private LinkedList<Customer> customers; // create list of customer


    private FileWriter fileWriter;
    private String employeesFilePath = "employees.json";
    private String customersFilePath = "customers.json";

    public Database() {

        employees = new LinkedList<Employee>();
        customers = new LinkedList<Customer>();
    }

    public LinkedList<Employee> getEmployees() { // return employees
        return employees;
    }

    public void addEmployee(Employee employee) {this.employees.add(employee);} // add employee
    // remove employee
    public void removeEmployee(String ID) {
        Employee e = null;

        for(Employee employee : employees) {
            if(employee.getID().equals(ID)) e = employee;
        }
        System.out.println(e.toString());
        employees.remove(e);
    }

    // add customer
    public void addCustomer(Customer customer) throws IOException {
        this.customers.add(customer);
        saveCustomers(); // save all customers
    }

    public Employee employeeLogin(LoginEvent event) { //  checks if employee login id matches any id in employee list and returns employee or null
        for(Employee employee : employees) {
            if(event.getID().equals(employee.getID())) {
                return employee;
            }
        }
        return null;
}

    public Customer customerLogin(LoginEvent event) { //  checks if customer login id matches any id in customer list and returns customer or null
        for(Customer customer : customers) {
            if(event.getID().equals(customer.getID())) {
                return customer;
            }
        }
        return null;
    }

    // save employees
    public void saveEmployees() throws IOException {

        fileWriter = new FileWriter(employeesFilePath);
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        // for each employee create json object and write to file
        for (Employee employee: employees) {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("Is_Admin", employee.isAdmin());
            jsonObject.put("ID", employee.getID());
            jsonObject.put("First_Name", employee.getFirstName());
            jsonObject.put("Last_Name", employee.getLastName());
            jsonObject.put("Age", employee.getAge());
            jsonObject.put("Role", employee.getRole());
            jsonObject.put("Phone_Number", employee.getPhoneNumber());
            jsonObject.put("Address", employee.getAddress());

            sb.append(jsonObject.toJSONString() + ',');
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        try {
            fileWriter.write(sb.toString());
            System.out.println("EMPLOYEE JSON CREATED AND SAVED");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load employees
    public void loadEmployees() throws ParseException, IOException {


        // LOAD EMPLOYEES---------------------------------------------
        JSONParser parser = new JSONParser(); //create JSON parser
        JSONArray employeesJSON = (JSONArray) parser.parse(new FileReader(employeesFilePath)); // create array from file

        try {
            System.out.println("LOADING EMPLOYEES --------------");
            for(Object employeeJSON : employeesJSON) { // for each employee JSON in employees file

                JSONObject employee = (JSONObject) employeeJSON; // create employee object from json

                // gather values

                boolean isAdmin = (boolean) employee.get("Is_Admin");
                String ID = (String) employee.get("ID");
                String firstName = (String) employee.get("First_Name");
                String lastName = (String) employee.get("Last_Name");
                String age = (String) employee.get("Age");
                String role = (String) employee.get("Role");
                String phoneNumber = (String) employee.get("Phone_Number");
                String address = (String) employee.get("Address");

                // add new employee to current employees list

                Employee newEmployee = new Employee(isAdmin, ID, firstName, lastName, age, role, phoneNumber, address);
                employees.add(newEmployee);
                if(newEmployee.isAdmin()) System.out.println("ADMIN LOADED: " + newEmployee.getID() + ":" + newEmployee.getFirstName());
                else System.out.println("EMPLOYEE LOADED: " + newEmployee.getID() + ":"  + newEmployee.getFirstName());
            }
            System.out.println("EMPLOYEES LOADED --------------");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //save customers
    public void saveCustomers() throws IOException {

        fileWriter = new FileWriter(customersFilePath);
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        // for each employee create json object and write to file
        for (Customer customer: customers) {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("Phone_Number", customer.getPhoneNumber());
            jsonObject.put("First_Name", customer.getFirstName());
            jsonObject.put("Last_Name", customer.getLastName());
            jsonObject.put("Address", customer.getAddress());
            jsonObject.put("Details", customer.getDetails());

            // save payments

            sb.append(jsonObject.toJSONString() + ',');
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        try {
            fileWriter.write(sb.toString());
            System.out.println("CUSTOMER JSON CREATED AND SAVED");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load customers
    public void loadCustomers() throws ParseException, IOException {


        // LOAD CUSTOMERS---------------------------------------------
        JSONParser parser = new JSONParser(); //create JSON parser
        JSONArray customersJSON = (JSONArray) parser.parse(new FileReader(customersFilePath)); // create JSONarray from file

        try {
            System.out.println("LOADING CUSTOMERS --------------");
            for(Object customerJSON : customersJSON) { // for each employee JSON in employees file

                JSONObject customer = (JSONObject) customerJSON; // create employee object from json

                // gather values

                String phoneNumber = (String) customer.get("Phone_Number");
                String firstName = (String) customer.get("First_Name");
                String lastName = (String) customer.get("Last_Name");
                String address = (String) customer.get("Address");
                String details = (String) customer.get("Details");

                // load payments here


                // add new employee to current employees list

                Customer newCustomer = new Customer( phoneNumber, firstName, lastName,address, details);
                customers.add(newCustomer);
                System.out.println("CUSTOMER LOADED: " + newCustomer.getID() + ":"  + newCustomer.getFirstName());

            }
            System.out.println("CUSTOMERS LOADED --------------");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
