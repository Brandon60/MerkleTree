/*****************************************
 ** File:    MerkleTreeNode.java
 ** Project: CSCE 314 Project 1, Fall 2020
 ** Author:  Brandon Gonzales, Ryan Hall
 ** Date:    11/07/20
 ** Section: 501
 ** E-mail:  ryan.hall.tx@tamu.edu
 **
 **   This file contains the code necessary for a node within a Merkle Tree.
 **   This program contains a left and right reference for children in the tree
 **   and inhereits variables and functions from Node.java as well.
 **
 ***********************************************/

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MerkleTreeNode extends Node {

    // Variable Declarations
    private MerkleTreeNode left;
    private MerkleTreeNode right;

    // Constructor
    // Builds a balanced node for the tree
    // Only to be used during a creation of a Merkle Tree node that has a left and right child. (NOT the data level (i.e. leaves))
    public MerkleTreeNode(MerkleTreeNode left, MerkleTreeNode right) {
        // Cannot use methods until super has been initialized. Initializing it to be null.
        super(null);

        // Setting the children to be the parameters that were passed in
        this.left = left;
        this.right = right;

        // All nodes will have a left child, even nodes with only one child.
        // Set the left node's parent to be this node
        this.left.setParent(this);

        // If the node also has a right child that isn't null
        // Set the right node's parent to be this node
        if (this.right != null) {
            this.right.setParent(this);
        }

        // Set this node's hash
        this.setHash(computeHash());

    }

    // Constructor
    // Builds a data level node for the tree (i.e. leaf)
    public MerkleTreeNode(String data) {
        // Cannot use methods until super has been initialized. Initializing it to be null.
        super(null);

        // Setting the children to be null since this is the leaf and will have no children
        this.left = null;
        this.right = null;

        // Set this leaf's hash
        this.setHash(computeHash(data));

    }

    // isLeaf()
    // Return whether the current node is a leaf or not
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    // getLeft()
    // Return the reference to the left child
    public MerkleTreeNode getLeft() {
        return left;
    }

    // setLeft()
    // Sets the objects reference to the left child
    public void setLeft(MerkleTreeNode node) {
        left = node;
    }

    // getRight()
    // Return the reference to the right child
    public MerkleTreeNode getRight() {
        return right;
    }

    // setRight()
    // Sets the objects reference to the right child
    public void setRight(MerkleTreeNode node) {
        right = node;
    }

    // computeHash()
    // Computes the concatenated hash of both of it's childrens hashes
    @Override
    public String computeHash() {
        // Gather the left node's hash
        // If there are two children grab the right one too
        String toHash;
        if (right != null) {
            toHash = left.getHash() + right.getHash();
        } else {
            return left.getHash();
        }

        // Initialize the hash
        String hash = "";

        // Try to hash the hashs
        try {
            // Select SHA-256 as the encoding standard
            // Might encounter NoSuchAlgorithException error
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(toHash.getBytes(StandardCharsets.UTF_8));

            // Start building the hexadecimal string
            StringBuilder hexadecimalString = new StringBuilder(2 * encodedhash.length);
            for (int i = 0; i < encodedhash.length; i++) {
                String hexadecimalValue = Integer.toHexString(0xff & encodedhash[i]);
                // if the hexadecimal value is only one in length add a 0 to make it two in length
                if (hexadecimalValue.length() == 1) {
                    hexadecimalString.append('0');
                }

                // append all these hexadecimal values
                hexadecimalString.append(hexadecimalValue);
            }
            // convert the hexadecimal string to a string
            hash = hexadecimalString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Could not find the algorithm " + e);
        }

        return hash;
    }

    // computeHash()
    // Computes the hash of the data passed into it
    @Override
    public String computeHash(String data) {
        // the value to hash will be the string of data
        String toHash = data;
        String hash = "";

        // Try to hash the data
        try {
            // Select SHA-256 as the encoding standard
            // Might encounter NoSuchAlgorithmException error
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(toHash.getBytes(StandardCharsets.UTF_8));

            // Start building the hexadecimal string
            StringBuilder hexadecimalString = new StringBuilder(2 * encodedhash.length);
            for (int i = 0; i < encodedhash.length; i++) {
                String hexadecimalValue = Integer.toHexString(0xff & encodedhash[i]);
                // if the hexadecimal value is only one in length add a 0 to make it two in length
                if (hexadecimalValue.length() == 1) {
                    hexadecimalString.append('0');
                }

                // append all these hexadecimal values
                hexadecimalString.append(hexadecimalValue);
            }
            // convert the hexadecimal string to a string
            hash = hexadecimalString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Could not find the algorithm " + e);
        }

        return hash;
    }

    // toString()
    // Returns the hash of the node
    @Override
    public String toString() {
        return this.getHash();
    }
}