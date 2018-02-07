package com.mediasoftindonesia.taniapps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mediasoftindonesia.taniapps.api.Produk;
import com.mediasoftindonesia.taniapps.api.ProdukJadi;
import com.mediasoftindonesia.taniapps.api.ProdukJadiService;
import com.mediasoftindonesia.taniapps.api.ProdukService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KatalogFragmentPembeli extends Fragment {

    private RecyclerView mRecyclerView, mRecyclerView2;
    private RecyclerView.LayoutManager mLayoutManager, mLayoutManager2;
    private RecyclerView.Adapter mAdapter, mAdapter2;
    private ArrayList<String> mDataset, mDataset2;
    ListView list_Artikel;
    String url = "http://tukangonline.hol.es/server/index.php/";
    private ProdukService request;
    private ProdukJadiService request2;
    private List<Produk> pro;
    private List<ProdukJadi> pro2;
    private String id_produk, id_produk2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.activity_katalog_fragment_pembeli, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mAdapter = new MainAdapter(mDataset);
        //mRecyclerView.setAdapter(mAdapter);
        loadJSON1();

        mRecyclerView2 = (RecyclerView) view.findViewById(R.id.recycler_view2);
        mRecyclerView2.setHasFixedSize(true);
        mLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView2.setLayoutManager(mLayoutManager2);
        //mAdapter = new MainAdapter(mDataset);
        //mRecyclerView.setAdapter(mAdapter);
        loadJSON2();

        return view;
    }

    //RECYCLER VIEW1
    private void loadJSON1() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(ProdukService.class);
        request.getProduk(id_produk).enqueue(new Callback<List<Produk>>() {
            @Override
            public void onResponse(Call<List<Produk>> call, Response<List<Produk>> response) {
                pro = response.body();

                String nama1 = "";
                String foto1 = "";
                String harga1 = "";
                String tanggal1 = "";
                String deskripsi1 = "";

                for (Produk p : pro){
                    nama1 = p.getNama();
                    foto1 = p.getFoto();
                    harga1 = p.getHarga();
                    tanggal1 = p.getTanggal();
                    deskripsi1 = p.getDeskripsi();
                }

                final MainAdapter2 mAdapter = new MainAdapter2(getContext(),pro);
                mRecyclerView.setAdapter(mAdapter);
                registerForContextMenu(mRecyclerView);
            }

            @Override
            public void onFailure(Call<List<Produk>> call, Throwable throwable) {
                Log.d("Error",throwable.getMessage());
            }
        });
    }

    //RECYCLER VIEW2
    private void loadJSON2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request2 = retrofit.create(ProdukJadiService.class);
        request2.getProdukJadi(id_produk).enqueue(new Callback<List<ProdukJadi>>() {
            @Override
            public void onResponse(Call<List<ProdukJadi>> call, Response<List<ProdukJadi>> response) {
                pro2 = response.body();

                String nama1 = "";
                String foto1 = "";
                String harga1 = "";
                String tanggal1 = "";
                String deskripsi1 = "";

                for (ProdukJadi pj : pro2){
                    nama1 = pj.getNama();
                    foto1 = pj.getFoto();
                    harga1 = pj.getHarga();
                    tanggal1 = pj.getTanggal();
                    deskripsi1 = pj.getDeskripsi();
                }

                final MainAdapter3 mAdapter = new MainAdapter3(getContext(),pro2);
                mRecyclerView2.setAdapter(mAdapter);
                registerForContextMenu(mRecyclerView2);
            }

            @Override
            public void onFailure(Call<List<ProdukJadi>> call, Throwable throwable) {
                Log.d("Error",throwable.getMessage());
            }
        });
    }
}
