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
 * Created by macair on 12/8/17.
 */

public interface PetaniService {
    @GET("UserPetani")
    Call<List<Petani>> getPetani(
            @Query("nama") String nama,
            @Query("password") String password
            //@Query("no_telp") String no_telp
    );

    @POST("UserPetani")
    @FormUrlEncoded
    Call<Petani> insertPetani(
            //@Field("id") String id,
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("no_telp") String no_telp,
            @Field("alamat") String alamat
    );

    @PUT("UserPetani")
    @FormUrlEncoded
    Call<Petani> updatePetani(
            //@Body HashMap<String, String> parameters
            @Field("id_petani") String id_petani,
            //@Field("nama") String nama,
            //@Field("email") String email,
            @Field("no_telp") String no_telp,
            @Field("alamat") String alamat,
            @Field("deskripsi") String deskripsi
    );

    @DELETE
    @FormUrlEncoded
    Call<Petani> deletePetani(
            @Field("nama") String nama
    );
}
