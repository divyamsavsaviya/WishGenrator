package com.aimulate.wishgenrator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EditWishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wish);

        TextView textView = findViewById(R.id.textView);
        String wish = getIntent().getStringExtra("WISH");
        textView.setText(wish);

    }
}