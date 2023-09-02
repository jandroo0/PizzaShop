package model;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private List<Employee> employees = new LinkedList<Employee>(); // create list of employees

    public Database() {

    }

    public List<Employee> getEmployees() { // return employees
        return employees;
    }

    public void addEmployee(Employee employee) {this.employees.add(employee);} // add employee
    public void removeEmployee(int index) {this.employees.remove(index);} // remove employee


    public void saveToFile(File file) throws IOException { // save to local file
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Employee[] employees1 = (Employee[])this.employees.toArray(new Employee[this.employees.size()]);
        oos.writeObject(employees1);
        oos.close();
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
