package com.example.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ViewRecipeActivity extends AppCompatActivity {
    private ListView listViewrecipe;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        listViewrecipe = findViewById(R.id.recipe_list_item); // Corrected ID
        Button buttonUpdate = findViewById(R.id.button_update);
        Button buttonDelete = findViewById(R.id.button_delete);
        databaseHelper = new DatabaseHelper(this);

        displayTechnologies();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleUpdate();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDelete();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayTechnologies();
    }

    private void displayTechnologies() {
        try {
            Cursor cursor = databaseHelper.getAllRecipes();
            if (cursor != null) {
                RecipeAdapter adapter = new RecipeAdapter(this, cursor, 0);
                listViewrecipe.setAdapter(adapter);
            } else {
                Toast.makeText(this, "No recipes found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void handleUpdate() {
        Intent intent = new Intent(ViewRecipeActivity.this, UpdateRecipeActivity.class);
        startActivity(intent);
    }

    private void handleDelete() {
        Intent intent = new Intent(ViewRecipeActivity.this, DeleteRecipeActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Delete button clicked", Toast.LENGTH_SHORT).show();
    }
}
