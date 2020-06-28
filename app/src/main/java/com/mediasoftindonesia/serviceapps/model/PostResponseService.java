package com.mediasoftindonesia.serviceapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostResponseService {

    @SerializedName("id_kota")
    @Expose
    private Integer idKota;
    @SerializedName("nama_kota")
    @Expose
    private String namaKota;
    @SerializedName("img_kota")
    @Expose
    private String imgKota;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getIdKota() {
        return idKota;
    }

    public void setIdKota(Integer idKota) {
        this.idKota = idKota;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    public String getImgKota() {
        return imgKota;
    }

    public void setImgKota(String imgKota) {
        this.imgKota = imgKota;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
