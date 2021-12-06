package com.aimulate.wishgenrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StartScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        Button buttonGenerateWish = findViewById(R.id.buttonGenerateWish);
        Button buttonAboutApplication = findViewById(R.id.buttonAboutApplication);
        Button buttonAboutDeveloper = findViewById(R.id.buttonAboutDeveloper);
        Button buttonPrivacyPolicy = findViewById(R.id.buttonPrivacyPolicy);

        buttonGenerateWish.setOnClickListener(v -> {
            startActivity(new Intent(StartScreenActivity.this,MainActivity.class));
        });

        buttonAboutApplication.setOnClickListener(v -> {
            startActivity(new Intent(StartScreenActivity.this,AboutApplicationActivity.class));
        });

        buttonAboutDeveloper.setOnClickListener(v -> {
            startActivity(new Intent(StartScreenActivity.this,AboutDeveloperActivity.class));
        });

        buttonPrivacyPolicy.setOnClickListener(v -> {
            startActivity(new Intent(StartScreenActivity.this, PrivacyPolicyActivity.class));
        });
    }
}