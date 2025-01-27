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
    public RecipeAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0); // 0 for default flags, no need for the "flags" variable
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.recipe_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView tec_ImageView = view.findViewById(R.id.image_view_recipe);
        TextView nameTextView = view.findViewById(R.id.text_view_recipe_cost);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_NAME));
        byte[] imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPE_IMAGE));

        nameTextView.setText(name);

        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        tec_ImageView.setImageBitmap(bitmap);
    }
}
