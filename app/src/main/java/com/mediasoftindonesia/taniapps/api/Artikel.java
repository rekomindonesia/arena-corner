package com.mediasoftindonesia.taniapps.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by macair on 12/12/17.
 */

public class Artikel implements Serializable{

    @SerializedName("id_artikel")
    @Expose
    private String idArtikel;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("penulis")
    @Expose
    private String penulis;
    @SerializedName("foto")
    @Expose
    private String foto;

    public String getIdArtikel() {
        return idArtikel;
    }

    public void setIdArtikel(String idArtikel) {
        this.idArtikel = idArtikel;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
