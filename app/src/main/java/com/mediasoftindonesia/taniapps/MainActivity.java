package com.mediasoftindonesia.taniapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mediasoftindonesia.taniapps.api.Petani;
import com.mediasoftindonesia.taniapps.utils.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private Petani petani;
    private Context context;

    private BottomNavigationViewEx.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()){
                case R.id.ic_home:
                    transaction.replace(R.id.content, new HomeFragment()).commit();
                    return true;
                case R.id.ic_catalog:
                   Intent intent1 = new Intent(MainActivity.this, KatalogFragment.class);
                    startActivity(intent1);
                    return true;
                case R.id.ic_profile:
                    transaction.replace(R.id.content, new ProfileFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        context = MainActivity.this;

        if (getIntent().hasExtra("petani")){
            try {
                petani = (Petani) getIntent().getSerializableExtra("petani");
                pref = context.getSharedPreferences("petani", MODE_PRIVATE);
                editor = pref.edit();
                editor.putString("nama", petani.getNama());
                editor.putString("email", petani.getEmail());
                editor.putString("no_telp", petani.getNo_telp());
                editor.putString("alamat", petani.getAlamat());
                editor.apply();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            pref = context.getSharedPreferences("petani", MODE_PRIVATE);
            petani = new Petani();
            petani.setNama(pref.getString("nama", "Tidak Masuk"));
            petani.setEmail(pref.getString("email", "Tidak Masuk"));
            petani.setNo_telp(pref.getString("no_telp", "Tidak Masuk"));
            petani.setAlamat(pref.getString("alamat", "Tidak Masuk"));
        }

        pref = getSharedPreferences("petani", Context.MODE_PRIVATE);

        Log.d(TAG, pref.getString("name", ""));
        Log.d(TAG, pref.getString("password", ""));
        Log.d(TAG, "onCreate: starting");

        setupBottomNavigationView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new HomeFragment()).commit();
    }
    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        bottomNavigationViewEx.setIconSize(25,25);
        bottomNavigationViewEx.setIconsMarginTop(20);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.ubah_password:
                Toast.makeText(MainActivity.this, "ubah password",Toast.LENGTH_LONG).show();
                return true;

            case R.id.sign_out:
                editor = pref.edit();
                editor.clear();
                editor.commit();
                editor.apply();
                Intent intent1 = new Intent(MainActivity.this, SplashScreenActivity.class);
                startActivity(intent1);
                return true;

            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

}
