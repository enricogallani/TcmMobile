package com.example.front.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_USER =
            "CREATE TABLE " + DBContract.User.TABLE_NAME + " (" +
                    DBContract.User._ID + " INTEGER PRIMARY KEY," +
                    DBContract.User.COLUMN_NAME_NAME + " VARCHAR(255)," +
                    DBContract.User.COLUMN_NAME_PASSWORD + " VARCHAR(255)," +
                    DBContract.User.COLUMN_NAME_EMAIL + " VARCHAR(255))";

    private static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + DBContract.User.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_USER);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}