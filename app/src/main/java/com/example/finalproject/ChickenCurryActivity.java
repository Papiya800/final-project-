package com.example.finalproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ChickenCurryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken_curry);

        // Finding views by their IDs
        TextView ingredientsLabel = findViewById(R.id.txt_ingredients_label);
        TextView ingredients = findViewById(R.id.txt_ingredients);
        TextView instructionsLabel = findViewById(R.id.txt_instructions_label);
        TextView instructions = findViewById(R.id.txt_instructions);

        // Setting dynamic text (if needed)
        // Example: updating instructions dynamically
        String updatedInstructions = instructions.getText().toString() + "\n\nEnjoy your meal!";
        instructions.setText(updatedInstructions);
    }
}
