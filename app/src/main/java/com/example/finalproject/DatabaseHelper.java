package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "finalproject.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_TECHNOLOGIES = "recipe";
    public static final String TABLE_USERS = "users";

    // Common column
    public static final String COLUMN_ID = "id";

    // Columns for recipe table
    public static final String COLUMN_TECH_NAME = "recipe_name";
    public static final String COLUMN_TECH_IMAGE = "recipe_image";

    // Columns for users table
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_RECIPE_IMAGE ="image" ;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TECHNOLOGIES);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create users table
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_PHONE + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);

        // Create recipe table
        String CREATE_TECHNOLOGIES_TABLE = "CREATE TABLE " + TABLE_TECHNOLOGIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TECH_NAME + " TEXT,"
                + COLUMN_TECH_IMAGE + " BLOB)";
        db.execSQL(CREATE_TECHNOLOGIES_TABLE);
    }

    // Insert user into the database
    public boolean insertUser(String username, String password, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_PHONE, phone);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    // Check if a user exists by phone number
    public boolean checkUserExists(String phone) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_ID}, COLUMN_PHONE + "=?", new String[]{phone}, null, null, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return exists;
    }

    // Check user by phone and password for login
    public boolean checkUserByPhone(String userPhone, String userPassword) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_PHONE + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{userPhone, userPassword});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Insert recipe into the database
    public boolean insertRecipe(String recipeName, byte[] recipeImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TECH_NAME, recipeName);
        values.put(COLUMN_TECH_IMAGE, recipeImage);

        long result = db.insert(TABLE_TECHNOLOGIES, null, values);
        db.close();
        return result != -1;
    }

    // Get all recipes
    public Cursor getAllRecipes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_ID + " AS _id", COLUMN_TECH_NAME, COLUMN_TECH_IMAGE};
        return db.query(TABLE_TECHNOLOGIES, projection, null, null, null, null, null);
    }

    // Get a specific recipe by name
    public Cursor getRecipeByName(String recipeName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_TECHNOLOGIES + " WHERE " + COLUMN_TECH_NAME + " = ?", new String[]{recipeName});
    }

    // Update a recipe
    public void updateRecipe(int recipeId, String recipeName, byte[] recipeImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TECH_NAME, recipeName);
        values.put(COLUMN_TECH_IMAGE, recipeImage);

        db.update(TABLE_TECHNOLOGIES, values, COLUMN_ID + " = ?", new String[]{String.valueOf(recipeId)});
        db.close();
    }

    // Delete a recipe by name
    public void deleteRecipe(String recipeName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TECHNOLOGIES, COLUMN_TECH_NAME + " = ?", new String[]{recipeName});
        db.close();
    }
}
