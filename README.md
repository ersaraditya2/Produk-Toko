# Toko Produk - Aplikasi Manajemen & Kasir Produk

Aplikasi manajemen produk dan inventaris berbasis *desktop* menggunakan **JavaFX**.

## Deskripsi

**Toko Produk** adalah aplikasi sistem manajemen produk sederhana yang dirancang dengan menerapkan arsitektur MVC (*Model-View-Controller*). Aplikasi ini memudahkan pengguna untuk mencatat data produk baru (lengkap dengan kode otomatis, nama, kategori, harga, dan lampiran gambar), mengelola daftar produk dalam tabel interaktif dengan fitur pencarian serta filter, dan melihat halaman rincian detail produk secara dinamis menggunakan sistem navigasi terpusat.

## Tampilan Aplikasi
Berikut adalah contoh dari tampilan simulasi dari produk toko:

### 1. Tampilan Utama (Input Data)
<p align="center">
</p>

## Fitur Utama

- **Manajemen Data Produk**: Menambahkan, melihat daftar, dan menghapus data produk secara *real-time*.
- **Generate Kode Otomatis**: Sistem otomatis menghasilkan kode unik (seperti `TR001`, `TR002`, dst.) untuk setiap produk baru.
- **Pencarian & Filter Kategori**: Mencari produk berdasarkan kode/nama serta memfilter berdasarkan kategori produk.
- **Lampiran Gambar**: Mendukung pemilihan file gambar lokal (PNG/JPEG) sebagai lampiran pada produk dengan validasi ukuran.
- **Navigasi Terpusat**: Perpindahan halaman yang mulus menggunakan *Navigator* dengan pola *dependency injection*.

## Teknologi

- **Java**
- **JavaFX**
- **FXML**
- **Maven**
- **IDE**: NetBeans / IntelliJ IDEA

## Struktur Proyek

```text
TokoProduk/
├── pom.xml
├── README.md
├── .gitignore
├── SS/
│   └── (Folder tangkapan layar / aset gambar)
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── kelas/
    │   │   │   ├── Navigator.java
    │   │   │   └── controller/
    │   │   │       ├── DaftarController.java
    │   │   │       ├── DetailController.java
    │   │   │       └── FormController.java
    │   │   ├── data/
    │   │   │   └── DataProduk.java
    │   │   └── Model/
    │   │       └── produk.java
    │   └── resources/
    │       └── kelas/
    │           ├── daftar.fxml
    │           ├── detail.fxml
    │           ├── form.fxml
    │           └── styles/
    │               └── app.css
    └── test/
        └── java/
