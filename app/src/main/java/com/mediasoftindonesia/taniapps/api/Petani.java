package com.mediasoftindonesia.taniapps.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by macair on 12/8/17.
 */

public class Petani implements Serializable {

    @SerializedName("id_petani")
    @Expose
    private String id_petani;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("no_telp")
    @Expose
    private String no_telp;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;

    public String getId_petani() {
        return id_petani;
    }

    public void setId_petani(String id_petani) {
        this.id_petani = id_petani;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
