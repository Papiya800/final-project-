package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String COLUMN_RECIPE_NAME = "recipe_name";
    public static final String COLUMN_RECIPE_IMAGE = "recipe_image";
    private static final String DATABASE_NAME = "UserDatabase";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_RECIPES = "recipes";
    private static final String COLUMN_RECIPE_ID = "recipe_id";
    private static final String COLUMN_RECIPE_DESCRIPTION = "recipe_description";
    private static final String COLUMN_RECIPE_INGREDIENTS = "ingredients";
    private static final String COLUMN_RECIPE_STEPS = "steps";
    public static String COLUMN_RECIPE_COST;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create recipes table
        String CREATE_RECIPES_TABLE = "CREATE TABLE " + TABLE_RECIPES + "("
                + COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_RECIPE_NAME + " TEXT,"
                + COLUMN_RECIPE_DESCRIPTION + " TEXT,"
                + COLUMN_RECIPE_INGREDIENTS + " TEXT,"
                + COLUMN_RECIPE_STEPS + " TEXT,"
                + COLUMN_RECIPE_IMAGE + " BLOB" + ")"; // Added image column
        db.execSQL(CREATE_RECIPES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old recipes table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    // Insert new recipe
    public boolean insertRecipe(String name, String description, String ingredients, String steps, byte[] imageByteArray) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPE_NAME, name);
        values.put(COLUMN_RECIPE_DESCRIPTION, description);
        values.put(COLUMN_RECIPE_INGREDIENTS, ingredients);
        values.put(COLUMN_RECIPE_STEPS, steps);
        values.put(COLUMN_RECIPE_IMAGE, imageByteArray); // Store image

        long result = db.insert(TABLE_RECIPES, null, values);
        db.close();
        return result != -1; // Return true if inserted successfully
    }

    // Get recipe by name
    public Cursor getRecipeByName(String recipeName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_RECIPES,
                null,
                COLUMN_RECIPE_NAME + " = ?",
                new String[]{recipeName},
                null,
                null,
                null);
    }

    // Delete recipe by name
    public int deleteRecipe(String recipeName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_RECIPES,
                COLUMN_RECIPE_NAME + " = ?",
                new String[]{recipeName});
    }

    // Get all recipes
    public Cursor getAllRecipes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_RECIPES,
                null, // Get all columns
                null, // No WHERE clause
                null, // No WHERE arguments
                null, // No GROUP BY
                null, // No HAVING
                null); // No ORDER BY
    }
}