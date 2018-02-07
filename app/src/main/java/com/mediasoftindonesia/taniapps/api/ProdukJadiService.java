package com.mediasoftindonesia.taniapps.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by macair on 12/28/17.
 */

public interface ProdukJadiService {

    @FormUrlEncoded
    @POST("unggahjadi.php")
    retrofit2.Call<ProdukJadi> uploadImage2(
            @Field("id_produk") String id_produk,
            @Field("nama") String nama,
            @Field("harga") String harga,
            @Field("deskripsi") String deskripsi,
            @Field("tanggal") String tanggal,
            @Field("foto") String foto
    );

    /*@GET("server/produk")
    Call<JSONResponse> getJSON(
            //@Query("id_produk") String id_produk
            *//*@Query("judul") String judul,
            @Query("deskripsi") String deskripsi,
            @Query("tanggal") String tanggal,
            @Query("penulis") String penulis,
            @Query("foto") String foto*//*
    );*/

    @GET("ProdukJadi")
    Call<List<ProdukJadi>> getProdukJadi(@Query("id_produk") String id_produk);

}
