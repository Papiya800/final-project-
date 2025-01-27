package com.example.finalproject;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuesadillaActivity extends AppCompatActivity {

    private TextView txtIngredients;
    private TextView txtInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesadilla); // এখানে আপনি আপনার XML ফাইলের নাম দেবেন


        txtIngredients = findViewById(R.id.txt_ingredients);
        txtInstructions = findViewById(R.id.txt_instructions);

        String ingredients = "Eggs\nMilk\nGreen pepper\nUnsalted butter\nBacon\nShredded cheddar cheese\nTortillas";
        String instructions = "1. First, whisk the eggs and milk together.\n\n" +
                "2. Add the green pepper and stir to combine.\n\n" +
                "3. Add butter to a heated skillet and cook the eggs until soft.\n\n" +
                "4. Place one tortilla on the skillet, top with shredded cheese, followed by the eggs, bacon, and more cheese.\n\n" +
                "5. Place the second tortilla on top of the egg mixture and lightly press down to seal.\n\n" +
                "6. Heat for 2-3 minutes on both sides, or until cheese is melted.";


        txtIngredients.setText(ingredients);
        txtInstructions.setText(instructions);
    }
}
