package com.mediasoftindonesia.taniapps;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mediasoftindonesia.taniapps.api.Petani;
import com.mediasoftindonesia.taniapps.api.PetaniService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateNewAccountActivity extends AppCompatActivity {

    private TextInputLayout mName, mEmail, mPassword, mNoHp, mAlamat;
    private TextView loginHere;
    private Button mBtnSignUp;
    ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registrasi");

        setContentView(R.layout.activity_create_new_account);

        mName = (TextInputLayout) findViewById(R.id.textIpnutLayout1);
        mEmail = (TextInputLayout) findViewById(R.id.textIpnutLayout2);
        mPassword = (TextInputLayout) findViewById(R.id.textIpnutLayout3);
        mNoHp = (TextInputLayout) findViewById(R.id.textIpnutLayout4);
        mAlamat = (TextInputLayout) findViewById(R.id.textIpnutLayout5);

        final EditText nama = (EditText) findViewById(R.id.nameEditText);
        final EditText email = (EditText) findViewById(R.id.emailEditText);
        final EditText password = (EditText) findViewById(R.id.passwordEditText);
        final EditText alamat = (EditText) findViewById(R.id.alamatEditText);
        final EditText no_telp = (EditText) findViewById(R.id.noTelpEditText);

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        final Button mBtnSignUp = (Button) findViewById(R.id.signUpButton);
        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RETROFIT
                Gson gson = new GsonBuilder().create();

                String url = "http://tukangonline.hol.es/server/index.php/";

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create(gson)).build();

                PetaniService service = retrofit.create(PetaniService.class);

                String nama1 = nama.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String alamat1 = alamat.getText().toString();
                String no_telp1 = no_telp.getText().toString();

                Petani petani = new Petani();

                petani.setNama(nama.getText().toString());
                petani.setEmail(email.getText().toString());
                petani.setPassword(password.getText().toString());
                petani.setAlamat(alamat.getText().toString());
                petani.setNo_telp(no_telp.getText().toString());

                if (view == mBtnSignUp){
                    if (connectivityManager.getActiveNetworkInfo() != null
                            && connectivityManager.getActiveNetworkInfo().isAvailable()
                            && connectivityManager.getActiveNetworkInfo().isConnected()){

                        if(nama1.equals("") || email1.equals("") || password1.equals("") || alamat1.equals("") || no_telp1.equals("")){
                            Toast.makeText(getApplicationContext(),"Isi semua data!", Toast.LENGTH_LONG).show();
                        }else {

                            service.insertPetani(nama.getText().toString(), email.getText().toString(), password.getText().toString(),
                                    alamat.getText().toString(), no_telp.getText().toString())
                                    .enqueue(new Callback<Petani>() {
                                        @Override
                                        public void onResponse(Call<Petani> call, Response<Petani> response) {
                                            Toast.makeText(getApplicationContext(),"Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(CreateNewAccountActivity.this, SplashScreenActivity.class);
                                            startActivity(intent);
                                        }
                                        @Override
                                        public void onFailure(Call<Petani> call, Throwable throwable) {
                                        }
                                    });
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

        mName.setHint("Name");
        mEmail.setHint("Email");
        mPassword.setHint("Password");
        mNoHp.setHint("Nomor Telepon");
        mAlamat.setHint("Alamat");
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }


}
