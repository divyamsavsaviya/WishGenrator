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

    public void shareOnInstagram(View view) {
        sendMessage(fancyWish,"com.instagram");
    }

    public void shareOnWhatsApp(View view) {
        sendMessage(fancyWish,"com.whatsapp");
    }

    public void shareOnTwitter(View view) {
        sendMessage(fancyWish,"com.twitter.android");
    }

    public void shareOnMessenger(View view) {
        sendMessage(fancyWish,"com.messenger");
    }

    private void sendMessage(String wish,String target_app_package) {
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