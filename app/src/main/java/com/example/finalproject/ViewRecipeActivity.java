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

    private ListView listViewRecipes;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        listViewRecipes = findViewById(R.id.list_view_recipes);
        Button buttonUpdate = findViewById(R.id.button_update);
        Button buttonDelete = findViewById(R.id.button_delete);
        databaseHelper = new DatabaseHelper(this);

        displayRecipes();

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
        // Refresh the displayed recipes
        displayRecipes();
    }

    private void displayRecipes() {
        try {
            // Fetch all recipes from the database
            Cursor cursor = databaseHelper.getAllRecipes();

            // Create RecipeAdapter instance
            RecipeAdapter recipeAdapter = new RecipeAdapter(this, cursor);

            // Set adapter to the ListView
            listViewRecipes.setAdapter(recipeAdapter);
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
