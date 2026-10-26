package model;

/**
 * Node khusus untuk Binary Search Tree (mengolah nilai integer).
 */
public class BSTNode extends Node {
    public int data;

    public BSTNode(int data) {
        super(String.valueOf(data));
        this.data = data;
    }
}
