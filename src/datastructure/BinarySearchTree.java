package datastructure;

import model.BSTNode;
import model.Node;

/**
 * Class Binary Search Tree (BST)
 * Mengimplementasikan operasi Insert, Search, Delete, dan Traversal.
 */
public class BinarySearchTree {
    private BSTNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BSTNode getRoot() {
        return root;
    }

    // --- Insert Operation ---
    public void insert(int data) {
        root = insertRec(root, data);
    }

    private BSTNode insertRec(BSTNode root, int data) {
        if (root == null) {
            return new BSTNode(data);
        }
        if (data < root.data) {
            root.left = insertRec((BSTNode) root.left, data);
        } else if (data > root.data) {
            root.right = insertRec((BSTNode) root.right, data);
        }
        return root;
    }

    // --- Search Operation ---
    public boolean search(int data) {
        return searchRec(root, data);
    }

    private boolean searchRec(BSTNode root, int data) {
        if (root == null) {
            return false;
        }
        if (root.data == data) {
            return true;
        }
        return data < root.data
                ? searchRec((BSTNode) root.left, data)
                : searchRec((BSTNode) root.right, data);
    }

    // --- Delete Operation ---
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    private BSTNode deleteRec(BSTNode root, int data) {
        if (root == null) return root;

        if (data < root.data) {
            root.left = deleteRec((BSTNode) root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec((BSTNode) root.right, data);
        } else {
            // Node dengan 1 child atau tanpa child
            if (root.left == null) return (BSTNode) root.right;
            else if (root.right == null) return (BSTNode) root.left;

            // Node dengan 2 child: Ambil inorder successor (terkecil di subtree kanan)
            root.data = minValue((BSTNode) root.right);
            root.value = String.valueOf(root.data);

            // Hapus inorder successor
            root.right = deleteRec((BSTNode) root.right, root.data);
        }
        return root;
    }

    private int minValue(BSTNode root) {
        int minv = root.data;
        while (root.left != null) {
            minv = ((BSTNode) root.left).data;
            root = (BSTNode) root.left;
        }
        return minv;
    }

    // --- Traversals ---
    public void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
    }

    public void preorder(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    public void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value + " ");
        }
    }
}
