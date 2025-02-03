package com.example.finalproject;

import android.annotation.SuppressLint;
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

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextName;
    private ImageView imageViewrecipe;
    private Button buttonDelete;
    private Button buttonSelectImage;
    private Button buttonSearch;


    private DatabaseHelper databaseHelper;
    private byte[] productImageByteArray;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_recipe);

        editTextName = findViewById(R.id.text_view_recipe_name);
        imageViewrecipe = findViewById(R.id.image_view_recipe);

        buttonDelete = findViewById(R.id.button_delete);
        buttonSearch = findViewById(R.id.button_search);


        databaseHelper = new DatabaseHelper(this);

        buttonSearch.setOnClickListener(view -> searchProduct());
        buttonDelete.setOnClickListener(view -> deleteProduct());
    }

    private void searchProduct()
    {
        String recipeName = editTextName.getText().toString().trim();
        if (recipeName.isEmpty())
        {
            Toast.makeText(this, "Please enter a recipe name to search", Toast.LENGTH_SHORT).show();
            return;
        }


        Cursor cursor = databaseHelper.getRecipeByName(recipeName);
        if (cursor != null && cursor.moveToFirst())
        {
            byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_IMAGE));


            if (image != null)
            {
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageViewrecipe.setImageBitmap(bitmap);
                productImageByteArray = image;
            }
            cursor.close();
        }
        else
        {
            Toast.makeText(this, "Technology not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteProduct()
    {
        String tecName = editTextName.getText().toString().trim();

       // databaseHelper.DeleteRecipe(recipeName);
    }

}