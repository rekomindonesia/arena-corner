package com.mediasoftindonesia.serviceapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("id_kategori")
    @Expose
    private Integer idKategori;
    @SerializedName("kota_id")
    @Expose
    private Integer kotaId;
    @SerializedName("kategori_name")
    @Expose
    private String kategoriName;

    public Integer getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(Integer idKategori) {
        this.idKategori = idKategori;
    }

    public Integer getKotaId() {
        return kotaId;
    }

    public void setKotaId(Integer kotaId) {
        this.kotaId = kotaId;
    }

    public String getKategoriName() {
        return kategoriName;
    }

    public void setKategoriName(String kategoriName) {
        this.kategoriName = kategoriName;
    }
}
