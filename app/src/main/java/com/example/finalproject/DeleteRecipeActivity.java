package com.example.finalproject;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteRecipeActivity extends AppCompatActivity {

    private EditText editTextRecipeName;
    private ImageView imageViewRecipe;
    private Button buttonDelete, buttonSearch;
    private TextView textViewRecipeCost;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_recipe);

        editTextRecipeName = findViewById(R.id.edit_text_recipe_name);
        textViewRecipeCost = findViewById(R.id.text_view_recipe_cost);
        imageViewRecipe = findViewById(R.id.image_view_recipe);
        buttonDelete = findViewById(R.id.button_delete);
        buttonSearch = findViewById(R.id.button_search);

        databaseHelper = new DatabaseHelper(this);

        buttonSearch.setOnClickListener(view -> searchRecipe());
        buttonDelete.setOnClickListener(view -> deleteRecipe());
    }

    private void searchRecipe() {
        String recipeName = editTextRecipeName.getText().toString().trim();
        if (recipeName.isEmpty()) {
            Toast.makeText(this, "Please enter a recipe name to search", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor cursor = databaseHelper.getRecipeByName(recipeName);
        if (cursor != null && cursor.moveToFirst()) {
            double recipeCost = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_COST));
            byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_IMAGE));

            textViewRecipeCost.setText("Cost: $" + recipeCost);

            if (image != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageViewRecipe.setImageBitmap(bitmap);
            } else {
                imageViewRecipe.setImageResource(R.drawable.default_recipe_image); // Default image
            }
            cursor.close();
        } else {
            Toast.makeText(this, "Recipe not found", Toast.LENGTH_SHORT).show();
            clearFields();
        }
    }

    private void deleteRecipe() {
        String recipeName = editTextRecipeName.getText().toString().trim();
        if (recipeName.isEmpty()) {
            Toast.makeText(this, "Please enter a recipe name to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        int rowsDeleted = databaseHelper.deleteRecipe(recipeName);
        if (rowsDeleted > 0) {
            Toast.makeText(this, "Recipe deleted successfully", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Recipe not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        editTextRecipeName.setText("");
        textViewRecipeCost.setText("Recipe Cost");
        imageViewRecipe.setImageResource(R.drawable.default_recipe_image);
    }
}