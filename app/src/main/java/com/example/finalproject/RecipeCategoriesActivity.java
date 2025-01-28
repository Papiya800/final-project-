package com.example.finalproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_categories);

        // Initialize buttons
        Button btnBreakfast = findViewById(R.id.btn_breakfast);
        Button btnLunch = findViewById(R.id.btn_lunch);
        Button btnDinner = findViewById(R.id.btn_dinner);
        Button btnDessert = findViewById(R.id.btn_dessert);

        // Set up click listeners for each button to navigate to respective activities
        btnBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeCategoriesActivity.this, BreakfastMenuActivity.class);
                startActivity(intent);
            }
        });

        btnLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeCategoriesActivity.this, LunchMenoActivity.class);
                startActivity(intent);
            }
        });

        btnDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeCategoriesActivity.this, DinnerActivity.class);
                startActivity(intent);
            }
        });

        btnDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeCategoriesActivity.this, DessertActivity.class);
                startActivity(intent);
            }
        });
    }
}

