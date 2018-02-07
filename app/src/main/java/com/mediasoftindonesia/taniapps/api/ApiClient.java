package com.mediasoftindonesia.taniapps.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by macair on 12/11/17.
 */

public class ApiClient {

    private static final String BaseUrl = "http://tukangonline.hol.es/news/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BaseUrl).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
