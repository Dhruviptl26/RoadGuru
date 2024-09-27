package com.example.gptlogin;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "loginDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "loginInfo";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMIRATES_ID = "emirates_id";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EMIRATES_ID + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void addLoginInfo(String emiratesId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMIRATES_ID, emiratesId);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public String getPhoneNumber() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_EMIRATES_ID},
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String emiratesId = cursor.getString(cursor.getColumnIndex(COLUMN_EMIRATES_ID));
            cursor.close();
            return emiratesId;
        }
        return "";
    }
}
