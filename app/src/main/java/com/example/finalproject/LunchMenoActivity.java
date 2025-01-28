package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LunchMenoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_meno);

        Button btnChickenCurry = findViewById(R.id.btn_chicken_curry);
        Button btnRiceRecipe = findViewById(R.id.btn_rice_recipe);

        btnChickenCurry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LunchMenoActivity.this, ChickenCurryActivity.class);
                startActivity(intent);
            }
        });

        btnRiceRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LunchMenoActivity.this, RiceRecipeActivity.class);
                startActivity(intent);
            }
        });
    }
}
