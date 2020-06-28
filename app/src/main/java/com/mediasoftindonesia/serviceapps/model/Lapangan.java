package com.mediasoftindonesia.serviceapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lapangan {

    @SerializedName("id_store")
    @Expose
    private Integer idStore;
    @SerializedName("kategori_id")
    @Expose
    private Integer kategoriId;
    @SerializedName("name_store")
    @Expose
    private String nameStore;
    @SerializedName("no_telp")
    @Expose
    private String noTelp;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("harga")
    @Expose
    private int harga;
    @SerializedName("img_store")
    @Expose
    private String imgStore;

    public Integer getIdStore() {
        return idStore;
    }

    public void setIdStore(Integer idStore) {
        this.idStore = idStore;
    }

    public Integer getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(Integer kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getImgStore() {
        return imgStore;
    }

    public void setImgStore(String imgStore) {
        this.imgStore = imgStore;
    }
}
