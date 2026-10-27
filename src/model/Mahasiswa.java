package model;

/**
 * Menyimpan informasi mahasiswa berupa NIM, nama, dan IPK.
 */
public class Mahasiswa {
    private String nim;
    private String nama;
    private double ipk;

    public Mahasiswa(String nim, String nama, double ipk) {
        this.nim = nim;
        this.nama = nama;
        this.ipk = ipk;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public double getIpk() {
        return ipk;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setIpk(double ipk) {
        this.ipk = ipk;
    }

    @Override
    public String toString() {
        return "NIM: " + nim
                + ", Nama: " + nama
                + ", IPK: " + String.format("%.2f", ipk);
    }
}
```

