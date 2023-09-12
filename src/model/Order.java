package model;

import java.util.LinkedList;

public class Order {

    String ID;
    LinkedList<MenuItem> items;


    public Order(String ID, LinkedList<MenuItem> items) {
        this.ID = ID;
        this.items = items;

    }


    public String getID() {
        return ID;
    }

    public LinkedList<MenuItem> getItems() {
        return items;
    }
}
