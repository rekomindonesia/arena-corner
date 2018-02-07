package com.mediasoftindonesia.taniapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mediasoftindonesia.taniapps.api.Petani;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends android.support.v4.app.Fragment {

    TextView mKeluar;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private Petani petani;
    private Context context;
    private TextView textViewNoTelp, textViewAlamat;

    private String nama, alamat, no_telp, deskripsi;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_profile_fragment, container, false);
        context = view.getContext();
        pref = context.getSharedPreferences("petani", MODE_PRIVATE);

        nama = pref.getString("nama",null);
        alamat = pref.getString("alamat",null);
        no_telp = pref.getString("no_telp",null);
        deskripsi = pref.getString("deskripsi",null);


        TextView mName = (TextView) view.findViewById(R.id.textViewUnggah);
        mName.setText(nama);

        TextView mAlamat = (TextView) view.findViewById(R.id.textView3);
        mAlamat.setText(alamat);

        TextView mNomorhp = (TextView) view.findViewById(R.id.textView4);
        mNomorhp.setText(no_telp);

        TextView mDeskripsi = (TextView) view.findViewById(R.id.textView30);
        mDeskripsi.setText(deskripsi);

        //ImageView imageView = (ImageView) view.findViewById(R.id.foto);

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

        TextView mEdit = (TextView) view.findViewById(R.id.textView2);
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), EditProfilPetaniActivity.class);
                startActivity(in);
            }
        });

        String noTelp1 = mNomorhp.getText().toString();
        String alamat1 = mAlamat.getText().toString();

        Intent i = new Intent(context, ProfileFragment.class);
        i.putExtra("no_telp", noTelp1);
        i.putExtra("alamat", alamat1);

        return view;
    }
}
