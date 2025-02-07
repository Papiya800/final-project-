package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Information
    public static final String DATABASE_NAME = "recipe_master.db";
    public static final int DATABASE_VERSION = 2;

    // Table Names
    public static final String TABLE_RECIPES = "recipes";

    // Common Column
    public static final String COLUMN_ID = "id";

    // Columns for Recipes Table
    public static final String COLUMN_RECIPE_NAME = "recipe_name";
    public static final String COLUMN_RECIPE_IMAGE = "recipe_image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Recipes Table
        String CREATE_RECIPES_TABLE = "CREATE TABLE " + TABLE_RECIPES + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_RECIPE_NAME + " TEXT,"
                + COLUMN_RECIPE_IMAGE + " BLOB)";
        db.execSQL(CREATE_RECIPES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    // Insert Recipe into Database
    public boolean insertRecipe(String recipeName, byte[] recipeImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPE_NAME, recipeName);
        values.put(COLUMN_RECIPE_IMAGE, recipeImage);

        long result = db.insert(TABLE_RECIPES, null, values); // Returns a row ID or -1 if failed
        db.close();

        return result != -1;
    }

    // Get All Recipes
    public Cursor getAllRecipes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_ID + " AS _id", COLUMN_RECIPE_NAME, COLUMN_RECIPE_IMAGE};
        return db.query(TABLE_RECIPES, projection, null, null, null, null, null);
    }

    // Get Recipe by Name
    public Cursor getRecipeByName(String recipeName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_RECIPES, null, COLUMN_RECIPE_NAME + "=?", new String[]{recipeName}, null, null, null);
    }

    // Update Recipe Image by Name
    public boolean updateRecipe(String recipeName, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_RECIPE_IMAGE, image);

        int rowsUpdated = db.update(TABLE_RECIPES, contentValues, COLUMN_RECIPE_NAME + "=?", new String[]{recipeName});

        return rowsUpdated > 0;
    }

    // Delete Recipe by Name
    public boolean deleteRecipe(String recipeName) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_RECIPES, COLUMN_RECIPE_NAME + "=?", new String[]{recipeName});
        return rowsDeleted > 0;
    }
}
