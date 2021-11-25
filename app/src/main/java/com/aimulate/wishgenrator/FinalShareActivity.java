package com.aimulate.wishgenrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class FinalShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_share);

        String fancyWish = getIntent().getStringExtra("FANCY_WISH");
        TextView textViewFancyWish = findViewById(R.id.textViewFancyWish);
        textViewFancyWish.setText(fancyWish);

        textViewFancyWish.setOnClickListener(v -> {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("data",textViewFancyWish.getText());
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(FinalShareActivity.this, "Copied!", Toast.LENGTH_SHORT).show();
        });
    }
}