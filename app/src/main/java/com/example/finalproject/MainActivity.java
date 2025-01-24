package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        ImageView imgGreetingImageView = findViewById(R.id.img_greetingImageView);
        TextView txtGreetingTextView = findViewById(R.id.txt_greetingTextView);
        TextView txtGreetingTextView1 = findViewById(R.id.txt_greetingTextView1);
        Button btStartButton = findViewById(R.id.bt_startButton);

        // Set click listener to navigate to RegiLoginActivity
        btStartButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegiLoginActivity.class);
            startActivity(intent); // Navigate to RegiLoginActivity
        });
    }
}
