package com.mediasoftindonesia.taniapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class FirstSplashActivity extends AppCompatActivity {

    private static int splashInterval = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Context context = FirstSplashActivity.this;
                SharedPreferences sharedPreferences = context.getSharedPreferences("member", context.MODE_PRIVATE);
                if (sharedPreferences.getString("name",null) != null){
                    // TODO Auto-generated method stub
                    Intent i = new Intent(FirstSplashActivity.this,MainActivity.class);
                    startActivity(i); // menghubungkan activity splashscren ke main activity dengan intent

                }else {
                    Intent i = new Intent(FirstSplashActivity.this,ChoiceActivity.class);
                    startActivity(i);
                }
                //jeda selesai Splashscreen
                this.finish();
            }
            private void finish() {
                // TODO Auto-generated method stub
            }
        }, splashInterval);
    }
}
