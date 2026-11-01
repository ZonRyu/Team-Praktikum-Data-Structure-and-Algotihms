import datastructure.BinarySearchTree;
import datastructure.ExpressionTree;
import datastructure.HashTableCustom;
import model.Mahasiswa;

public class Benchmark {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        HashTableCustom hashTable = new HashTableCustom(10);

        // Dataset 20 mahasiswa (untuk Hash Table)
        Mahasiswa[] dataMahasiswa = {
            new Mahasiswa("23001", "Andi", 3.75),
            new Mahasiswa("23002", "Budi", 3.50),
            new Mahasiswa("23003", "Citra", 3.85),
            new Mahasiswa("23004", "Dedi", 3.40),
            new Mahasiswa("23005", "Eka", 3.90),
            new Mahasiswa("23006", "Fajar", 3.65),
            new Mahasiswa("23007", "Gita", 3.80),
            new Mahasiswa("23008", "Hadi", 3.45),
            new Mahasiswa("23009", "Intan", 3.95),
            new Mahasiswa("23010", "Joko", 3.60),
            new Mahasiswa("23011", "Karin", 3.70),
            new Mahasiswa("23012", "Lukman", 3.55),
            new Mahasiswa("23013", "Maya", 3.88),
            new Mahasiswa("23014", "Nanda", 3.72),
            new Mahasiswa("23015", "Oscar", 3.48),
            new Mahasiswa("23016", "Putri", 3.92),
            new Mahasiswa("23017", "Rizky", 3.67),
            new Mahasiswa("23018", "Sinta", 3.83),
            new Mahasiswa("23019", "Tono", 3.78),
            new Mahasiswa("23020", "Vina", 3.86)
        };



        System.out.println("\n=========================================================================================");
        System.out.println("                                     BENCHMARK                                           ");
        System.out.println("=========================================================================================");
        
        // -------------------------------------------------------------------------------------
        // BENCHMARK INSERT (20 DATA)
        // -------------------------------------------------------------------------------------
        // BST
        int[] numbers = {55, 30, 80, 15, 42, 68, 95, 8, 22, 35, 48, 62, 75, 88, 99, 3, 18, 28, 40, 72};
        System.out.println("\nINSERT (BINARY SEARCH TREE)");
        
        long startInsertNs = System.nanoTime();
        for (int num : numbers) {
            bst.insert(num);
        }
        long endInsertNs = System.nanoTime();
        double totalInsertTimeMs = (endInsertNs - startInsertNs) / 1_000_000.0;

        System.out.printf("Total Waktu Insert (%d data) : %.6f ms\n", numbers.length, totalInsertTimeMs);
        System.out.printf("Rata-rata Waktu Insert/item  : %.6f ms\n", totalInsertTimeMs / numbers.length);

        // Hash Table
        System.out.println("\nINSERT (HASH TABLE)");
        long startInsertHTNs = System.nanoTime();
        for (Mahasiswa mhs : dataMahasiswa) {
            hashTable.insert(mhs);
        }
        long endInsertHTNs = System.nanoTime();
        double totalInsertHTMs = (endInsertHTNs - startInsertHTNs) / 1_000_000.0;

        System.out.printf("Total Waktu Insert (%d data) : %.6f ms\n", dataMahasiswa.length, totalInsertHTMs);
        System.out.printf("Rata-rata Waktu Insert/item  : %.6f ms\n", totalInsertHTMs / dataMahasiswa.length);
        System.out.println("=========================================================================================");

        // -------------------------------------------------------------------------------------
        // BENCHMARK SEARCH (10 NILAI: KOMBINASI ADA & TIDAK ADA)
        // -------------------------------------------------------------------------------------
        // BST
        int[] searchTargets = {15, 42, 95, 8, 72, 99, 30, 55, 100, 53};
        System.out.println("\nBENCHMARK SEARCH PADA BST (" + searchTargets.length + " NILAI)");
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-12s | %-15s | %-15s\n", "Target", "Status", "Durasi (ms)");
        System.out.println("---------------------------------------------------------");

        double totalSearchTimeMs = 0;
        for (int target : searchTargets) {
            long startNs = System.nanoTime();
            boolean found = bst.search(target);
            long endNs = System.nanoTime();

            double durationMs = (endNs - startNs) / 1_000_000.0;
            totalSearchTimeMs += durationMs;

            System.out.printf("Search (%-3d) | %-15s | %10.6f ms\n",
                target, (found ? "Ditemukan" : "Tidak ditemukan"), durationMs);
        }
        System.out.println("---------------------------------------------------------");
        System.out.printf("Total Waktu Search (%d data) : %.6f ms\n", searchTargets.length, totalSearchTimeMs);
        System.out.printf("Rata-rata Waktu Search/item  : %.6f ms\n", totalSearchTimeMs / searchTargets.length);

        // Hash Table
        String[] searchNIMs = {"23005", "23001", "23009", "23012", "23017", "23020", "23010", "23025", "23099", "99999"};
        System.out.println("\nBENCHMARK SEARCH PADA HASH TABLE (" + searchNIMs.length + " NIM)");
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-12s | %-15s | %-15s\n", "Target", "Status", "Durasi (ms)");
        System.out.println("---------------------------------------------------------");

        double totalSearchHTMs = 0;
        for (String nim : searchNIMs) {
            long startNs = System.nanoTime();
            Mahasiswa hasil = hashTable.search(nim);
            long endNs = System.nanoTime();

            double durationMs = (endNs - startNs) / 1_000_000.0;
            totalSearchHTMs += durationMs;

            System.out.printf("Search (%-5s) | %-15s | %10.6f ms\n",
                nim, (hasil != null ? "Ditemukan" : "Tidak ditemukan"), durationMs);
        }
        System.out.println("---------------------------------------------------------");
        System.out.printf("Total Waktu Search (%d NIM)  : %.6f ms\n", searchNIMs.length, totalSearchHTMs);
        System.out.printf("Rata-rata Waktu Search/item  : %.6f ms\n", totalSearchHTMs / searchNIMs.length);
        System.out.println("=========================================================================================");

        // -------------------------------------------------------------------------------------
        // BENCHMARK DELETE (10 NILAI)
        // -------------------------------------------------------------------------------------
        // BST
        int[] deleteTargets = {3, 18, 28, 8, 48, 72, 99, 15, 30, 55};
        System.out.println("\nBENCHMARK DELETE PADA BST (" + deleteTargets.length + " NILAI)");

        double totalDeleteTimeMs = 0;
        for (int target : deleteTargets) {
            long startNs = System.nanoTime();
            bst.delete(target);
            long endNs = System.nanoTime();

            double durationMs = (endNs - startNs) / 1_000_000.0;
            totalDeleteTimeMs += durationMs;
        }
        System.out.printf("Total Waktu Delete (%d nilai) : %.6f ms\n", deleteTargets.length, totalDeleteTimeMs);
        System.out.printf("Rata-rata Waktu Delete/item  : %.6f ms\n", totalDeleteTimeMs / deleteTargets.length);

        // Hash Table
        String[] deleteNIMs = {"23002", "23004", "23008", "23010", "23015", "23019", "23001", "23020", "23017", "23014"};
        System.out.println("\nBENCHMARK DELETE PADA HASH TABLE (" + deleteNIMs.length + " NIM)");

        double totalDeleteHTMs = 0;
        for (String nim : deleteNIMs) {
            long startNs = System.nanoTime();
            hashTable.delete(nim);
            long endNs = System.nanoTime();

            double durationMs = (endNs - startNs) / 1_000_000.0;
            totalDeleteHTMs += durationMs;
        }

        System.out.printf("Total Waktu Delete (%d NIM)  : %.6f ms\n", deleteNIMs.length, totalDeleteHTMs);
        System.out.printf("Rata-rata Waktu Delete/item  : %.6f ms\n", totalDeleteHTMs / deleteNIMs.length);

        // -------------------------------------------------------------------------------------
        // BENCHMARK EXPRESSION TREE (SEPERTI DI MAIN.JAVA)
        // -------------------------------------------------------------------------------------
        System.out.println("\n=========================================================================================");
        System.out.println("                     BENCHMARK EXPRESSION TREE                                           ");
        System.out.println("=========================================================================================");
        ExpressionTree expTree = new ExpressionTree();
        String expression = "(3 + 5) * 2";
        System.out.println("Input Ekspresi Matematika : " + expression);

        // Build Tree
        long startBuild = System.nanoTime();
        expTree.buildTreeFromInfix(expression);
        long endBuild = System.nanoTime();
        System.out.printf("Build Tree          | Durasi: %10.6f ms\n", 
                (endBuild - startBuild) / 1_000_000.0);

        // Traversal
        System.out.print("Inorder Exp Tree    : ");
        long startExpInorder = System.nanoTime();
        expTree.inorder(expTree.getRoot());
        long endExpInorder = System.nanoTime();
        System.out.printf("\nInorder Exp Tree    | Durasi: %.6f ms\n", 
                (endExpInorder - startExpInorder) / 1_000_000.0);

        // Evaluate
        long startEval = System.nanoTime();
        double result = expTree.evaluate(expTree.getRoot());
        long endEval = System.nanoTime();
        System.out.printf("Evaluasi Tree       | Durasi: %.6f ms\n",
                (endEval - startEval) / 1_000_000.0);

        System.out.printf("Hasil               : %.2f\n", result);
        System.out.println("=========================================================================================");
    }
}
