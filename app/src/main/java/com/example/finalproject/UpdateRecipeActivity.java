package com.example.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UpdateRecipeActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextName;
    private ImageView imageViewRecipe;
    private Button buttonSelectImage;
    private Button buttonSearch;
    private Button buttonUpdate;

    private DatabaseHelper databaseHelper;
    private byte[] recipeImageByteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipe);

        // Initialize the views
        editTextName = findViewById(R.id.edit_text_recipe_name);
        imageViewRecipe = findViewById(R.id.image_view_recipe);
        buttonSelectImage = findViewById(R.id.button_select_image);
        buttonSearch = findViewById(R.id.button_search);
        buttonUpdate = findViewById(R.id.button_update);

        databaseHelper = new DatabaseHelper(this);

        // Set button actions
        buttonSearch.setOnClickListener(view -> searchRecipe());
        buttonSelectImage.setOnClickListener(view -> selectImage());
        buttonUpdate.setOnClickListener(view -> updateRecipe());
    }

    // Search for a recipe by name
    private void searchRecipe() {
        String recipeName = editTextName.getText().toString().trim();

        if (recipeName.isEmpty()) {
            Toast.makeText(this, "Please enter a recipe name to search", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor cursor = databaseHelper.getRecipeByName(recipeName);

        if (cursor != null && cursor.moveToFirst()) {
            // Fetch recipe data
            byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_IMAGE));

            // Set the image to the ImageView
            if (image != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageViewRecipe.setImageBitmap(bitmap);
                recipeImageByteArray = image;
            }

            cursor.close();
        } else {
            Toast.makeText(this, "Recipe not found", Toast.LENGTH_SHORT).show();
        }
    }

    // Select an image for the recipe
    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                // Get the image URI and convert it to bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                imageViewRecipe.setImageBitmap(bitmap);

                // Convert bitmap to byte array
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                recipeImageByteArray = byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Update the recipe details
    private void updateRecipe() {
        String recipeName = editTextName.getText().toString().trim();

        if (recipeName.isEmpty()) {
            Toast.makeText(this, "Please enter a recipe name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Call the method to update the recipe in the database
        boolean isUpdated = databaseHelper.updateRecipe(recipeName, recipeImageByteArray);

        if (isUpdated) {
            Toast.makeText(this, "Recipe updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to update recipe", Toast.LENGTH_SHORT).show();
        }
    }
}
