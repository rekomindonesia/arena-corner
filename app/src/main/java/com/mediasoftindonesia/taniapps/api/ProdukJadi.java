package com.mediasoftindonesia.taniapps.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by macair on 12/28/17.
 */

public class ProdukJadi {

    @SerializedName("id_produk")
    @Expose
    private String id_produk;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("harga")
    @Expose
    private String harga;

    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("response")
    @Expose
    private String response;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    @SerializedName("status")
    @Expose
    private String status;

    public String getId_produk() {
        return id_produk;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setResponse(String response) {
        response = response;
    }

    public String getResponse() {
        return response;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
