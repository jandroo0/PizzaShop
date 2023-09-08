package model;

public class Drink extends MenuItem {

    private Size size;

    public Drink(String itemName, float price, Size size) {
        super(itemName, price);
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
