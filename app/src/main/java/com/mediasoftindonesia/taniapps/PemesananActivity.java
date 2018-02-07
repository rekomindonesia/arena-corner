package com.mediasoftindonesia.taniapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mediasoftindonesia.taniapps.api.Petani;
import com.mediasoftindonesia.taniapps.api.PetaniService;
import com.mediasoftindonesia.taniapps.api.Produk;
import com.mediasoftindonesia.taniapps.api.ProdukService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PemesananActivity extends AppCompatActivity {

    private Button mPesan;
    private String id_produk, nama, harga, tanggal, foto, nama1, password1, no_telp1, deskripsi, banyak, h, a;
    private PetaniService petaniService;
    private String url = "http://tukangonline.hol.es/server/index.php/";
    private List<Petani> verif;
    private TextView mPetani,mAlamat, totalbayar;
    private List<Produk> list;
    private ProdukService produkService;
    private int c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        final TextView mNama = (TextView) findViewById(R.id.textView19);
        final TextView mHarga = (TextView) findViewById(R.id.textView26);
        TextView mTanggal = (TextView) findViewById(R.id.textView27);
        mPetani = (TextView) findViewById(R.id.textView17);
        mAlamat = (TextView) findViewById(R.id.textView18);
        TextView mDeskripsi = (TextView) findViewById(R.id.textView24);
        totalbayar = (TextView) findViewById(R.id.textView32);

        ImageView mSilang = (ImageView) findViewById(R.id.imageViewSilang);
        mSilang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(PemesananActivity.this, Main2Activity.class);
                startActivity(in);
            }
        });

     /*   Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        petaniService = retrofit.create(PetaniService.class);
        petaniService.getPetani(nama1, password1, no_telp1).enqueue(new Callback<List<Petani>>() {
            @Override
            public void onResponse(Call<List<Petani>> call, Response<List<Petani>> response) {
                verif = response.body();

                for (Petani p : verif){
                    nama1 = p.getNama();
                    no_telp1 = p.getNo_telp();
                }

                mPetani.setText(nama1);
                mAlamat.setText(no_telp1);
            }

            @Override
            public void onFailure(Call<List<Petani>> call, Throwable throwable) {

            }
        });*/

        nama = getIntent().getStringExtra("nama");
        harga = getIntent().getStringExtra("harga");
        tanggal = getIntent().getStringExtra("tanggal");
        deskripsi = getIntent().getStringExtra("deskripsi");

        mNama.setText(nama);
        mHarga.setText(harga);
        mTanggal.setText(tanggal);
        mDeskripsi.setText(deskripsi);

        final Button tambah = (Button) findViewById(R.id.btnTambah);
        final Button kurang = (Button) findViewById(R.id.btnKurang);
        final TextView jumlah = (TextView) findViewById(R.id.textView29);

        kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jumlah.getText().toString().equals("0")){
                    Toast.makeText(getApplicationContext(),"Anda belum memasukan jumlah pesanan",Toast.LENGTH_LONG).show();
                }
                else {

                    int l=Integer.parseInt( jumlah.getText().toString());
                    l=l-1;
                    String k=Integer.toString(l);
                    jumlah.setText(k);
                }
            }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int a = Integer.parseInt(jumlah.getText().toString());
                if (a < 10) {
                    if (jumlah.getText().toString().equals("0")) {
                        jumlah.setText("1");
                    } else {
                        String n = jumlah.getText().toString();
                        int m = Integer.parseInt(n);
                        m = m + 1;
                        String o = Integer.toString(m);
                        jumlah.setText(o);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "maksimal pemesan untuk 1 menu adalah 10", Toast.LENGTH_LONG).show();
                }
            }
        });

        mPesan = (Button) findViewById(R.id.buttonPesan);
        mPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(PemesananActivity.this, MapsActivity.class);

                banyak = jumlah.getText().toString();
                int n = Integer.parseInt(banyak);
                int u = Integer.parseInt(harga);
                final int[] hasil = {u * n};
                h = Integer.toString(hasil[0]);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                produkService = retrofit.create(ProdukService.class);
                produkService.uploadImage(id_produk, nama, banyak, h, deskripsi,foto).enqueue(new Callback<Produk>() {
                    @Override
                    public void onResponse(Call<Produk> call, Response<Produk> response) {

                    }

                    @Override
                    public void onFailure(Call<Produk> call, Throwable throwable) {

                    }
                });

                /*produkService.getProduk(id_petani).enqueue(new Callback<List<Produk>>() {
                    @Override
                    public void onResponse(Call<List<Produk>> call, Response<List<Produk>> response) {
                        list = response.body();

                        String harga[] = new String[list.size()];

                        int i = 0;

                        for (Produk p : list){
                            harga[i] = p.getHarga();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Produk>> call, Throwable throwable) {

                    }
                });*/

                produkService.getProduk(id_produk).enqueue(new Callback<List<Produk>>() {
                    @Override
                    public void onResponse(Call<List<Produk>> call, Response<List<Produk>> response) {
                        list = response.body();
                        String uang[] = new String[list.size()];
                        int hasil = 0;
                        int i = 0;
                        for (Produk p : list){
                            uang[i] = p.getHarga();

                            int u = Integer.parseInt(uang[i]);
                            i++;
                            int t=u;
                            hasil+=t;
                        }

                        a = Integer.toString(hasil);
                        totalbayar.setText(a);
                        final String cob = totalbayar.getText().toString();
                        c = Integer.parseInt(cob);
                        Intent in = new Intent(PemesananActivity.this, MapsActivity.class);
                        in.putExtra("harga", cob);
                        Bundle b = new Bundle();
                        in.putExtras(b);
                        startActivity(in);

                    }

                    @Override
                    public void onFailure(Call<List<Produk>> call, Throwable throwable) {

                    }
                });

                startActivity(intent);
                Toast.makeText(PemesananActivity.this, "Pesanan Anda berhasil ditambahkan", Toast.LENGTH_LONG).show();
            }
        });
    }
}
