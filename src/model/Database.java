package model;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private LinkedList<Employee> employees = new LinkedList<Employee>(); // create list of employees

    private String employeesFilePath = "employees.csv";

    public Database() {

    }

    public LinkedList<Employee> getEmployees() { // return employees
        return employees;
    }

    public void addEmployee(Employee employee) {this.employees.add(employee);} // add employee
    public void removeEmployee(int index) {this.employees.remove(index);} // remove employee



    public void saveToFile() throws IOException { // save to local file
        try {
            File file = new File(employeesFilePath);

            if (file.createNewFile()) {
                System.out.println("Employees File Created: " + file.getName());
            } else {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                Employee[] employees1 = (Employee[])this.employees.toArray(new Employee[this.employees.size()]);
                oos.writeObject(employees1);
                oos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadFromFile(File file) throws IOException{ // load from local file
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            Employee[] employees1 = (Employee[])ois.readObject();
            this.employees.clear();
            this.employees.addAll(Arrays.asList(employees1));
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e1) {
            e1.printStackTrace();
        }

        ois.close();
    }
}
