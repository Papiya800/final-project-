package com.example.finalproject;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeAdapter extends CursorAdapter {

    // Constructor
    public RecipeAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags); // Use `flags` to specify behavior
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.recipe_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find views in the recipe_list_item layout
        ImageView recipeImageView = view.findViewById(R.id.image_view_recipe);
        TextView recipeNameTextView = view.findViewById(R.id.text_view_recipe_name); // Correct the TextView ID

        // Get data from the cursor
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TECH_NAME)); // Use COLUMN_TECH_NAME
        byte[] imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TECH_IMAGE));

        // Set recipe name
        recipeNameTextView.setText(name);

        // Decode the image byte array and set it to the ImageView
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        recipeImageView.setImageBitmap(bitmap);
    }
}
