package com.mediasoftindonesia.taniapps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediasoftindonesia.taniapps.api.Artikel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtikelActivity extends AppCompatActivity {

    private String judul, penulis, deskripsi, foto, id_artikel;
    private Context context;
    private List<Artikel> artikel;
    private Artikel kumpArtikel;
    String url = "http://tukangonline.hol.es/server/index.php/";
    private TextView mJudul, mPenulis, mDeskripsi;
    private ImageView mFoto, mKeluar;
    //private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        TextView txtJudul = (TextView) findViewById(R.id.textViewJudulArtikel);
        TextView txtPenulis = (TextView) findViewById(R.id.textViewPenulis);
        TextView txtDeskripsi = (TextView) findViewById(R.id.textViewDeskripsiArtikel);
        ImageView imgArtikel = (ImageView) findViewById(R.id.imageViewFotoArtikel);
        ImageView imgSilang = (ImageView) findViewById(R.id.imageViewSilang1);


        foto = getIntent().getStringExtra("foto");
        judul = getIntent().getStringExtra("judul");
        penulis = getIntent().getStringExtra("penulis");
        deskripsi = getIntent().getStringExtra("deskripsi");

        txtJudul.setText(judul);
        txtPenulis.setText(penulis);
        txtDeskripsi.setText(deskripsi);
        Picasso.with(context).load(foto)
                .resize(170, 150)
                .noFade()
                .into(imgArtikel);

        imgSilang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArtikelActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        txtDeskripsi.setMovementMethod(new ScrollingMovementMethod());

    }
}
