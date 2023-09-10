package model;

public class MenuItem {

    private String category;
    private String itemName;
    private float price;

    public MenuItem(String category, String itemName, float price) {
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String toString() {
        return itemName + " $" + price;
    }
}
