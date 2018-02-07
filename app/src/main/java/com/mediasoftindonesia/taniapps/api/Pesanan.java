package com.mediasoftindonesia.taniapps.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by macair on 12/22/17.
 */

public class Pesanan {
    @SerializedName("id_pesanan")
    @Expose
    private String idPesanan;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("nama")
    @Expose
    private String nama;

    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(String idPesanan) {
        this.idPesanan = idPesanan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
