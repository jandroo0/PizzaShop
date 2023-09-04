package model;

import java.util.LinkedList;

/*Pizza class which will hold the different options for each pizza*/

public class Pizza {

    private String size;
    private String cheeseAmt;
    private String sauceAmt;
    private String crustType;

    private LinkedList<String> toppings;

    public Pizza(String size, String cheeseAmt, String sauceAmt, String crustType, LinkedList<String> toppings) {
        this.size = size;
        this.cheeseAmt = cheeseAmt;
        this.sauceAmt = sauceAmt;
        this.crustType = crustType;
        this.toppings = toppings;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
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

    public LinkedList<String> getToppings() {
        return toppings;
    }

    public void setToppings(LinkedList<String> toppings) {
        this.toppings = toppings;
    }
}
