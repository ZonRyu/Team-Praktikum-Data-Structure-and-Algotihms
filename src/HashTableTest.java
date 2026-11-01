import datastructure.HashTableCustom;
import model.Mahasiswa;

public class HashTableTest {

    public static void main(String[] args) {

        HashTableCustom hashTable = new HashTableCustom(10);

        // Dataset 20 mahasiswa
        Mahasiswa[] data = {
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
            new Mahasiswa("23019", "Taufik", 3.78),
            new Mahasiswa("23020", "Vina", 3.86)
        };

        System.out.println("=== HASH TABLE TEST ===");

        // 1. Insert
        System.out.println("\n1. INSERT DATA");

        for (Mahasiswa mahasiswa : data) {
            boolean berhasil = hashTable.insert(mahasiswa);

            System.out.println(
                mahasiswa.getNim() + " : "
                + (berhasil ? "Berhasil" : "Gagal")
            );
        }

        System.out.println("\nTotal mahasiswa: " + hashTable.size());

        // 2. Search
        System.out.println("\n2. SEARCH DATA");

        Mahasiswa hasil = hashTable.search("23005");

        if (hasil != null) {
            System.out.println("Ditemukan: " + hasil);
        } else {
            System.out.println("Mahasiswa tidak ditemukan.");
        }

        // 3. Update
        System.out.println("\n3. UPDATE DATA");

        boolean update = hashTable.update(
            "23005", "Eka Pratama", 3.98
        );

        System.out.println(
            update ? "Update berhasil." : "Data tidak ditemukan."
        );

        // 4. Delete
        System.out.println("\n4. DELETE DATA");

        boolean delete = hashTable.delete("23010");

        System.out.println(
            delete ? "Delete berhasil." : "Data tidak ditemukan."
        );

        // 5. Search setelah delete
        System.out.println("\n5. SEARCH SETELAH DELETE");

        hasil = hashTable.search("23010");

        System.out.println(
            hasil == null
            ? "Data sudah tidak ditemukan."
            : hasil.toString()
        );

        // 6. Display
        System.out.println("\n6. DISPLAY HASH TABLE");

        hashTable.display();

        System.out.println("\nTotal data akhir: " + hashTable.size());

        System.out.println("\n=== TEST SELESAI ===");
    }
}
