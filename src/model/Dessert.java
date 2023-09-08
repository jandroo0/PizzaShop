package model;

public class Dessert extends MenuItem {

    private Size size;

    public Dessert(String itemName, float price, Size size) {
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
