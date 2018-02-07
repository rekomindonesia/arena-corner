package com.mediasoftindonesia.taniapps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mediasoftindonesia.taniapps.api.Pembeli;
import com.mediasoftindonesia.taniapps.api.PembeliService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPembeliActivity extends AppCompatActivity {

    private TextView mDaftar;
    private ImageView mMasuk;
    private EditText mUserEditText;
    private EditText mPswEditText;
    private String nama, password, email, no_telp, alamat;

    private String url = "http://tukangonline.hol.es/server/index.php/";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    ProgressDialog pd;
    ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pembeli);

        mMasuk = (ImageView) findViewById(R.id.imageView9);
        mMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserEditText = (EditText) findViewById(R.id.editText);
                mPswEditText = (EditText) findViewById(R.id.editText2);

                final String nama1 = mUserEditText.getText().toString();
                final String password1 = mPswEditText.getText().toString();

                connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getActiveNetworkInfo() != null
                        && connectivityManager.getActiveNetworkInfo().isAvailable()
                        && connectivityManager.getActiveNetworkInfo().isConnected()){
                    if (nama1.equals("") || password1.equals("")){
                        Toast.makeText(getApplicationContext(),"Masukan username dan password!", Toast.LENGTH_LONG).show();
                    }else {
                        inisialisasiRetrofit();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"No Internet Connection", Toast.LENGTH_LONG).show();
                }
                pd = new ProgressDialog(LoginPembeliActivity.this);
            }
        });

        mDaftar = (TextView) findViewById(R.id.textView11);
        mDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPembeliActivity.this, BuyerCreateNewAccountActivity.class);
                startActivity(intent);
            }
        });
    }
    private void inisialisasiRetrofit() {

        mUserEditText = (EditText) findViewById(R.id.editText);
        mPswEditText = (EditText) findViewById(R.id.editText2);

        nama = mUserEditText.getText().toString();
        password = mPswEditText.getText().toString();

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PembeliService service = retrofit.create(PembeliService.class);
        Call<List<Pembeli>> call  = service.getPembeli(nama,password);
        call.enqueue(new Callback<List<Pembeli>>() {
            @Override
            public void onResponse(Call<List<Pembeli>> call, Response<List<Pembeli>> response) {
                try {
                    List<Pembeli> pembeli = response.body();
                    Intent intent = new Intent(LoginPembeliActivity.this, Main2Activity.class);
                    Context context = LoginPembeliActivity.this;

                    pref = context.getSharedPreferences("pembeli",Context.MODE_PRIVATE);
                    editor = pref.edit();
                    editor.putString("id_pembeli", pembeli.get(0).getId_pembeli());
                    editor.putString("password", pembeli.get(0).getPassword());
                    editor.putString("nama", pembeli.get(0).getNama());
                    editor.putString("email", pembeli.get(0).getEmail());
                    editor.putString("alamat", pembeli.get(0).getAlamat());
                    editor.putString("no_telp", pembeli.get(0).getNo_telp());
                    editor.apply();

                    intent.putExtra("pembeli", pembeli.get(0));
                    startActivity(intent);

                }catch (Exception e){
                    //Toast.makeText(LoginActivity.this, "username dan password salah. \n Silahkan coba lagi",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pembeli>> call, Throwable throwable) {

            }
        });

    }
}
