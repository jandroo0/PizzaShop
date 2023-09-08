package model;

public class MenuItem {

    private String itemName;
    private float price;

    public MenuItem(String itemName, float price) {
        this.itemName = itemName;
        this.price = price;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
