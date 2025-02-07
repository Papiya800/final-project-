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

    public RecipeAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.recipe_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView recipeNameTextView = view.findViewById(R.id.recipe_name);
        ImageView recipeImageView = view.findViewById(R.id.recipe_image);

        // Retrieve the data from the cursor
        String recipeName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_NAME));
        byte[] imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_IMAGE));

        // Set the data to the TextView
        recipeNameTextView.setText(recipeName);

        // ImageBytes null
        if (imageBytes != null && imageBytes.length > 0) {
            // Convert byte[] to Bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            recipeImageView.setImageBitmap(bitmap);
        } else {

            recipeImageView.setImageResource(R.drawable.placeholder_image);
        }
    }
}
