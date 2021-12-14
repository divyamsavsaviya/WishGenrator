package com.aimulate.wishgenrator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aimulate.wishgenrator.adapters.WishAdapter;
import com.aimulate.wishgenrator.data.Wish;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish);
        Toast.makeText(WishActivity.this, "Wish Activity Launched", Toast.LENGTH_SHORT).show();
        String filterId = getIntent().getStringExtra("FILTER_ID");
        ProgressBar progressBar = findViewById(R.id.progressBarActivityWish);
        progressBar.setVisibility(View.VISIBLE);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewActivityWish);
        WishAdapter adapter = new WishAdapter(new WishAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(String wish) {
                Intent intent = new Intent(WishActivity.this,EditWishActivity.class);
                intent.putExtra(getString(R.string.KEY_WISH),wish);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        List<Wish> wishes = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference()
                .child("filters")
                .child(filterId)
                .child("wishes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                wishes.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Wish wish = snapshot1.getValue(Wish.class);
                    wishes.add(wish);
                }
                Log.d("LOG--", "onDataChange: " + wishes);
                Toast.makeText(WishActivity.this, "Loadded Successfully", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                runOnUiThread(() -> {
                    adapter.submitList(wishes);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}