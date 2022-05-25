package com.codelabs.pocketuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MailConfirmationActivity extends AppCompatActivity {

    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_confirmation);

        btnLogin = findViewById(R.id.btn_confirm_email_send);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MailConfirmationActivity.this, LoginActivity.class));
                Animatoo.animateSlideRight(MailConfirmationActivity.this);
            }
        });
    }
}