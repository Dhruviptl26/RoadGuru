package com.example.gptlogin;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "loginDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_LOGIN_INFO = "loginInfo";
    private static final String TABLE_REGISTRATION_INFO = "registrationInfo";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMIRATES_ID = "emirates_id";

    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_DL = "dl";  // Driving license
    private static final String COLUMN_ADDRESS = "address";

    private static final String TABLE_LOGIN_CREATE =
            "CREATE TABLE " + TABLE_LOGIN_INFO + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EMIRATES_ID + " TEXT NOT NULL);";

    private static final String TABLE_REGISTRATION_CREATE =
            "CREATE TABLE " + TABLE_REGISTRATION_INFO + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_EMAIL + " TEXT NOT NULL, " +
                    COLUMN_DL + " TEXT NOT NULL, " +
                    COLUMN_ADDRESS + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create both tables: one for login, one for registration
        db.execSQL(TABLE_LOGIN_CREATE);
        db.execSQL(TABLE_REGISTRATION_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop tables if they exist and recreate them
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATION_INFO);
        onCreate(db);
    }

    // Method to add login info (only Emirates ID)
    public void addLoginInfo(String emiratesId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMIRATES_ID, emiratesId);
        db.insert(TABLE_LOGIN_INFO, null, values);
        db.close();
    }

    // Method to add registration info (Name, Email, DL, Address)
    public void addRegistrationInfo(String name, String email, String dl, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_DL, dl);
        values.put(COLUMN_ADDRESS, address);
        db.insert(TABLE_REGISTRATION_INFO, null, values);
        db.close();
    }

    // Method to retrieve the stored Emirates ID
    public String getPhoneNumber() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LOGIN_INFO, new String[]{COLUMN_EMIRATES_ID},
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String emiratesId = cursor.getString(cursor.getColumnIndex(COLUMN_EMIRATES_ID));
            cursor.close();
            return emiratesId;
        }
        return "";
    }
}
