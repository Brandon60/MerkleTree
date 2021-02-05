/*****************************************
** File:    Node.java
** Project: CSCE 314 Project 1, Fall 2020
** Author:  Brandon Gonzales, Ryan Hall
** Date:    11/07/20
** Section: 501
** E-mail:  ryan.hall.tx@tamu.edu 
**
**   This file contains the code necessary for any node within a Merkel Tree.
**   This program contains the nodes hash value as well as an ABSTRACT function
**   declaration for computing the hash. There are also setters and getters
**   available.
**
***********************************************/

public abstract class Node {
	// Stored hash value for a Node
	private String hash = "";
	private MerkleTreeNode parent;

	public Node(String hash) {
		this.hash = hash;
		parent = null;
	}

	// computeHash()
	// Computes the data sets hash
	public abstract String computeHash();
	public abstract String computeHash(String data);
	
	// getHash()
	// Gets the node's hash
	public String getHash() {return hash;}

	// setHash()
	// Sets the node's hash
	public void setHash(String hash) {
		this.hash = hash;
	}

	// getParent()
	// Gets the node's parent
	public MerkleTreeNode getParent() {
		return parent;
	}

	// setParent()
	// Sets the node's parent
	public void setParent(MerkleTreeNode parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return getHash();
	}
}