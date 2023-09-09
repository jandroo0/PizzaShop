package model;

import java.util.LinkedList;

/*Pizza class which will hold the different options for each pizza*/

public class PrebuiltPizza extends MenuItem {

    private Size size;
    private String cheeseAmt;
    private String sauceAmt;
    private String crustType;

    private LinkedList<Ingredient> toppings;

    public PrebuiltPizza(String category, String name, float price, Size size, String cheeseAmt, String sauceAmt, String crustType, LinkedList<Ingredient> toppings) {
        super(category, name, price);
        this.size = size;
        this.cheeseAmt = cheeseAmt;
        this.sauceAmt = sauceAmt;
        this.crustType = crustType;
        this.toppings = toppings;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getCheeseAmt() {
        return cheeseAmt;
    }

    public void setCheeseAmt(String cheeseAmt) {
        this.cheeseAmt = cheeseAmt;
    }

    public String getSauceAmt() {
        return sauceAmt;
    }

    public void setSauceAmt(String sauceAmt) {
        this.sauceAmt = sauceAmt;
    }

    public String getCrustType() {
        return crustType;
    }

    public void setCrustType(String crustType) {
        this.crustType = crustType;
    }

    public LinkedList<Ingredient> getToppings() {
        return toppings;
    }

    public void setToppings(LinkedList<Ingredient> toppings) {
        this.toppings = toppings;
    }


}
