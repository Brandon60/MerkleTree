/*****************************************
 ** File:    PurchaseOrder.java
 ** Project: CSCE 314 Project 1, Fall 2020
 ** Author:  Brandon Gonzales, Ryan Hall
 ** Date:    11/07/20
 ** Section: 501
 ** E-mail:  ryan.hall.tx@tamu.edu
 **
 **   This file contains the code for any PurchaseOrder.
 **   Purchase orders have a type of item and quantity, as well
 **   as price.
 **
 ***********************************************/

public class PurchaseOrder extends Transaction {

    private int quantity;
    private int type;
        // 1 - Cups
        // 2 - Straws
        // 3 - Lids
        // 4 - Napkins
        // 5 - Sugar
        // 6 - Lemons

    public PurchaseOrder(double price, int type, int quantity) {
        super(price * quantity, false);
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
                if (plural) { return this.quantity + "x Cups"; } else { return this.quantity + " Cup"; }
            case 2:
                if (plural) { return this.quantity + "x Straws"; } else { return this.quantity + " Straw"; }
            case 3:
                if (plural) { return this.quantity + "x Lids"; } else { return this.quantity + " Lid"; }
            case 4:
                if (plural) { return this.quantity + "x Napkins"; } else { return this.quantity + " Napkin"; }
            case 5:
                if (plural) { return this.quantity + " bags of Sugar"; } else { return this.quantity + " bag of Sugar"; }
            case 6:
                if (plural) { return this.quantity + "x Lemons"; } else { return this.quantity + " Lemon"; }
        }
        return "ERROR: Item not found in database!";
    }
}