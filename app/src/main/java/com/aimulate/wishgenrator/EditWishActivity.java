package com.aimulate.wishgenrator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.aimulate.wishgenrator.adapters.FontAdapter;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class EditWishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wish);

        String wish = getIntent().getStringExtra(getString(R.string.KEY_WISH));
        TextView textView = findViewById(R.id.textViewEditWish);
        textView.setText(wish);
        String[] fonts = getResources().getStringArray(R.array.fonts);

        RecyclerView recyclerView = findViewById(R.id.fontWishRecyclerView);
        FontAdapter adapter = new FontAdapter(fancyWish -> {
            Intent intent = new Intent(EditWishActivity.this,FinalShareActivity.class);
            intent.putExtra("FANCY_WISH",fancyWish);
            startActivity(intent);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ArrayList<String> fancyWishes = new ArrayList<>();
        for(int i = 0 ; i < fonts.length; i++) {
            String fancyWish = generateText(wish,fonts[i]);
            Log.d("LOG--", "onCreate: " + wish + " " +  fancyWish);
            fancyWishes.add(fancyWish);
        }
        Log.d("LOG--", "onCreate: " + fancyWishes);
        adapter.submitList(fancyWishes);
    }

    public String generateText(String wish,String font){
        wish.toLowerCase(Locale.ROOT);
        char[] arrayWish = wish.toCharArray();
        String[] fontArray = font.split(" ");
        String fancyWish = "";

        for (char c : arrayWish) {
            if (c != ' ') {
                int index = getIndexOfAlphabet(c);
                fancyWish = fancyWish + fontArray[index - 1];
            } else {
                fancyWish = fancyWish + " ";
            }
        }
        return fancyWish;
    }

    private int getIndexOfAlphabet(char c) {
        int tamp = c;
        return (tamp - 96);
    }
}