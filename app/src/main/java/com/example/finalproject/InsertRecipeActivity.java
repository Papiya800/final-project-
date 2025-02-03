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

    private ImageView imgRecipeImage;
    private EditText etRecipeName;
    private Button btnChooseImage;
    private Button btnInsertRecipe;

    private DatabaseHelper databaseHelper;
    private byte[] imageByteArray;

    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_recipe);

        imgRecipeImage = findViewById(R.id.img_image);
        etRecipeName = findViewById(R.id.et_recipe_name);
        btnChooseImage = findViewById(R.id.bt_choose_img);
        btnInsertRecipe = findViewById(R.id.bt_inset_img);

        databaseHelper = new DatabaseHelper(this);

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
                            Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        btnChooseImage.setOnClickListener(view -> chooseImage());
        btnInsertRecipe.setOnClickListener(view -> insertRecipe());
    }

    private void chooseImage() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        imagePickerLauncher.launch(pickIntent);
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void insertRecipe() {
        String recipeName = etRecipeName.getText().toString().trim();

        if (recipeName.isEmpty()) {
            Toast.makeText(this, "Please enter a recipe name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (imageByteArray == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isInserted = databaseHelper.insertRecipe(recipeName, imageByteArray);

        if (isInserted) {
            Toast.makeText(this, "Recipe inserted successfully!", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Insertion failed. Try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        etRecipeName.setText("");
        imgRecipeImage.setImageResource(R.drawable.placeholder_image); // Replace with your placeholder image
        imageByteArray = null;
    }
}
