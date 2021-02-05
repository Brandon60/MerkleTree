/*****************************************
 ** File:    MerkleTree.java
 ** Project: CSCE 314 Project 1, Fall 2020
 ** Author:  Brandon Gonzales, Ryan Hall
 ** Date:    11/07/20
 ** Section: 501
 ** E-mail:  ryan.hall.tx@tamu.edu
 **
 **   This file contains the code necessary for a Merkel Tree.
 **   This program contains a root and maintains a list of leaves.
 **   It has code to construct the tree as well as how to add and
 **   remove leaves.
 **
 ***********************************************/

import java.util.*;

public class MerkleTree {

    private MerkleTreeNode root;
    private ArrayList<MerkleTreeNode> leaves;

    public MerkleTree() {
        this.leaves = new ArrayList<>();
    }

    public MerkleTreeNode getRoot() {
        return root;
    }

    public void setRoot(MerkleTreeNode root) {
        this.root = root;
    }

    public ArrayList<MerkleTreeNode> getLeaves() {
        return leaves;
    }

    private void addLeaf(MerkleTreeNode leaf) {
        // Add leaf
        this.leaves.add(leaf);
    }

    private boolean removeLeaf(MerkleTreeNode target) {
        // For every leaf
        for (MerkleTreeNode leaf : leaves) {
            // If the leaf hash matches the target hash, remove it
            if (leaf.getHash().equals(target.getHash())) {
                this.leaves.remove(leaf);
                return true;
            }
        }

        return false;
    }

    // constructTree
    // This will initialize all the rest of the nodes, as well as set the root hash.
    // This only requires the leaves in order to construct itself
    public void constructTree() {
        // Create a primary and secondary ArrayList
        // toProcess contains all the nodes that need to be processed (i.e. nodes on same depth)
        // processNext contains all the nodes that will get processed on the next run (i.e. parents)
        ArrayList<MerkleTreeNode> toProcess = new ArrayList<>();
        ArrayList<MerkleTreeNode> processNext = new ArrayList<>();

        // For every pair of leaves, create a new parent node for them
        for (int i = 0; i / 2 < leaves.size() / 2; i += 2) {
            MerkleTreeNode newNode = new MerkleTreeNode(leaves.get(i), leaves.get(i+1));
            toProcess.add(newNode);
        }

        // If there is one leaf left (occurs only in odd # of leaves)
        // create a parent node for that leaf
        if (leaves.size() % 2 == 1) {
            MerkleTreeNode newNode = new MerkleTreeNode(leaves.get(leaves.size() - 1), null);
            toProcess.add(newNode);
        }

        // How many nodes are at this depth
        //         0            numOfNodesInLayer = 1
        //    0         0       numOfNodesInLayer = 2
        // 0    0    0    0     numOfNodesInLayer = 4
        int numOfNodesInLayer = toProcess.size();

        // While we aren't at the root
        while (numOfNodesInLayer > 1) {
            // while there are still some nodes to process
            while (toProcess.size() != 0) {
                // Create a newNode
                MerkleTreeNode newNode;

                // If we have a pair we can make a new node for
                if (toProcess.size() >= 2) {
                    newNode = new MerkleTreeNode(toProcess.get(0), toProcess.get(1));

                    processNext.add(newNode);
                    toProcess.remove(1);
                    toProcess.remove(0);
                // Else we have a node with only one child
                } else {
                    newNode = new MerkleTreeNode(toProcess.get(0), null);

                    processNext.add(newNode);
                    toProcess.remove(0);
                }
            }

            // exchange lists and reset processNext
            toProcess = processNext;
            processNext = new ArrayList<>();

            // update numOfNodesInLayer
            numOfNodesInLayer = toProcess.size();
        }

        // If there is still a hash left (i.e. the root)
        if (toProcess.size() == 1) {
            setRoot(toProcess.get(0));
        // Else there are no leaves left, set root to null
        } else if (leaves.size() == 0) {
            setRoot(null);
        }

    }

    // insert()
    // insert leaf into the tree, updates tree, returns whether the node was added or not
    public boolean insert(MerkleTreeNode node) {
        if (contains(node.getHash())) {
            return false;
        } else {
            addLeaf(node);
            constructTree();
            return contains(node.getHash());
        }
    }

    // remove()
    // remove leaf from the tree, updates tree, returns whether the node was removed or not
    public boolean remove(MerkleTreeNode node) {
        if (contains(node.getHash())) {
            removeLeaf(node);
            constructTree();
            return !contains(node.getHash());
        } else {
            return true;
        }
    }

    // contains()
    // Driver code for the contains function, starts at root if node isn't specified
    public boolean contains(String hash) {
        return contains(getRoot(), hash);
    }

    // contains()
    // Returns true if the hash is present in the tree, or false if not
    public boolean contains(MerkleTreeNode node, String hash) {
        // If the root is empty, the tree is empty ; return false
        if (getRoot() == null) {
            return false;
        // If the current node equals the target hash ; return true
        } else if (node.getHash().equals(hash)) {
            return true;
        // If both children aren't null, go down both sides
        } else if (node.getLeft() != null && node.getRight() != null){
            return contains(node.getLeft(), hash) || contains(node.getRight(), hash);
        // If the left child isn't null, go down the left
        } else if (node.getLeft() != null) {
            return contains(node.getLeft(), hash);
        // If the right child isn't null, go down the right
        } else if (node.getRight() != null) {
            return contains(node.getRight(), hash);
        }

        // Else you've reached a leaf, return false
        return false;
    }

    // toString()
    // Returns a string that displays the tree bottom-up (i.e. leaves printed first, root printed last)
    @Override
    public String toString() {
        // Header of the string
        String returnString = "Current Merkle Tree Build " + "(# of leaves: " + leaves.size() + ")";
        returnString += "\n";

        // If there are no leaves, say the tree is empty
        if (leaves.size() == 0) {
            return returnString + "The tree is empty. No tree to print.\n";
        }

        // Create a primary and secondary ArrayList
        // toProcess contains all the nodes that need to be processed (i.e. nodes on same depth)
        // processNext contains all the nodes that will get processed on the next run (i.e. parents)
        ArrayList<MerkleTreeNode> toProcess = new ArrayList<>();
        ArrayList<MerkleTreeNode> processNext = new ArrayList<>();

        // Cycle through all leaves, print leaf, and for every pair add the parent to the toProcess list
        for (int i = 0; i < leaves.size(); i++) {
            returnString += leaves.get(i) + "\t";
            if (i % 2 == 0) {
                toProcess.add(leaves.get(i).getParent());
            }
        }

        returnString += "\n";

        // How many nodes are at this depth
        //         0            numOfNodesInLayer = 1
        //    0         0       numOfNodesInLayer = 2
        // 0    0    0    0     numOfNodesInLayer = 4
        int numOfNodesInLayer = toProcess.size();

        // While we aren't at the root
        while (numOfNodesInLayer > 1) {
            // add the current depths nodes to the list
            for (int i = 0; i < toProcess.size(); i++) {
                returnString += (toProcess.get(i) + "\t");
                // add their parents to be processed next
                if (i % 2 == 0) {
                    processNext.add(toProcess.get(i).getParent());
                }
            }
            returnString += "\n";

            // exchange lists and reset processNext
            toProcess = processNext;
            processNext = new ArrayList<>();

            // update numOfNodesInLayer
            numOfNodesInLayer = toProcess.size();
        }

        // Add the root
        returnString += toProcess.get(0) + "\n";

        return returnString;
    }
}