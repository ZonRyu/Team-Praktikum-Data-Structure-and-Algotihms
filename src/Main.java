import datastructure.BinarySearchTree;
import datastructure.ExpressionTree;

class main {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("    DEMO BINARY SEARCH TREE (BST)    ");
        System.out.println("=========================================");
        BinarySearchTree bst = new BinarySearchTree();

        // 1. Insert Data
        int[] numbers = {50, 30, 20, 40, 70, 60, 80};
        System.out.print("Memasukkan data ke BST: ");
        for (int num : numbers) {
            System.out.print(num + " ");
            bst.insert(num);
        }
        System.out.println("\n");

        // 2. Traversal
        System.out.print("Inorder Traversal   : ");
        bst.inorder(bst.getRoot());
        System.out.println();

        System.out.print("Preorder Traversal  : ");
        bst.preorder(bst.getRoot());
        System.out.println();

        System.out.print("Postorder Traversal : ");
        bst.postorder(bst.getRoot());
        System.out.println("\n");

        // 3. Search
        int target = 40;
        System.out.println("Pencarian nilai " + target + " : " + (bst.search(target) ? "Ditemukan" : "Tidak ditemukan"));
        target = 90;
        System.out.println("Pencarian nilai " + target + " : " + (bst.search(target) ? "Ditemukan" : "Tidak ditemukan"));
        System.out.println();

        // 4. Delete
        System.out.println("Menghapus nilai 20 (Node Leaf)...");
        bst.delete(20);
        System.out.print("Inorder setelah hapus 20 : ");
        bst.inorder(bst.getRoot());
        System.out.println();

        System.out.println("Menghapus nilai 30 (Node dengan 1 atau 2 child)...");
        bst.delete(30);
        System.out.print("Inorder setelah hapus 30 : ");
        bst.inorder(bst.getRoot());
        System.out.println("\n");


        System.out.println("=========================================");
        System.out.println("       DEMO EXPRESSION TREE          ");
        System.out.println("=========================================");
        ExpressionTree expTree = new ExpressionTree();

        String expression = "(3 + 5) * 2";
        System.out.println("Input Ekspresi Matematika : " + expression);

        // Build Tree
        expTree.buildTreeFromInfix(expression);

        // Traversal
        System.out.print("Inorder Expression Tree    : ");
        expTree.inorder(expTree.getRoot());
        System.out.println();

        // Evaluate
        double result = expTree.evaluate(expTree.getRoot());
        System.out.println("Hasil Evaluasi             : " + result);
        System.out.println("=========================================");
    }
}