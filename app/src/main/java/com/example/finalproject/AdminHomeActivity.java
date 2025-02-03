package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);


        Button btn_insert_tec =findViewById(R.id.btn_insert_recipe);
        Button btn_view_tec =findViewById(R.id.btn_view_tec);

        btn_insert_tec.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, InsertRecipeActivity.class);
            startActivity(intent);
        });

        btn_view_tec.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, ViewRecipeActivity.class);
            startActivity(intent);
        });




    }
}