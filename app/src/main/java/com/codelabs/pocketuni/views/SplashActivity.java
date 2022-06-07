package com.codelabs.pocketuni.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.codelabs.pocketuni.R;
import com.codelabs.pocketuni.services.SharedPreferencesManager;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferencesManager sharedPreferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_splash);

        sharedPreferencesManager = new SharedPreferencesManager(SplashActivity.this);

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(2000);

                    if(sharedPreferencesManager.getBooleanPreferences(SharedPreferencesManager.USER_LOGGED_IN) == true) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }else{
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finishAffinity();
                } catch (Exception e) {
                }
            }
        };
        background.start();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//            }
//        }, 2000);

    }
}