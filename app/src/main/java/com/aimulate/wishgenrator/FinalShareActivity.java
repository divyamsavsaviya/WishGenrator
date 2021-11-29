package com.aimulate.wishgenrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FinalShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_share);

        String fancyWish = getIntent().getStringExtra("FANCY_WISH");
        TextView textViewFancyWish = findViewById(R.id.textViewFancyWish);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, fancyWish + "\n Application description\n its working");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });
        textViewFancyWish.setText(fancyWish);

        textViewFancyWish.setOnClickListener(v -> {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("data",textViewFancyWish.getText());
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(FinalShareActivity.this, "Copied!", Toast.LENGTH_SHORT).show();
        });
    }
}