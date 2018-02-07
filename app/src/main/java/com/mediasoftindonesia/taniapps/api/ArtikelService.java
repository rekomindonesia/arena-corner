package com.mediasoftindonesia.taniapps.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by macair on 12/12/17.
 */

public interface ArtikelService {

    @GET("Artikel")
    Call<List<Artikel>> getArtikel(
            @Query("id_artikel") String id_artikel
            /*@Query("judul") String judul,
            @Query("deskripsi") String deskripsi,
            @Query("tanggal") String tanggal,
            @Query("penulis") String penulis,
            @Query("foto") String foto*/
    );
}
