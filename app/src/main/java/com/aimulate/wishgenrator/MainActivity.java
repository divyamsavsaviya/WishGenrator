package com.aimulate.wishgenrator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aimulate.wishgenrator.adapters.FilterAdapter;
import com.aimulate.wishgenrator.data.Filter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGenerateCustomWish = findViewById(R.id.buttonGenerateCustomWish);
        buttonGenerateCustomWish.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,CustomWishActivity.class));
        });

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        FilterAdapter adapter = new FilterAdapter(filterId -> {
            Intent intent = new Intent(MainActivity.this, WishActivity.class);
            intent.putExtra("FILTER_ID",filterId);
            startActivity(intent);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        List<Filter> filters = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("filters");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                filters.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Filter filter = snapshot1.getValue(Filter.class);
                    filters.add(filter);
                    Log.d("LOG--", "onDataChange: " + filter);
                }
                progressBar.setVisibility(View.GONE);
                runOnUiThread(() -> {
                    adapter.submitList(filters);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("LOG--", "onCancelled: ", error.toException());
                Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}