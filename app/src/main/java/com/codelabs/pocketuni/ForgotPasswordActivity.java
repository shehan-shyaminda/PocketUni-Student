package com.codelabs.pocketuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnSend;
    private TextView btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        btnBack = findViewById(R.id.ic_forgot_password_back);
        btnSend = findViewById(R.id.btn_forgot_password_send);
        btnLogin = findViewById(R.id.btn_forgot_password_login);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                Animatoo.animateSlideRight(ForgotPasswordActivity.this);
                finishAffinity();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                Animatoo.animateSlideRight(ForgotPasswordActivity.this);
                finishAffinity();
            }
        });
    }
}