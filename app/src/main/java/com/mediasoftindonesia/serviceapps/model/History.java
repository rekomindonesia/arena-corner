package com.mediasoftindonesia.serviceapps.model;

import java.util.List;

public class History {

    private int id_transaksi;
    private String tgl_service;
    private String catatan_service;
    private Lapangan store;
    private Status status;

    public int getId_transaksi() {
        return id_transaksi;
    }

    public String getTgl_service() {
        return tgl_service;
    }

    public String getCatatan_service() {
        return catatan_service;
    }

    public Lapangan getStore() {
        return store;
    }

    public Status getStatus() {
        return status;
    }
}
