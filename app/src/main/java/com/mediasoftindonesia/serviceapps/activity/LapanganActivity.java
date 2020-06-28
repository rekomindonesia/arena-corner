package com.mediasoftindonesia.serviceapps.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mediasoftindonesia.serviceapps.R;
import com.mediasoftindonesia.serviceapps.adapter.LapanganAdapter;
import com.mediasoftindonesia.serviceapps.adapter.TokoAdapter;
import com.mediasoftindonesia.serviceapps.model.Lapangan;
import com.mediasoftindonesia.serviceapps.model.PostResponseLapangan;
import com.mediasoftindonesia.serviceapps.model.Store;
import com.mediasoftindonesia.serviceapps.network.ApiService;
import com.mediasoftindonesia.serviceapps.network.RetrofitBuilder;
import com.mediasoftindonesia.serviceapps.token.TokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LapanganActivity extends AppCompatActivity {

    private static final String TAG = "LapanganActivity";
    private LapanganActivity self;

    private LapanganAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private ApiService service;
    private Call<PostResponseLapangan> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapangan);
        self = this;

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(self);
        recyclerView.setLayoutManager(layoutManager);

        service = RetrofitBuilder.createService(ApiService.class);
        getData();
    }

    private void getData() {
        Intent intent = getIntent();

        call = service.postsDataLapangan(intent.getIntExtra("kategori_id",0));
        call.enqueue(new Callback<PostResponseLapangan>() {
            @Override
            public void onResponse(Call<PostResponseLapangan> call, Response<PostResponseLapangan> response) {
                Log.w(TAG, "onResponse: " + response);

                if (response.isSuccessful()) {
                    List<Lapangan> lapanganList= response.body().getStore();
                    adapter = new LapanganAdapter(lapanganList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<PostResponseLapangan> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

}
