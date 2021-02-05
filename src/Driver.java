/*****************************************
** File:    Driver.java
** Project: CSCE 314 Project 1, Fall 2020
** Author:  Brandon Gonzales, Ryan Hall
** Date:    11/07/20
** Section: 501
** E-mail:  ryan.hall.tx@tamu.edu 
**
**   This file contains the main driver program for Project 1.
**   This file reads the input from files and constructs Merkle
**   Trees based off of it. These are then used to compare hashes
**   with their expected values.
**
***********************************************/

/*

PROJECT DESCRIPTION: 
In this project, Professor Lupoli has recently opened up a Lemon Squeezy franchise by his home in Maryland. 
At the end of every night, Professor Lupoli has to count the transactions for the night to verify by hand 
that they add up to the reported sales on the cash registers. One night, he notices that his cash register 
actually prints out a hash value referring to the hash of all the combined transactions for the night. 
Armed with this knowledge, he asks his students to come up with a solution in which given the transactions 
for the night, he can receive a value that he can compare to the cash registers outputted hash value. He 
mentions, “This will help tell me if each cash register is playing nice with us, let’s put it that way”.

*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		// Opening a file that is placed inside of the project directory
		File file = new File("input.txt");
		Scanner reader = new Scanner(file);

		// Initialize register
		CashRegister register = new CashRegister();

		// While there is another transaction in the input
		while (reader.hasNextLine()) {
			// Read in the transaction and split it along the spaces
			String transaction = reader.nextLine();
			String[] parts = transaction.split(" ");

			// Correct number of arguments check
			if (parts.length != 4) {
				System.out.println("ERROR: Transaction has incorrect amount of arguments! (Expected: 4, Actual: " + parts.length + ")");
				System.exit(0);
			}

			// Split up all the parts
			String value = parts[0];
			double price = Double.parseDouble(parts[1]);
			int type = Integer.parseInt(parts[2]);
			int quantity = Integer.parseInt(parts[3]);

			// If value is "+" that means it's a Customer Order
			if (value.equals("+")) {
				register.addTransaction(new CustomerOrder(price, type, quantity));
			// If value is "-" that means it's a Purchase Order
			} else if (value.equals("-")) {
				register.addTransaction(new PurchaseOrder(price, type, quantity));
			// Else the input in unreadable
			} else {
				System.out.println("ERROR: Transaction type could not be identified");
				System.exit(0);
			}
		}

		System.out.println("Total Income: $" + register.getTotalIncome());
		System.out.println("Total Expenses: $" + register.getTotalExpenses());
		System.out.println("Total Profits: $" + register.getTotalProfit());

		System.out.println(register.getTransactions());
		System.out.println();

		// Create a new Merkle Tree
		// For every transaction, add it to the tree as a leaf
		MerkleTree tree = new MerkleTree();
		for (Object o : register.getTransactions()) {
			if (o instanceof PurchaseOrder) {
				tree.insert(new MerkleTreeNode(((PurchaseOrder) o).getHashablePrice()));
			} else if (o instanceof CustomerOrder) {
				tree.insert(new MerkleTreeNode(((CustomerOrder) o).getHashablePrice()));
			}
		}

		// Print tree
		System.out.println(tree);
	}

}