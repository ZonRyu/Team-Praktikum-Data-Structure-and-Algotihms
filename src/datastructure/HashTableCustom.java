package datastructure;

import model.Mahasiswa;

/**
 * Implementasi Hash Table custom menggunakan separate chaining.
 * NIM digunakan sebagai key untuk mengidentifikasi mahasiswa.
 */
public class HashTableCustom {

    private static class Entry {
        String key;
        Mahasiswa value;
        Entry next;

        Entry(String key, Mahasiswa value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private final Entry[] table;
    private int size;

    public HashTableCustom(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Kapasitas harus lebih besar dari 0."
            );
        }

        table = new Entry[capacity];
        size = 0;
    }

    /**
     * Mengubah NIM menjadi indeks array.
     * Kompleksitas waktu: O(k), dengan k panjang NIM.
     */
    private int hash(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException(
                    "NIM tidak boleh kosong."
            );
        }

        return Math.floorMod(key.hashCode(), table.length);
    }

    /**
     * Menambahkan mahasiswa baru.
     * NIM duplikat ditolak agar data tidak tertimpa.
     * Rata-rata: O(1), worst case: O(n).
     */
    public boolean insert(Mahasiswa mahasiswa) {
        if (mahasiswa == null) {
            throw new IllegalArgumentException(
                    "Data mahasiswa tidak boleh null."
            );
        }

        String nim = mahasiswa.getNim();
        int index = hash(nim);

        Entry current = table[index];

        while (current != null) {
            if (current.key.equals(nim)) {
                return false;
            }
            current = current.next;
        }

        Entry newEntry = new Entry(nim, mahasiswa);
        newEntry.next = table[index];
        table[index] = newEntry;

        size++;
        return true;
    }

    /**
     * Mencari mahasiswa berdasarkan NIM.
     * Rata-rata: O(1), worst case: O(n).
     */
    public Mahasiswa search(String nim) {
        int index = hash(nim);
        Entry current = table[index];

        while (current != null) {
            if (current.key.equals(nim)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    /**
     * Memperbarui nama dan IPK mahasiswa.
     * Rata-rata: O(1), worst case: O(n).
     */
    public boolean update(String nim, String namaBaru, double ipkBaru) {
        if (namaBaru == null || namaBaru.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nama tidak boleh kosong."
            );
        }

        if (Double.isNaN(ipkBaru)
                || Double.isInfinite(ipkBaru)
                || ipkBaru < 0.0
                || ipkBaru > 4.0) {
            throw new IllegalArgumentException(
                    "IPK harus berada di antara 0.0 dan 4.0."
            );
        }

        Mahasiswa mahasiswa = search(nim);

        if (mahasiswa == null) {
            return false;
        }

        mahasiswa.setNama(namaBaru);
        mahasiswa.setIpk(ipkBaru);

        return true;
    }

    /**
     * Menghapus mahasiswa berdasarkan NIM.
     * Rata-rata: O(1), worst case: O(n).
     */
    public boolean delete(String nim) {
        int index = hash(nim);

        Entry current = table[index];
        Entry previous = null;

        while (current != null) {
            if (current.key.equals(nim)) {
                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    /**
     * Menampilkan seluruh data mahasiswa.
     * Kompleksitas waktu: O(m + n), dengan m kapasitas tabel
     * dan n jumlah mahasiswa.
     */
    public void display() {
        if (size == 0) {
            System.out.println("Hash Table masih kosong.");
            return;
        }

        for (int i = 0; i < table.length; i++) {
            Entry current = table[i];

            if (current != null) {
                System.out.print("Index " + i + ": ");

                while (current != null) {
                    System.out.print(current.value);

                    if (current.next != null) {
                        System.out.print(" -> ");
                    }

                    current = current.next;
                }

                System.out.println();
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
