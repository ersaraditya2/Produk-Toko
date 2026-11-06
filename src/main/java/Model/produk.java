/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Touch U
 */
public class produk {
    private String kode;
    private String nama;
    private String kategori;
    private int harga;
    private String gambarUri;

    public produk(String kode, String nama, String kategori, int harga, String gambarUri) {
        if (nama == null && harga < 0) {
            throw new IllegalArgumentException("Nama tidak boleh kosong atau harga tidak boleh kurang dari nol!!!");
        }
        this.kode = kode;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.gambarUri = gambarUri;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    public int getHarga() {
        return harga;
    }

    public String getGambarUri() {
        return gambarUri;
    }
    
    
}
