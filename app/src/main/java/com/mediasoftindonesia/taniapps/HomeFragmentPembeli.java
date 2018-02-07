package com.mediasoftindonesia.taniapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mediasoftindonesia.taniapps.api.Artikel;
import com.mediasoftindonesia.taniapps.api.ArtikelService;
import com.mediasoftindonesia.taniapps.api.Produk;
import com.mediasoftindonesia.taniapps.api.ProdukService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragmentPembeli extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    ListView list_Artikel;

    private List<Artikel> verif;
    private List<Produk> pro;
    private ArtikelService ser;
    private ProdukService request;
    String url = "http://tukangonline.hol.es/server/index.php/";
    //String url2 = "http://tukangonline.hol.es/server/produk/";
    //String url2 = "http://tukangonline.hol.es/news/upload/";
    Context context;
    private SharedPreferences sp;
    private String id_artikel, id_produk;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.activity_home_fragment_pembeli, container, false);

        TextView mSelengkapnya = (TextView) view.findViewById(R.id.textViewSelengkapnya);
        mSelengkapnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                KatalogFragmentPembeli llf = new KatalogFragmentPembeli();
                ft.replace(R.id.homeFragmentPem, llf);
                ft.commit();
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadJSON1();
        //loadJSON2();

        //GET DATA
        sp=getActivity().getSharedPreferences("artikel", MODE_PRIVATE);
        final String id=sp.getString("id_artikel",null);
        list_Artikel = (ListView) view.findViewById(R.id.listArtikel);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ser = retrofit.create(ArtikelService.class);
        ser.getArtikel(id_artikel).enqueue(new Callback<List<Artikel>>() {
            @Override
            public void onResponse(Call<List<Artikel>> call, Response<List<Artikel>> response) {
                final List<Artikel> data = response.body();
                verif = response.body();

                String judul1 = "";
                String tgl1 = "";
                String deskripsi1 = "";
                String foto1 = "";

                for (Artikel m : verif){
                    judul1 = m.getJudul();
                    tgl1 = m.getTanggal();
                    deskripsi1 = m.getDeskripsi();
                    foto1 = m.getFoto();
                }

                final CustomListViewBeranda adapter= new CustomListViewBeranda(getContext(),data);
                list_Artikel.setAdapter(adapter);
                registerForContextMenu(list_Artikel);
            }

            @Override
            public void onFailure(Call<List<Artikel>> call, Throwable throwable) {

            }
        });

        context = view.getContext();

        return view;
    }

    //RECYCLER VIEW
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
                final MainAdapter mAdapter = new MainAdapter(getContext(),pro);
                mRecyclerView.setAdapter(mAdapter);
                registerForContextMenu(mRecyclerView);
            }

            @Override
            public void onFailure(Call<List<Produk>> call, Throwable throwable) {
                Log.d("Error",throwable.getMessage());
            }
        });
    }

    /*private void loadJSON2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(ProdukService.class);
        request.getProduk(id_produk).enqueue(new Callback<List<Produk>>() {
            @Override
            public void onResponse(Call<List<Produk>> call, Response<List<Produk>> response) {
                pro = response.body();

                String foto1 = "";

                for (Produk p : pro){
                    foto1 = p.getFoto();
                }
                final MainAdapter mAdapter = new MainAdapter(getContext(),pro);
                mRecyclerView.setAdapter(mAdapter);
                registerForContextMenu(mRecyclerView);
            }

            @Override
            public void onFailure(Call<List<Produk>> call, Throwable throwable) {
                Log.d("Error",throwable.getMessage());
            }
        });
    }*/


}
