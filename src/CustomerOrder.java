/*****************************************
 ** File:    CustomerOrder.java
 ** Project: CSCE 314 Project 1, Fall 2020
 ** Author:  Brandon Gonzales, Ryan Hall
 ** Date:    11/07/20
 ** Section: 501
 ** E-mail:  ryan.hall.tx@tamu.edu
 **
 **   This file contains the code for any CustomerOrder.
 **   Customer orders have a type of item and quantity, as well
 **   as price.
 **
 ***********************************************/

public class CustomerOrder extends Transaction {

    private int quantity;
    private int type;
        // 1 - Small Lemonade
        // 2 - Medium Lemonade
        // 3 - Large Lemonade
        // 4 - Frozen Lemonade
        // 5 - Strawberry Lemonade

    public CustomerOrder(double price, int type, int quantity) {
        super(price * quantity, true);
        this.type = type;
        this.quantity = quantity;
    }

    // getType()
    // Gets the transaction's type
    public int getType() {
        return type;
    }

    // setType()
    // Sets the transaction's type
    public void setType(int type) {
        this.type = type;
    }

    // getQuantity
    // Gets the transaction's quantity
    public int getQuantity() {
        return quantity;
    }

    // setQuantity
    // Sets the transaction's quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        boolean plural = this.quantity > 1;
        switch (this.getType()) {
            case 1:
                if (plural) { return this.quantity + "x Small Lemonades"; } else { return this.quantity + " Small Lemonade"; }
            case 2:
                if (plural) { return this.quantity + "x Medium Lemonades"; } else { return this.quantity + " Medium Lemonade"; }
            case 3:
                if (plural) { return this.quantity + "x Large Lemonades"; } else { return this.quantity + " Large Lemonade"; }
            case 4:
                if (plural) { return this.quantity + "x Frozen Lemonades"; } else { return this.quantity + " Frozen Lemonade"; }
            case 5:
                if (plural) { return this.quantity + "x Strawberry Lemonades"; } else { return this.quantity + " Strawberry Lemonade"; }
        }
        return "ERROR: Item not found in database!";
    }
}
