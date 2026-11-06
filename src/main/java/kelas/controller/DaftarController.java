package kelas.controller;

import java.io.IOException;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kelas.Navigator;
import data.DataProduk;
import Model.produk;

public class DaftarController {

    @FXML
    private TableView<produk> tabel;
    @FXML
    private TableColumn<produk, String> kodeCol;
    @FXML
    private TableColumn<produk, String> namaCol;
    @FXML
    private TableColumn<produk, String> kategoriCol;
    @FXML
    private TableColumn<produk, Integer> hargaCol;
    @FXML
    private TableColumn<produk, Long> totalCol;
    @FXML
    private TextField cariField;
    @FXML
    private ComboBox<String> filterJenisBox;
    @FXML
    private CheckBox hargaCheck;
    @FXML
    private Label pesanLabel;

    private Navigator navigator;
    private DataProduk data;
    private final ObservableList<produk> dataTampilan
            = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // KOLOM KODE
        PropertyValueFactory<produk, String> sumberKode
                = new PropertyValueFactory<produk, String>("kode");
        kodeCol.setCellValueFactory(sumberKode);

        // KOLOM NAMA
        PropertyValueFactory<produk, String> sumberNama
                = new PropertyValueFactory<produk, String>("nama");
        namaCol.setCellValueFactory(sumberNama);

        // KOLOM KATEGORI
        PropertyValueFactory<produk, String> sumberJenis
                = new PropertyValueFactory<produk, String>("kategori");
        kategoriCol.setCellValueFactory(sumberJenis);

        // KOLOM HARGA
        PropertyValueFactory<produk, Integer> sumberHarga
                = new PropertyValueFactory<produk, Integer>("harga");
        hargaCol.setCellValueFactory(sumberHarga);
        
        filterJenisBox.getItems().add("Semua");
        filterJenisBox.getItems().add("Makanan");   
        filterJenisBox.getItems().add("Minuman");  
        filterJenisBox.setValue("Semua");

        tabel.setItems(dataTampilan);
        tabel.setPlaceholder(new Label("Belum ada data yang sesuai."));

        ubahTampilanJumlah();
    }

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
        cari();
    }

    @FXML
    private void cari() {
        if (data == null) {
            return;
        }
        dataTampilan.clear();
        String kata = cariField.getText().trim().toLowerCase(Locale.ROOT);
        String jenisDipilih = filterJenisBox.getValue();

        for (produk produkN : data.getDaftar()) {
            String kode = produkN.getKode().toLowerCase(Locale.ROOT);
            String nama = produkN.getNama().toLowerCase(Locale.ROOT);
            boolean cocokKata = false;
            boolean cocokJenis = false;

            if (kode.contains(kata) || nama.contains(kata)) {
                cocokKata = true;
            }
            if ("Semua".equals(jenisDipilih)) {
                cocokJenis = true;
            } else if (produkN.getKategori().equals(jenisDipilih)) {
                cocokJenis = true;
            }
            if (cocokKata && cocokJenis) {
                dataTampilan.add(produkN);
            }
        }
        tabel.sort();
        pesanLabel.setText("Jumlah data yang tampil: " + dataTampilan.size());
    }

    @FXML
    private void resetPencarian() {
        cariField.clear();
        filterJenisBox.setValue("Semua");
        cari();
    }

    @FXML
    private void ubahTampilanJumlah() {
        if (hargaCheck.isSelected()) {
            hargaCol.setVisible(true);
        } else {
            hargaCol.setVisible(false);
        }
    }

    @FXML
    private void tambah() {
        try {
            navigator.bukaForm();
        } catch (IOException e) {
            pesanLabel.setText("Form gagal dibuka.");
            e.printStackTrace();
        }
    }

    @FXML
    private void detail() {
        produk produk = tabel.getSelectionModel().getSelectedItem();
        if (produk == null) {
            pesanLabel.setText("Pilih satu baris dahulu.");
            return;
        }
        try {
            navigator.bukaDetail(produk);
        } catch (IOException e) {
            pesanLabel.setText("Detail gagal dibuka.");
            e.printStackTrace();
        }
    }

    @FXML
    private void hapus() {
        produk produk = tabel.getSelectionModel().getSelectedItem();
        if (produk == null) {
            pesanLabel.setText("Pilih satu baris dahulu.");
            return;
        }
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION,
                "Hapus " + produk.getKode() + "?",
                ButtonType.YES, ButtonType.NO);
        dialog.initOwner(tabel.getScene().getWindow());
        ButtonType jawaban = dialog.showAndWait().orElse(ButtonType.NO);
        if (jawaban == ButtonType.YES) {
            data.hapus(produk);
            cari();
        }
    }
}
