package com.aimulate.wishgenrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

public class CustomWishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_wish);

        TextInputLayout textInputLayout = findViewById(R.id.editTextLayoutWish);
        Button buttonGenerateCustomWish = findViewById(R.id.buttonGenerateCustomWish);

        buttonGenerateCustomWish.setOnClickListener(v -> {
            String wish = textInputLayout.getEditText().getText().toString();
            if (wish.isEmpty()) {
                textInputLayout.setError("Enter Custom Wish");
            } else {
                Intent intent = new Intent(CustomWishActivity.this, EditWishActivity.class);
                intent.putExtra(getString(R.string.KEY_WISH), wish.toLowerCase(Locale.ROOT));
                startActivity(intent);
            }
        });

        textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}