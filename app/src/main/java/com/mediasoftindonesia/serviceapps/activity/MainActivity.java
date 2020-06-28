package com.mediasoftindonesia.serviceapps.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mediasoftindonesia.serviceapps.R;
import com.mediasoftindonesia.serviceapps.fragment.HomeFragment;
import com.mediasoftindonesia.serviceapps.fragment.HistoryFragment;
import com.mediasoftindonesia.serviceapps.token.TokenManager;
import com.mediasoftindonesia.serviceapps.utils.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TokenManager tokenManager;
    private BottomNavigationViewEx bottomNavigationViewEx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        setupBottomNavigationView();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new HomeFragment()).commit();
    }

    private BottomNavigationViewEx.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()){
                case R.id.ic_home:
                    transaction.replace(R.id.content, new HomeFragment()).commit();
                    return true;
                case R.id.ic_profile:
                    transaction.replace(R.id.content, new HistoryFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        bottomNavigationViewEx.setIconSize(25,25);
        bottomNavigationViewEx.setIconsMarginTop(20);
    }
}
