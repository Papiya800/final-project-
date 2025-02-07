package com.example.finalproject;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteRecipeActivity extends AppCompatActivity {

    private EditText editTextName;
    private ImageView imageViewRecipe;
    private Button buttonSearch;
    private Button buttonDelete;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_recipe);

        editTextName = findViewById(R.id.text_view_recipe_name);
        imageViewRecipe = findViewById(R.id.image_view_recipe);
        buttonSearch = findViewById(R.id.button_search);
        buttonDelete = findViewById(R.id.button_delete);

        databaseHelper = new DatabaseHelper(this);

        buttonSearch.setOnClickListener(view -> searchRecipe());
        buttonDelete.setOnClickListener(view -> deleteRecipe());
    }

    // Search recipe by name
    private void searchRecipe() {
        String recipeName = editTextName.getText().toString().trim();

        if (recipeName.isEmpty()) {
            Toast.makeText(this, "Please enter a recipe name to search", Toast.LENGTH_SHORT).show();
            return;
        }

        // Query the database for the recipe by name
        Cursor cursor = databaseHelper.getRecipeByName(recipeName);

        if (cursor != null && cursor.moveToFirst()) {
            // Extract image data from the cursor
            byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_IMAGE));

            // Display the image if found
            if (image != null) {
                imageViewRecipe.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
            }

            cursor.close();
        } else {
            Toast.makeText(this, "Recipe not found", Toast.LENGTH_SHORT).show();
        }
    }

    // Delete the recipe
    private void deleteRecipe() {
        String recipeName = editTextName.getText().toString().trim();

        if (recipeName.isEmpty()) {
            Toast.makeText(this, "Please enter a recipe name to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        // Attempt to delete the recipe from the database
        boolean isDeleted = databaseHelper.deleteRecipe(recipeName);

        if (isDeleted) {
            Toast.makeText(this, "Recipe deleted successfully", Toast.LENGTH_SHORT).show();
            editTextName.setText("");  // Clear the input after deletion
            imageViewRecipe.setImageDrawable(getResources().getDrawable(R.drawable.ser_img5));  // Reset image
        } else {
            Toast.makeText(this, "Failed to delete recipe", Toast.LENGTH_SHORT).show();
        }
    }
}
