/*****************************************
 ** File:    CashRegister.java
 ** Project: CSCE 314 Project 1, Fall 2020
 ** Author:  Brandon Gonzales, Ryan Hall
 ** Date:    11/07/20
 ** Section: 501
 ** E-mail:  ryan.hall.tx@tamu.edu
 **
 **   This file contains the code for a cash register in the shop.
 **   The cash register keeps track of all transactions going through
 **   it and has functions to determine total profits, expenses, and income.
 **
 ***********************************************/

import java.util.ArrayList;

public class CashRegister {
    ArrayList<Object> transactions;

    public CashRegister() {
        transactions = new ArrayList<>();
    }

    // getTransactions()
    // Gets all recorded transactions
    public ArrayList<Object> getTransactions() {
        return transactions;
    }

    // addTransaction()
    // Adds the transaction to transactions if it is a PurchaseOrder or a CustomerOrder
    public boolean addTransaction(Object transaction) {
        if (transaction instanceof PurchaseOrder || transaction instanceof CustomerOrder) {
            transactions.add(transaction);
            return contains(transaction);
        }
        return false;
    }

    // removeTransaction()
    // Removes the transaction if it is a PurchaseOrder or a CustomerOrder
    public boolean removeTransaction(Object transaction) {
        if (transaction instanceof PurchaseOrder || transaction instanceof CustomerOrder) {
            transactions.remove(transaction);
            return !contains(transaction);
        }
        return false;
    }

    // contains()
    // Checks the transactions list to see if an object in getTransactions equals the target
    public boolean contains(Object target) {
        return this.getTransactions().contains(target);
    }

    // getTotalExpenses()
    // Adds up all the PurchaseOrders and returns the total
    public double getTotalExpenses() {
        double totalExpenses = 0;
        for (Object o : this.getTransactions()) {
            if (o instanceof PurchaseOrder && !((PurchaseOrder) o).getValue()) {
                double expense = ((PurchaseOrder) o).getPrice();
                totalExpenses += expense;
            }
        }
        return totalExpenses * -1;
    }

    // getTotalIncome()
    // Adds up all the CustomerORders and returns the total
    public double getTotalIncome() {
        double totalIncome = 0;
        for (Object o : this.getTransactions()) {
            if (o instanceof CustomerOrder && ((CustomerOrder) o).getValue()) {
                double income = ((CustomerOrder) o).getPrice();
                totalIncome += income;
            }
        }
        return totalIncome;
    }

    // getTotalProfit()
    // Difference between income and expenses
    public double getTotalProfit() {
        return getTotalIncome() + getTotalExpenses();
    }
}