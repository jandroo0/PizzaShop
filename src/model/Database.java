package model;

import gui.login.event.LoginEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;


public class Database {

    // instance lists
    private final LinkedList<Employee> employees; // create list of employees
    private final LinkedList<Customer> customers; // create list of customer

    private final LinkedList<Order> orders; // create list of orders

    private final LinkedList<MenuItem> menu; // create list of menu items
    private LinkedList<PrebuiltPizza> prebuiltPizzas; // create list of prebuilt pizzas

    private final LinkedList<Ingredient> ingredients; // create list of ingredients

    // file paths
    private final String employeesFilePath = "employees.json";
    private final String customersFilePath = "customers.json";
    private final String ordersFilePath = "orders.json";
    private final String paymentsFilePath = "payments.json";
    private final String menuFilePath = "menu.json";
    private final String inventoryFilePath = "inventory.json";
    private FileWriter fileWriter;

    public Database() {

        employees = new LinkedList<Employee>();
        customers = new LinkedList<Customer>();
        orders = new LinkedList<Order>();
        menu = new LinkedList<MenuItem>();
        prebuiltPizzas = new LinkedList<PrebuiltPizza>();
        ingredients = new LinkedList<Ingredient>();


        /// TESTS ________________________________________

//        menu.add(new MenuItem("Drink", "Fanta", 5.65f));
//        menu.add(new MenuItem("Drink", "Coke", 5.65f));
//
//        LinkedList<Ingredient> testToppings = new LinkedList<Ingredient>();
//        testToppings.add(new Ingredient("Garlic", "Topping", 0));
//        testToppings.add(new Ingredient("Onion", "Topping", 0));
//
//        LinkedList<Ingredient> testToppings2 = new LinkedList<Ingredient>();
//        testToppings2.add(new Ingredient("Pepperoni", "Topping", 0));
//
//
//        menu.add(new PrebuiltPizza("Pizza", "Onion Pizza", 10.99f, Size.Medium, "LT", "Normal", "Garlic", testToppings));
//        menu.add(new PrebuiltPizza("Pizza", "Pepperoni Pizza", 10.99f, Size.Medium, "LT", "Normal", "Garlic", testToppings2));


//        ingredients.add(new Ingredient("Veggies", "Green Pepper", 0));
//        ingredients.add(new Ingredient("Meats", "Pepperoni", 0));
//        ingredients.add(new Ingredient("Meats", "Sausage", 0));


//        customers.add(new Customer("1234567890", "John", "Gornell", "123 Main Street", "1234567890"));
//        customers.add(new Customer("0987654321", "Jane", "Gornell", "123 Main Street", "1234567890"));

//        customers.get(0).getPayments().add(new CardPayment("1234567890", "1234567890", "123sad456", "11/23", "123"));
//        customers.get(0).getPayments().add(new CardPayment("1234567890", "1234567890", "123sad456", "11/23", "123"));


    }


    // MENU

    // Save menu to file


    public void saveMenu() throws IOException {
        fileWriter = new FileWriter(menuFilePath);

        JSONObject categoriesJSON = new JSONObject();

        // save prebuilt pizzas
        for (PrebuiltPizza prebuiltPizza : prebuiltPizzas) {
            JSONObject menuItemJSON = new JSONObject();

//          menuItemJSON.put("Size", prebuiltPizza.getSize().toString());
            menuItemJSON.put("Type", prebuiltPizza.getTypeID());
            menuItemJSON.put("Category", prebuiltPizza.getCategory());
            menuItemJSON.put("Item_Name", prebuiltPizza.getItemName());
            menuItemJSON.put("Price", prebuiltPizza.getPrice());

            menuItemJSON.put("Crust", prebuiltPizza.getCrustType());

            String category = prebuiltPizza.getCategory();

            StringBuilder toppingsString = new StringBuilder();

            for (Ingredient topping : prebuiltPizza.getToppings()) {
                toppingsString.append(topping.getCategory() + "-" + topping.getName() + ",");
            }

            toppingsString.deleteCharAt(toppingsString.length() - 1); // delete last comma
            menuItemJSON.put("Toppings", toppingsString.toString());

            JSONArray categoryArray = (categoriesJSON.containsKey(category))
                    ? (JSONArray) categoriesJSON.get(category)
                    : new JSONArray();
            categoryArray.add(menuItemJSON);
            categoriesJSON.put(category, categoryArray);
        }

        // Group menu items by category
        for (MenuItem menuItem : menu) {
            JSONObject menuItemJSON = new JSONObject();

            menuItemJSON.put("Type", menuItem.getTypeID());
            menuItemJSON.put("Category", menuItem.getCategory());
            menuItemJSON.put("Item_Name", menuItem.getItemName());
            menuItemJSON.put("Price", menuItem.getPrice());


            String category = menuItem.getCategory();

            JSONArray categoryArray = (categoriesJSON.containsKey(category))
                    ? (JSONArray) categoriesJSON.get(category)
                    : new JSONArray();
            categoryArray.add(menuItemJSON);
            categoriesJSON.put(category, categoryArray);

        }


        // Write categories JSON to file
        fileWriter.write(categoriesJSON.toJSONString());
        System.out.println("MENU JSON CREATED AND SAVED");
        fileWriter.close();
    }


    public void loadMenu() throws ParseException, IOException {
        menu.clear(); // Clear the current menu list
        prebuiltPizzas.clear(); // Clear the current prebuilt pizza list

        JSONParser parser = new JSONParser(); // Create JSON parser
        JSONObject categoriesJSON = (JSONObject) parser.parse(new FileReader(menuFilePath)); // Create JSON object from file

        try {
            System.out.println("LOADING MENU --------------");

            for (Object categoryKey : categoriesJSON.keySet()) { // For each category
                String category = (String) categoryKey;
                JSONArray categoryArray = (JSONArray) categoriesJSON.get(category);

                for (Object menuItemJSON : categoryArray) { // For each menu item JSON in category
                    JSONObject menuItemObj = (JSONObject) menuItemJSON; // Create menu item object from JSON

                    String typeID = (String) menuItemObj.get("Type");
                    String itemName = (String) menuItemObj.get("Item_Name");
                    float price = Float.parseFloat(menuItemObj.get("Price").toString());

                    if (category.equalsIgnoreCase("prebuilt")) {
                        PrebuiltPizza prebuiltPizza = loadPrebuiltPizza(menuItemObj);
                        prebuiltPizzas.add(prebuiltPizza);
                    } else {
                        MenuItem menuItem = new MenuItem(typeID, category, itemName, price);
                        menu.add(menuItem);
                    }

                    System.out.println("MENU ITEM : " + category + " : " + itemName + " : $" + price);
                }
            }

            System.out.println("MENU LOADED --------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PrebuiltPizza loadPrebuiltPizza(JSONObject menuItemObj) {
        String typeID = (String) menuItemObj.get("Type");
        String itemName = (String) menuItemObj.get("Item_Name");
        float price = Float.parseFloat(menuItemObj.get("Price").toString());
        String crustType = (String) menuItemObj.get("Crust");

        // Load toppings
        String toppingsString = (String) menuItemObj.get("Toppings");
        String[] toppingsArray = toppingsString.split(",");

        LinkedList<Ingredient> toppings = new LinkedList<>();
        for (String toppingStr : toppingsArray) {
            String[] toppingParts = toppingStr.split("-");
            String category = toppingParts[0];
            String name = toppingParts[1];
            float toppingPrice = 0.0f; // You may need to adjust this based on your data structure
            Ingredient topping = new Ingredient(typeID, category, name, toppingPrice);
            toppings.add(topping);
        }

        return new PrebuiltPizza(typeID, "PREBUILT", itemName, price, crustType, toppings);
    }

    public void addPrebuiltPizza(PrebuiltPizza prebuiltPizza) {
        prebuiltPizzas.add(prebuiltPizza);
    }

    public LinkedList<PrebuiltPizza> getPrebuiltPizzas() {
        return prebuiltPizzas;
    }


//     INGREDIENTS

    // Save ingredients to file
    public void saveIngredients() throws IOException {
        fileWriter = new FileWriter(inventoryFilePath);

        JSONObject categoriesJSON = new JSONObject();

        // Group ingredients by category
        for (Ingredient ingredient : ingredients) {
            JSONObject ingredientJSON = new JSONObject();
            ingredientJSON.put("Type", ingredient.getTypeID());
            ingredientJSON.put("Category", ingredient.getCategory());
            ingredientJSON.put("Item_Name", ingredient.getName());
            ingredientJSON.put("Price", ingredient.getPrice());

            String category = ingredient.getCategory();
            JSONArray categoryArray = (categoriesJSON.containsKey(category))
                    ? (JSONArray) categoriesJSON.get(category)
                    : new JSONArray();
            categoryArray.add(ingredientJSON);
            categoriesJSON.put(category, categoryArray);
        }

        // Write categories JSON to file
        fileWriter.write(categoriesJSON.toJSONString());
        System.out.println("INVENTORY JSON CREATED AND SAVED");
        fileWriter.close();
    }

    // Load ingredients from file
    public void loadIngredients() throws ParseException, IOException {
        ingredients.clear(); // Clear the current inventory list

        JSONParser parser = new JSONParser(); // Create JSON parser
        JSONObject categoriesJSON = (JSONObject) parser.parse(new FileReader(inventoryFilePath)); // Create JSON object from file

        try {
            System.out.println("LOADING INVENTORY --------------");
            for (Object categoryKey : categoriesJSON.keySet()) {
                String category = (String) categoryKey;
                JSONArray ingredientsJSON = (JSONArray) categoriesJSON.get(category);

                for (Object ingredientItemJSON : ingredientsJSON) {
                    JSONObject ingredientObj = (JSONObject) ingredientItemJSON;
                    String typeID = (String) ingredientObj.get("Type");
                    String itemName = (String) ingredientObj.get("Item_Name");
                    float price = Float.parseFloat(ingredientObj.get("Price").toString());

                    Ingredient ingredient = new Ingredient(typeID, category, itemName, price);
                    ingredients.add(ingredient);

                    System.out.println("INVENTORY ITEM : " + category + " : " + itemName + " : $" + price);
                }
            }
            System.out.println("INVENTORY LOADED --------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // add ingredient
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        System.out.println("ADDED INGREDIENT: " + ingredient);
    }


    // Add menu item (Pizza, Drink, Dessert, etc.)
    public void addMenuItem(MenuItem menuItem) {
        this.menu.add(menuItem);
        System.out.println("ADDED ITEM: " + menuItem);
    }

    // Remove menu item (Pizza, Drink, Dessert, etc.)
    public void removeMenuItem(MenuItem menuItem) {
        this.menu.remove(menuItem);
        System.out.println("REMOVED ITEM: " + menuItem);
    }

    // EMPLOYEES

    // check if there given ID is already in use for employees
    public boolean existingEmployee(String ID) {
        for (Employee employee : employees) {
            if (employee.getID().equals(ID)) return true;
        }
        return false;
    }

    //  checks if employee login id matches any id in employee list and returns employee or null
    public Employee employeeLogin(LoginEvent event) throws ParseException, IOException {
        loadEmployees(); // refresh list
        for (Employee employee : employees) {
            if (event.getID().equals(employee.getID())) {
                return employee;
            }
        }
        return null;
    }

    // add employee
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    // remove employee
    public void removeEmployee(String ID) {
        Employee e = null;

        for (Employee employee : employees) {
            if (employee.getID().equals(ID)) e = employee;
        }
        System.out.println(e.toString());
        employees.remove(e);
    }

    // save employees
    public void saveEmployees() throws IOException {

        fileWriter = new FileWriter(employeesFilePath);
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        // for each employee create json object and write to file
        for (Employee employee : employees) {
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
        sb.deleteCharAt(sb.length() - 1);
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

        employees.clear();

        // LOAD EMPLOYEES---------------------------------------------
        JSONParser parser = new JSONParser(); //create JSON parser
        JSONArray employeesJSON = (JSONArray) parser.parse(new FileReader(employeesFilePath)); // create array from file

        try {
            System.out.println("LOADING EMPLOYEES --------------");
            for (Object employeeJSON : employeesJSON) { // for each employee JSON in employees file

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
                if (newEmployee.isAdmin())
                    System.out.println("ADMIN LOADED: " + newEmployee.getID() + ":" + newEmployee.getFirstName());
                else System.out.println("EMPLOYEE LOADED: " + newEmployee.getID() + ":" + newEmployee.getFirstName());
            }
            System.out.println("EMPLOYEES LOADED --------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CUSTOMERS

    // add customer
    public void addCustomer(Customer customer) throws IOException {
        this.customers.add(customer);
        saveCustomers(); // save all customers
    }

    // check if given phoneNumber is already in use
    public boolean existingCustomer(String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getID().equals(phoneNumber)) return true;
        }
        return false;
    }

    //  checks if customer login id matches any id in customer list and returns customer or null
    public Customer customerLogin(LoginEvent event) throws ParseException, IOException {
        loadCustomers(); // refresh list
        for (Customer customer : customers) {
            if (event.getID().equals(customer.getID())) {
                return customer;
            }
        }
        return null;
    }

    //save customers
    public void saveCustomers() throws IOException {

        fileWriter = new FileWriter(customersFilePath);
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        // for each employee create json object and write to file
        for (Customer customer : customers) {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("Phone_Number", customer.getPhoneNumber());
            jsonObject.put("First_Name", customer.getFirstName());
            jsonObject.put("Last_Name", customer.getLastName());
            jsonObject.put("Address", customer.getAddress());
            jsonObject.put("Details", customer.getDetails());


            sb.append(jsonObject.toJSONString() + ',');
        }
        sb.deleteCharAt(sb.length() - 1);
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
        customers.clear(); // clear current list of customers

        // LOAD CUSTOMERS---------------------------------------------
        JSONParser parser = new JSONParser(); //create JSON parser
        JSONArray customersJSON = (JSONArray) parser.parse(new FileReader(customersFilePath)); // create JSONarray from file

        try {
            System.out.println("LOADING CUSTOMERS --------------");
            for (Object customerJSON : customersJSON) { // for each employee JSON in employees file

                JSONObject customer = (JSONObject) customerJSON; // create employee object from json

                // gather values

                String phoneNumber = (String) customer.get("Phone_Number");
                String firstName = (String) customer.get("First_Name");
                String lastName = (String) customer.get("Last_Name");
                String address = (String) customer.get("Address");
                String details = (String) customer.get("Details");


                // add new employee to current employees list

                Customer newCustomer = new Customer(phoneNumber, firstName, lastName, address, details);
                customers.add(newCustomer); // add each customer to list

                //log message
                System.out.println("CUSTOMER LOADED: " + newCustomer.getID() + ":" + newCustomer.getFirstName());
            }
            System.out.println("CUSTOMERS LOADED --------------");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // CUSTOMER ORDERS

    // save orders to file
    public void saveOrders() throws IOException {
        fileWriter = new FileWriter(ordersFilePath);

        JSONObject categoriesJSON = new JSONObject();

        // Group orders by category (order ID)
        for (Customer customer : customers) {
            for (Order order : customer.getOrders()) {

                JSONObject orderJSONObject = new JSONObject();
                orderJSONObject.put("ID", order.getID());
                orderJSONObject.put("Items", order.getItems());
                orderJSONObject.put("Total", order.getTotalPrice());

                String category = order.getID(); // Use customer ID as the category (order ID)

                StringBuilder itemsString = new StringBuilder();

                for (MenuItem item : order.getItems()) {
                    itemsString.append(item.getCategory() + "-" + item.getItemName() + ",");
                }

                itemsString.deleteCharAt(itemsString.length() - 1); // delete last comma
                orderJSONObject.put("Items", itemsString.toString());

                JSONArray categoryArray = (categoriesJSON.containsKey(category))
                        ? (JSONArray) categoriesJSON.get(category)
                        : new JSONArray();
                categoryArray.add(orderJSONObject);
                categoriesJSON.put(category, categoryArray);
            }
        }
        // Write categories JSON to file
        fileWriter.write(categoriesJSON.toJSONString());
        System.out.println("ORDERS JSON CREATED AND SAVED");
        fileWriter.close();
    }


    // load orders
    public void loadOrders() throws IOException {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(ordersFilePath)) {
            JSONObject categoriesJSON = (JSONObject) parser.parse(reader);

            for (Object categoryKey : categoriesJSON.keySet()) {
                String category = (String) categoryKey;
                JSONArray ordersArray = (JSONArray) categoriesJSON.get(category);

                for (Object orderObject : ordersArray) {
                    JSONObject orderJSON = (JSONObject) orderObject;
                    String orderID = (String) orderJSON.get("ID");
                    String itemsString = (String) orderJSON.get("Items");
                    float totalPrice = ((Number) orderJSON.get("Total")).floatValue();

                    String[] itemTokens = itemsString.split(",");

                    LinkedList<MenuItem> items = new LinkedList<>();
                    for (String itemToken : itemTokens) {
                        String[] itemInfo = itemToken.split("-");
                        String typeID = itemInfo[0];
                        String itemName = itemInfo[1];

                        // Check if the item is a PrebuiltPizza
                        if (typeID.equals("PREBUILT")) {
                            // Assume you have methods to retrieve the PrebuiltPizza details
                            PrebuiltPizza prebuiltPizza = getPrebuiltPizzaByName(itemName);
                            if (prebuiltPizza != null) {
                                items.add(prebuiltPizza);
                            }
                        } else {
                            MenuItem menuItem = getMenuItemByName(itemName);
                            items.add(menuItem);
                        }
                    }

                    Order order = new Order(orderID, items, totalPrice);
                    Customer customer = findCustomerByID(category);
                    if (customer != null) {
                        customer.getOrders().add(order);
                        ;
                    } else {
                        // Handle case where customer is not found
                    }
                }
            }

            System.out.println("ORDERS LOADED");
            for (Customer customer : customers) {
                System.out.println(customer.getOrders());
            }
        } catch (ParseException e) {
            e.printStackTrace(); // Handle parsing exception
        }
    }


    // PAYMENTS

    // save payments to file
    public void savePayments() throws IOException {
        fileWriter = new FileWriter(paymentsFilePath);

        JSONObject categoriesJSON = new JSONObject();

        // Group payments by category (payment ID)
        for (Customer customer : customers) {
            for (int i = 0; i < customer.getPayments().size(); i++) {
                Payment payment = customer.getPayments().get(i);

                JSONObject paymentJSONObject = new JSONObject();
                paymentJSONObject.put("ID", payment.getID());
                paymentJSONObject.put("Card_Name", ((CardPayment) payment).getCardName());
                paymentJSONObject.put("Card_Number", ((CardPayment) payment).getCardNumber());
                paymentJSONObject.put("Exp_Date", ((CardPayment) payment).getExpDate());
                paymentJSONObject.put("CVC", ((CardPayment) payment).getCVC());

                String category = customer.getID(); // Use customer ID as the category (payment ID)

                JSONArray categoryArray = (categoriesJSON.containsKey(category))
                        ? (JSONArray) categoriesJSON.get(category)
                        : new JSONArray();
                categoryArray.add(paymentJSONObject);
                categoriesJSON.put(category, categoryArray);
            }
        }

        // Write categories JSON to file
        fileWriter.write(categoriesJSON.toJSONString());
        System.out.println("PAYMENT JSON CREATED AND SAVED");
        fileWriter.close();
    }

    //  load payment
    public void loadPayments() throws ParseException, IOException {
        // Clear all listed payments
        for (Customer customer : customers) {
            customer.getPayments().clear();
        }

        // LOAD PAYMENTS---------------------------------------------
        JSONParser parser = new JSONParser(); // Create JSON parser
        JSONObject categoriesJSON = (JSONObject) parser.parse(new FileReader(paymentsFilePath)); // Create JSON object from file

        try {
            System.out.println("LOADING PAYMENTS --------------");

            for (Object categoryKey : categoriesJSON.keySet()) { // For each category (customer ID)
                String category = (String) categoryKey;
                JSONArray paymentsArray = (JSONArray) categoriesJSON.get(category); // Get payments array

                for (Object paymentJSON : paymentsArray) { // For each payment JSON in category
                    JSONObject paymentObj = (JSONObject) paymentJSON; // Create payment object from JSON

                    String ID = (String) paymentObj.get("ID"); // In this case, the category is the ID
                    String cardName = (String) paymentObj.get("Card_Name");
                    String cardNumber = (String) paymentObj.get("Card_Number");
                    String expDate = (String) paymentObj.get("Exp_Date");
                    String CVC = (String) paymentObj.get("CVC");

                    Payment newPayment = new CardPayment(ID, cardName, cardNumber, expDate, CVC);
                    for (Customer customer : customers) {
                        if (customer.getID().equals(ID)) {
                            customer.getPayments().add(newPayment);
                        }
                    }

                    System.out.println("PAYMENT METHOD LOADED : " + ID + " : " + newPayment);
                }
            }

            System.out.println("PAYMENTS LOADED --------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Customer findCustomerByID(String customerID) {
        for (Customer customer : customers) {
            if (customer.getID().equals(customerID)) {
                return customer;
            }
        }
        return null;
    }

    private PrebuiltPizza getPrebuiltPizzaByName(String name) {
        for (PrebuiltPizza pizza : prebuiltPizzas) {
            if (pizza.getItemName().equals(name)) {
                return pizza;
            }
        }
        return null;
    }

    private MenuItem getMenuItemByName(String name) {
        for (MenuItem item : menu) {
            System.out.println(item.getItemName());
            System.out.println(name);
            if (item.getItemName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }


    // getters

    // return all menu items
    public LinkedList<MenuItem> getMenu() {
        return menu;
    }

    // return all available ingredients
    public LinkedList<Ingredient> getInventory() {
        return ingredients;
    }

    // return employee list
    public LinkedList<Employee> getEmployees() { // return employees
        return employees;
    }

    public void removeIngredient(Ingredient selectedItem) {
        ingredients.remove(selectedItem);
    }

    public void removePizza(PrebuiltPizza pizza) {
        prebuiltPizzas.remove(pizza);
    }

    public void createOrder(Order order) {
        for (Customer customer : customers) {
            if (customer.getID().equals(order.getID())) {
                customer.getOrders().add(order);
            }
        }
//        saveOrders();
    }
}
