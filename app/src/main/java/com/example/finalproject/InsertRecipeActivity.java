package com.example.finalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class InsertRecipeActivity extends AppCompatActivity {

    private EditText etRecipeName, etRecipeDescription, etIngredients, etSteps;
    private Button btnSaveRecipe;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_recipe);

        // Initialize UI elements
        etRecipeName = findViewById(R.id.et_recipe_name);
        etRecipeDescription = findViewById(R.id.et_recipe_description);
        etIngredients = findViewById(R.id.et_ingredients);
        etSteps = findViewById(R.id.et_steps);
        btnSaveRecipe = findViewById(R.id.btn_save_recipe);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Save Recipe Button Click Listener
        btnSaveRecipe.setOnClickListener(v -> {
            String recipeName = etRecipeName.getText().toString().trim();
            String recipeDescription = etRecipeDescription.getText().toString().trim();
            String ingredients = etIngredients.getText().toString().trim();
            String steps = etSteps.getText().toString().trim();

            if (recipeName.isEmpty() || recipeDescription.isEmpty() || ingredients.isEmpty() || steps.isEmpty()) {
                Toast.makeText(InsertRecipeActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            } else {
                boolean isInserted = databaseHelper.insertRecipe(recipeName, recipeDescription, ingredients, steps);
                if (isInserted) {
                    Toast.makeText(InsertRecipeActivity.this, "Recipe Saved Successfully!", Toast.LENGTH_LONG).show();

                    // Clear fields after saving
                    etRecipeName.setText("");
                    etRecipeDescription.setText("");
                    etIngredients.setText("");
                    etSteps.setText("");
                } else {
                    Toast.makeText(InsertRecipeActivity.this, "Error saving recipe!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
