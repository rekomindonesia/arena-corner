package com.mediasoftindonesia.taniapps.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by macair on 12/12/17.
 */

public interface PembeliService {
    @GET("UserPembeli")
    Call<List<Pembeli>> getPembeli(@Query("nama") String nama, @Query("password") String password);


    @POST("UserPembeli")
    @FormUrlEncoded
    Call<Pembeli> insertPembeli(
            //@Field("id") String id,
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("no_telp") String no_telp,
            @Field("alamat") String alamat
    );

    @PUT
    @FormUrlEncoded
    Call<Pembeli> updatePembeli(
            @Field("id_petani") String id_petani,
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("no_telp") String no_telp,
            @Field("alamat") String alamat
    );

    @DELETE
    @FormUrlEncoded
    Call<Pembeli> deletePembeli(
            @Field("nama") String nama
    );
}
