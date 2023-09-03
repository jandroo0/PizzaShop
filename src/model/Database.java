package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private LinkedList<Employee> employees = new LinkedList<>(); // create list of employees


    private FileWriter fileWriter;
    private String employeesFilePath = "employees.json";

    public Database() {

    }

    public LinkedList<Employee> getEmployees() { // return employees
        return employees;
    }

    public void addEmployee(Employee employee) {this.employees.add(employee);} // add employee
    public void removeEmployee(int index) {this.employees.remove(index);} // remove employee



    public void saveToFile() throws IOException { // save to local file

        fileWriter = new FileWriter(employeesFilePath);
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        // for each employee create json object and write to file
        for (Employee employee: employees) {
            JSONObject jsonObject = new JSONObject();
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
            System.out.println("EMPLOYEE JSON CREATED");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void loadFromFile() throws ParseException, IOException { // load from local files


        // LOAD EMPLOYEES---------------------------------------------
        JSONParser parser = new JSONParser(); //create JSON parser
        JSONArray employeesJSON = (JSONArray) parser.parse(new FileReader(employeesFilePath)); // create array from file

        try {
            System.out.println("LOADING EMPLOYEES...");
            for(Object employeeJSON : employeesJSON) { // for each employee JSON in employees file

                JSONObject employee = (JSONObject) employeeJSON; // create employee object from json

                // gather values
                String ID = (String) employee.get("ID");
                String firstName = (String) employee.get("First_Name");
                String lastName = (String) employee.get("Last_Name");
                String age = (String) employee.get("Age");
                String role = (String) employee.get("Role");
                String phoneNumber = (String) employee.get("Phone_Number");
                String address = (String) employee.get("Address");

                // add new employee to current employees list

                Employee newEmployee = new Employee(ID, firstName, lastName, age, role, phoneNumber, address);
                employees.add(newEmployee);
                System.out.println(newEmployee.toString());
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
