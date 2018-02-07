package com.mediasoftindonesia.taniapps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity {

    Button mPetani, mPembeli;
    public boolean isFirstStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        mPetani = (Button) findViewById(R.id.buttonPetani);
        mPembeli = (Button) findViewById(R.id.buttonPembeli);

        mPetani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = ChoiceActivity.this;
                SharedPreferences sharedPreferences = context.getSharedPreferences("petani", context.MODE_PRIVATE);
                if (sharedPreferences.getString("nama", null) != null){
                    Intent i = new Intent(ChoiceActivity.this, MainActivity.class);
                    startActivity(i);
                }else {
                    Intent intent1 = new Intent(ChoiceActivity.this, SplashScreenActivity.class);
                    startActivity(intent1);
                }
            }
        });

        mPembeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = ChoiceActivity.this;
                SharedPreferences sharedPreferences = context.getSharedPreferences("pembeli", context.MODE_PRIVATE);
                if (sharedPreferences.getString("nama", null) != null){
                    Intent i = new Intent(ChoiceActivity.this, Main2Activity.class);
                    startActivity(i);
                }else {
                    Intent intent1 = new Intent(ChoiceActivity.this, LoginPembeliActivity.class);
                    startActivity(intent1);
                }
            }
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Intro App Initialize SharedPreferences
                SharedPreferences getSharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                isFirstStart = getSharedPreferences.getBoolean("firstStart", true);

                //  Check either activity or app is open very first time or not and do action
                if (isFirstStart) {

                    //  Launch application introduction screen
                    Intent i = new Intent(ChoiceActivity.this, MyIntro.class);
                    startActivity(i);
                    SharedPreferences.Editor e = getSharedPreferences.edit();
                    e.putBoolean("firstStart", false);
                    e.apply();
                }
            }
        });
        t.start();
    }

    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        ChoiceActivity.this.finish();
                    }
                }).create().show();
    }
}
