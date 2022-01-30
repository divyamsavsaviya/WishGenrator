package com.aimulate.wishgenrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class FinalShareActivity extends AppCompatActivity {

    String fancyWish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_share);

        fancyWish = getIntent().getStringExtra("FANCY_WISH");
        TextView textViewFancyWish = findViewById(R.id.textViewFancyWish);
        Button buttonWhatsapp = findViewById(R.id.buttonWhatsapp);
        Button buttonInstagram = findViewById(R.id.buttonInstagram);
        Button buttonOther = findViewById(R.id.buttonOther);
        Button buttonInvite = findViewById(R.id.buttonInvite);
        textViewFancyWish.setText(fancyWish);

        buttonWhatsapp.setOnClickListener(v -> {
            sendMessage(fancyWish, "com.whatsapp");
        });
        buttonInstagram.setOnClickListener(v -> {
            sendMessage(fancyWish, "com.instagram.android");
        });
        buttonOther.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, fancyWish);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

        buttonInvite.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.instagram.android"
                    + "\n\ndownload this application to create awesome wishes");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });


        textViewFancyWish.setOnClickListener(v -> {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("data", textViewFancyWish.getText());
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(FinalShareActivity.this, "Copied!", Toast.LENGTH_SHORT).show();
        });
    }

    private void sendMessage(String wish, String target_app_package) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setPackage(target_app_package);

        // Give your message here
        intent.putExtra(Intent.EXTRA_TEXT, wish);

        // Checking whether Whatsapp
        // is installed or not
        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "Please install application first.", Toast.LENGTH_SHORT).show();
            return;
        }
        // Starting Whatsapp
        startActivity(intent);
    }
}