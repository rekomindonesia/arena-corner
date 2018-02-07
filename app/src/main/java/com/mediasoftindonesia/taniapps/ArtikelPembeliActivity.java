package com.mediasoftindonesia.taniapps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ArtikelPembeliActivity extends AppCompatActivity {

    Context context;
    //private List<Artikel>
    private String foto, judul, penulis, deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel_pembeli);

        TextView txtJudul = (TextView) findViewById(R.id.textViewJudulArtikel1);
        TextView txtPenulis = (TextView) findViewById(R.id.textViewPenulis1);
        TextView txtDeskripsi = (TextView) findViewById(R.id.textViewDeskripsiArtikel1);
        ImageView imgArtikel = (ImageView) findViewById(R.id.imageViewFotoArtikel1);
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
                Intent intent = new Intent(ArtikelPembeliActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        txtDeskripsi.setMovementMethod(new ScrollingMovementMethod());

    }
}
