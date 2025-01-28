package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class InsertRecipeActivity extends AppCompatActivity {

    private EditText etRecipeName, etRecipeDescription, etIngredients, etSteps;
    private ImageView imgRecipeImage;
    private Button btnChooseImage, btnSaveRecipe;
    private DatabaseHelper databaseHelper;
    private byte[] imageByteArray;

    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_recipe);

        // Initialize UI elements
        etRecipeName = findViewById(R.id.et_recipe_name);
        etRecipeDescription = findViewById(R.id.et_recipe_description);
        etIngredients = findViewById(R.id.et_ingredients);
        etSteps = findViewById(R.id.et_steps);
        imgRecipeImage = findViewById(R.id.img_recipe_image);
        btnChooseImage = findViewById(R.id.btn_choose_image);
        btnSaveRecipe = findViewById(R.id.btn_save_recipe);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Set up Image Picker
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                            imgRecipeImage.setImageBitmap(imageBitmap);
                            imageByteArray = bitmapToByteArray(imageBitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        // Choose Image Button Click Listener
        btnChooseImage.setOnClickListener(v -> chooseImage());

        // Save Recipe Button Click Listener
        btnSaveRecipe.setOnClickListener(v -> saveRecipe());
    }

    // Launch the image picker
    private void chooseImage() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        imagePickerLauncher.launch(pickIntent);
    }

    // Convert Bitmap to byte array
    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    // Save Recipe into the database
    private void saveRecipe() {
        String recipeName = etRecipeName.getText().toString().trim();
        String recipeDescription = etRecipeDescription.getText().toString().trim();
        String ingredients = etIngredients.getText().toString().trim();
        String steps = etSteps.getText().toString().trim();

        if (recipeName.isEmpty() || recipeDescription.isEmpty() || ingredients.isEmpty() || steps.isEmpty() || imageByteArray == null) {
            Toast.makeText(this, "Please fill all fields and select an image!", Toast.LENGTH_SHORT).show();
        } else {
            boolean isInserted = databaseHelper.insertRecipe(recipeName, recipeDescription, ingredients, steps, imageByteArray);
            if (isInserted) {
                Toast.makeText(this, "Recipe Saved Successfully!", Toast.LENGTH_LONG).show();

                // Clear fields after saving
                etRecipeName.setText("");
                etRecipeDescription.setText("");
                etIngredients.setText("");
                etSteps.setText("");
                imgRecipeImage.setImageResource(android.R.color.transparent);
            } else {
                Toast.makeText(this, "Error saving recipe!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}