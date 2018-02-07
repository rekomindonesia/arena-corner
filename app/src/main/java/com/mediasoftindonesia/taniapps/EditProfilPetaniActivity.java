package com.mediasoftindonesia.taniapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditProfilPetaniActivity extends AppCompatActivity {

    private TextInputLayout mNoTelp, mAlamat;
    private EditText editTextNoTelp, editTextAlamat;
    private ImageView mKembali;
    private Button mUbah;

    private String url = "http://tukangonline.hol.es/server/index.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil_petani);

        mNoTelp = (TextInputLayout) findViewById(R.id.textIpnutLayout1);
        mAlamat = (TextInputLayout) findViewById(R.id.textIpnutLayout2);

        editTextNoTelp = (EditText) findViewById(R.id.EditTextNoTelp);
        editTextAlamat = (EditText) findViewById(R.id.EditTextAlamat);

        mNoTelp.setHint("Nomor Telepon");
        mAlamat.setHint("Alamat");

        mKembali = (ImageView) findViewById(R.id.imageViewSilang1);
        mKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(EditProfilPetaniActivity.this, MainActivity.class);
                startActivity(in);
            }
        });

        mUbah = (Button) findViewById(R.id.buttonUnggah);
        mUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 = new Intent(EditProfilPetaniActivity.this, MainActivity.class);

                startActivity(in2);
            }
        });
    }
}
