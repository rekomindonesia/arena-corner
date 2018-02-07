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

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mediasoftindonesia.taniapps.api.Pembeli;
import com.mediasoftindonesia.taniapps.utils.BottomNavigationViewHelper;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private Pembeli pembeli;
    private Context context;

    private BottomNavigationViewEx.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()){
                case R.id.ic_home:
                    transaction.replace(R.id.content, new HomeFragmentPembeli()).commit();
                    return true;
                case R.id.ic_catalog:
                    transaction.replace(R.id.content, new KatalogFragmentPembeli()).commit();
                    return true;
                case R.id.ic_profile:
                    transaction.replace(R.id.content, new ProfileFragmentPembeli()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i = getIntent();
        context = Main2Activity.this;

        if (getIntent().hasExtra("pembeli")){
            try {
                pembeli = (Pembeli) getIntent().getSerializableExtra("pembeli");
                pref = context.getSharedPreferences("pembeli", MODE_PRIVATE);
                editor = pref.edit();
                editor.putString("nama", pembeli.getNama());
                editor.putString("email", pembeli.getEmail());
                editor.putString("no_telp", pembeli.getNo_telp());
                editor.putString("alamat", pembeli.getAlamat());
                editor.apply();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            pref = context.getSharedPreferences("pembeli", MODE_PRIVATE);
            pembeli = new Pembeli();
            pembeli.setNama(pref.getString("nama", "Tidak Masuk"));
            pembeli.setEmail(pref.getString("email", "Tidak Masuk"));
            pembeli.setNo_telp(pref.getString("no_telp", "Tidak Masuk"));
            pembeli.setAlamat(pref.getString("alamat", "Tidak Masuk"));
        }

        pref = getSharedPreferences("pembeli", Context.MODE_PRIVATE);

        Log.d(TAG, pref.getString("name", ""));
        Log.d(TAG, pref.getString("password", ""));
        Log.d(TAG, "onCreate: starting");

        setupBottomNavigationView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new HomeFragmentPembeli()).commit();
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
}
