package com.aimulate.wishgenrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditWishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wish);

        TextView textView = findViewById(R.id.textView);
        String wish = getIntent().getStringExtra("WISH");

        textView.setText("\uD835\uDD04, \uD835\uDD05, ℭ, \uD835\uDD07, \uD835\uDD08, \uD835\uDD09, \uD835\uDD0A, ℌ, ℑ, \uD835\uDD0D, \uD835\uDD0E, \uD835\uDD0F, \uD835\uDD10, \uD835\uDD11, \uD835\uDD12, \uD835\uDD13, \uD835\uDD14, ℜ, \uD835\uDD16, \uD835\uDD17, \uD835\uDD18, \uD835\uDD19, \uD835\uDD1A, \uD835\uDD1B, \uD835\uDD1C, ℨ");

        textView.setOnClickListener(v -> {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("data",textView.getText());
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(EditWishActivity.this, "Copied!", Toast.LENGTH_SHORT).show();
        });



    }
}