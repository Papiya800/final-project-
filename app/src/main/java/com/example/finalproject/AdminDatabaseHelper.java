package com.example.finalproject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminDatabaseHelper extends SQLiteOpenHelper {
    // Database Information
    public static final String DATABASE_NAME = "admin_management.db";
    public static final int DATABASE_VERSION = 1;

    // Table and Columns
    public static final String TABLE_ADMINS = "admins";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public AdminDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Admins Table
        String CREATE_ADMINS_TABLE = "CREATE TABLE " + TABLE_ADMINS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(CREATE_ADMINS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINS);
        onCreate(db);
    }

    // Insert Admin
    public boolean insertAdmin(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_ADMINS, null, values);
        db.close();
        return result != -1;
    }

    // Get All Admins
    public Cursor getAllAdmins() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ADMINS, null);
    }

    // Update Admin
    public boolean updateAdmin(int id, String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        int rows = db.update(TABLE_ADMINS, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rows > 0;
    }

    // Delete Admin
    public boolean deleteAdmin(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_ADMINS, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rows > 0;
    }

    public boolean insertUser(String username, String givePassword, String userPhoneNum) {
        return false;
    }

    public boolean onCreate(String userPhoneNum) {
        return false;
    }
}
