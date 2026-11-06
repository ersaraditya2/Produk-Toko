package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.produk;

public class DataProduk {

    private final ObservableList<produk> daftar = FXCollections.observableArrayList();
    private int urutan = 1;

    public ObservableList<produk> getDaftar() {
        return FXCollections.unmodifiableObservableList(daftar);
    }

    public produk tambah(String nama, String kategori, int harga, String gambarUri) {
        // Membuat kode otomatis misal TR001, TR002, dst.
        String kode = String.format("TR%03d", urutan);
        
        produk p = new produk(kode, nama, kategori, harga, gambarUri);
        
        daftar.add(p);
        urutan++;
        return p;
    }

    public void hapus(produk p) {
        daftar.remove(p);
    }
}