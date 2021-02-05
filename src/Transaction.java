/*****************************************
 ** File:    Transaction.java
 ** Project: CSCE 314 Project 1, Fall 2020
 ** Author:  Brandon Gonzales, Ryan Hall
 ** Date:    11/07/20
 ** Section: 501
 ** E-mail:  ryan.hall.tx@tamu.edu
 **
 **   This file contains the code for any transaction that
 **   happens on the register. This includes the type of transaction
 **   it was as well as the total price of the transaction.
 **
 ***********************************************/

public class Transaction {
    private double price;
    private boolean value; // 0 means negative (i.e. costs, purchase orders), 1 mean positive (i.e. income, customer transactions)

    public Transaction(double price, boolean value) {
        this.price = price;
        this.value = value;
    }

    // getPrice()
    // Gets the overall transaction total
    public double getPrice() {
        return price;
    }

    // setPrice()
    // Sets the overall transaction total
    public void setPrice(double price) {
        this.price = price;
    }

    // getHashablePrice
    // Returns the overall total as a format of "$ + total" (ex. "$2.50")
    public String getHashablePrice() {
        if (getValue()) {
            return "$" + price;
        } else {
            return "-$" + price;
        }
    }

    // getValue()
    // Gets the transaction's value
    public boolean getValue() {
        return value;
    }

    // setValue()
    // Sets the transaction's value
    public void setValue(boolean value) {
        this.value = value;
    }
}