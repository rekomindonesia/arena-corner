package com.mediasoftindonesia.taniapps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mediasoftindonesia.taniapps.api.Petani;
import com.mediasoftindonesia.taniapps.api.PetaniService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView mFbLogoImageView, mRectangle1, mRectangle2;
    private EditText mUserEditText;
    private EditText mPswEditText;
    private TextView mForgotPswTextView;
    private Button mLoginButton;
    private Button mNewAccountButton;
    private ImageView mFbLogoStaticImageView, mBgLogin;
    private TextInputLayout usernameWrapper, passwordWrapper;
    private String nama, password, email, no_telp, alamat;

    private String url = "http://tukangonline.hol.es/server/index.php/";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    ProgressDialog pd;
    ConnectivityManager connectivityManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mLoginButton = (Button) findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mUserEditText = (EditText) findViewById(R.id.userEditText);
                mPswEditText = (EditText) findViewById(R.id.pswEditText);

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
                pd = new ProgressDialog(SplashScreenActivity.this);

            }
        });

        mNewAccountButton = (Button) findViewById(R.id.newAccountButton);
        mNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SplashScreenActivity.this, CreateNewAccountActivity.class);
                startActivity(intent1);
            }
        });

    }

    private void inisialisasiRetrofit() {

        mUserEditText = (EditText) findViewById(R.id.userEditText);
        mPswEditText = (EditText) findViewById(R.id.pswEditText);

        nama = mUserEditText.getText().toString();
        password = mPswEditText.getText().toString();

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PetaniService service = retrofit.create(PetaniService.class);
        Call<List<Petani>> call  = service.getPetani(nama,password);
        call.enqueue(new Callback<List<Petani>>() {
            @Override
            public void onResponse(Call<List<Petani>> call, Response<List<Petani>> response) {
                try {
                    List<Petani> petani = response.body();
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    Context context = SplashScreenActivity.this;

                    pref = context.getSharedPreferences("petani",Context.MODE_PRIVATE);
                    editor = pref.edit();
                    editor.putString("id_petani", petani.get(0).getId_petani());
                    editor.putString("password", petani.get(0).getPassword());
                    editor.putString("nama", petani.get(0).getNama());
                    editor.putString("email", petani.get(0).getEmail());
                    editor.putString("alamat", petani.get(0).getAlamat());
                    editor.putString("no_telp", petani.get(0).getNo_telp());
                    editor.putString("deskripsi", petani.get(0).getDeskripsi());
                    editor.apply();

                    intent.putExtra("petani", petani.get(0));
                    startActivity(intent);

                }catch (Exception e){
                    //Toast.makeText(LoginActivity.this, "username dan password salah. \n Silahkan coba lagi",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Petani>> call, Throwable throwable) {

            }
        });

    }
}
