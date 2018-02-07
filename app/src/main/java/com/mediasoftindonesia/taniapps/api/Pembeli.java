package com.mediasoftindonesia.taniapps.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by macair on 12/12/17.
 */

public class Pembeli implements Serializable{
    @SerializedName("id_pembeli")
    @Expose
    private String id_pembeli;
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

    public String getId_pembeli() {
        return id_pembeli;
    }

    public void setId_pembeli(String id_pembeli) {
        this.id_pembeli = id_pembeli;
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
}
