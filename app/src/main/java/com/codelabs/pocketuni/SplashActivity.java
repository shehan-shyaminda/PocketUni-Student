package com.codelabs.pocketuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.codelabs.pocketuni.services.SharedPreferencesManager;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferencesManager sharedPreferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferencesManager = new SharedPreferencesManager(SplashActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sharedPreferencesManager.getBooleanPreferences(SharedPreferencesManager.USER_LOGGED_IN) == true) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }else{
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finishAffinity();
            }
        }, 2000);

    }
}