package com.mediasoftindonesia.taniapps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediasoftindonesia.taniapps.api.Artikel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomListViewBeranda extends BaseAdapter {

    List<Artikel> fData;
    Context context;
    LayoutInflater layoutInflater;

    //String url = "http://tukangonline.hol.es/server/artikel/";

    public CustomListViewBeranda(Context context, List<Artikel> fData) {
        this.fData = fData;
        this.context = context;
        layoutInflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return fData.size();
    }

    @Override
    public Object getItem(int i) {
        return this.fData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.activity_custom_list_view_beranda, viewGroup,false);

        TextView txtTglUpload = (TextView) view.findViewById(R.id.tglUpload);
        TextView txtJudul = (TextView) view.findViewById(R.id.textViewJudul);
        TextView txtDeskripsi = (TextView) view.findViewById(R.id.textViewDeskripsi);
        ImageView img = (ImageView) view.findViewById(R.id.gambarProduk);
        ImageView bgCopy = (ImageView) view.findViewById(R.id.imageView10);

        final String judul1 = this.fData.get(i).getJudul();
        final String tanggal1 = this.fData.get(i).getTanggal();
        final String deskripsi1 = this.fData.get(i).getDeskripsi();
        final String foto1 = this.fData.get(i).getFoto();
        final String penulis1 = this.fData.get(i).getPenulis();

        txtJudul.setText(this.fData.get(i).getJudul());
        txtTglUpload.setText(this.fData.get(i).getTanggal());
        txtDeskripsi.setText(this.fData.get(i).getDeskripsi());
        Picasso.with(this.context)
                .load(this.fData.get(i).getFoto())
                .resize(87 ,60)
                .noFade()
                .into(img);

        bgCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context, ArtikelPembeliActivity.class);
                in.putExtra("foto", foto1);
                in.putExtra("judul", judul1);
                in.putExtra("penulis", penulis1);
                in.putExtra("deskripsi", deskripsi1);
                context.startActivity(in);
            }
        });

        return view;
    }
}
