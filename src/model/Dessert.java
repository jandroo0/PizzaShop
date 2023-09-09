package model;

public class Dessert extends MenuItem {

    private Size size;

    public Dessert(String category, String itemName, float price, Size size) {
        super(category, itemName, price);
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
