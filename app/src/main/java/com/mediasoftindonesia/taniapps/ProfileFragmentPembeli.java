package com.mediasoftindonesia.taniapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediasoftindonesia.taniapps.api.Petani;
import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragmentPembeli extends Fragment {

    TextView mKeluar;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private Petani petani;
    private Context context;

    private String nama, alamat, no_telp, foto;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_profile_fragment_pembeli, container, false);
        context = view.getContext();
        pref = context.getSharedPreferences("pembeli", MODE_PRIVATE);

        nama = pref.getString("nama",null);
        alamat = pref.getString("alamat",null);
        no_telp = pref.getString("no_telp",null);
        foto = pref.getString("foto",null);


        TextView mName = (TextView) view.findViewById(R.id.textViewUnggah);
        mName.setText(nama);

        TextView mAlamat = (TextView) view.findViewById(R.id.textView4);
        mAlamat.setText(alamat);

        TextView mNomorhp = (TextView) view.findViewById(R.id.textView3);
        mNomorhp.setText(no_telp);

        ImageView imageView = (ImageView) view.findViewById(R.id.foto);
        Picasso.with(getContext())
                .load(foto)
                .resize(100,100)
                .noFade()
                .into(imageView);

        mKeluar = (TextView) view.findViewById(R.id.textViewKeluar);
        mKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor = pref.edit();
                editor.clear();
                editor.commit();
                editor.apply();
                Intent intent1 = new Intent(getContext(), ChoiceActivity.class);
                startActivity(intent1);
            }
        });
        return view;
    }
}
