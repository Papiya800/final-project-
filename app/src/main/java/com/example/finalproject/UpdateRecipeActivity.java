package com.example.finalproject;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UpdateRecipeActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextName;
    private ImageView imageViewrecipe;
    private Button buttonUpdate;
    private Button buttonSelectImage;
    private Button buttonSearch;


    private DatabaseHelper databaseHelper;
    private byte[] recipeImageByteArray;
    private View imageviewrecipe;
    private String recipe_name;
    private byte[] image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipe);

        editTextName = findViewById(R.id.edit_text_recipe_name);
        imageviewrecipe = findViewById(R.id.image_view_recipe);
        buttonUpdate = findViewById(R.id.button_update);
        buttonSelectImage = findViewById(R.id.button_select_image);
        buttonSearch = findViewById(R.id.button_search);


        databaseHelper = new DatabaseHelper(this);

        buttonSearch.setOnClickListener(view -> searchProduct(image));
        buttonSelectImage.setOnClickListener(view -> selectImage());
        buttonUpdate.setOnClickListener(view -> updateProduct());
    }

    private void searchProduct(byte[] image) {
        String recipeName = editTextName.getText().toString().trim();
        if (recipeName.isEmpty()) {
            Toast.makeText(this, "Please enter a recipe name to search", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor cursor = databaseHelper.getRecipeByName(recipe_name);

        if (cursor != null && cursor.moveToFirst())
        {
            if (null != image) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageViewrecipe.setImageBitmap(bitmap);
                recipeImageByteArray = image;
            }
            cursor.close();
        }
        else
        {
            Toast.makeText(this, "Recipe not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageViewrecipe.setImageBitmap(bitmap);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                recipeImageByteArray = byteArrayOutputStream.toByteArray();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateProduct() {

        String tecName = editTextName.getText().toString().trim();


        if (tecName.isEmpty())
        {
            Toast.makeText(this, "Please give recipe name", Toast.LENGTH_SHORT).show();
            return;
        }



    }
}