package com.mediasoftindonesia.taniapps.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by macair on 12/22/17.
 */

public interface PesananService {
    @GET("Pesanan")
    Call<List<Pesanan>> getPesanan(
            @Query("id_pesanan") String id_pesanan
    );

    @POST("Pesanan")
    @FormUrlEncoded
    Call<Pesanan> insertPesanan(
            //@Field("id") String id,
            @Field("lokasi") String lokasi,
            @Field("harga") String harga,
            @Field("nama") String nama
    );
}
