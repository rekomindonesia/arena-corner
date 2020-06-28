package com.mediasoftindonesia.serviceapps.network;

import com.mediasoftindonesia.serviceapps.model.AccessToken;
import com.mediasoftindonesia.serviceapps.model.Booking;
import com.mediasoftindonesia.serviceapps.model.InfoUser;
import com.mediasoftindonesia.serviceapps.model.PostResponseHistory;
import com.mediasoftindonesia.serviceapps.model.PostResponseLapangan;
import com.mediasoftindonesia.serviceapps.model.PostResponseService;
import com.mediasoftindonesia.serviceapps.model.PostResponseStore;
import com.mediasoftindonesia.serviceapps.model.Store;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("register")
    @FormUrlEncoded
    Call<InfoUser> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("no_telp") String no_telp,
            @Field("alamat") String alamat
    );

    @POST("login")
    @FormUrlEncoded
    Call<AccessToken> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken);

    @GET("get_kota")
    Call<List<PostResponseService>> postsDataService();

    @GET("getKategoriByKota")
    Call<List<Store>> postsDataStore(
            @Query("kota_id") int kota_id
    );

    @GET("getStoreByKategori")
    Call<PostResponseLapangan> postsDataLapangan(
            @Query("kategori_id") int kategori_id
    );

    @POST("transaksi")
    @FormUrlEncoded
    Call<Booking> booking(
            @Field("id_store") int id_store,
            @Field("waktu_service") String waktu_service,
            @Field("tgl_service") String tgl_service,
            @Field("catatan_service") String catatan_service,
            @Field("id_status") int id_status
    );

    @GET("getDataByTransaksi")
    Call<PostResponseHistory> postsDataHistory();

}
