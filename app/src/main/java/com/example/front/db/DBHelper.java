package com.example.front.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_USER =
            "CREATE TABLE " + DBContract.User.TABLE_NAME + " (" +
                    DBContract.User._ID + " INTEGER PRIMARY KEY," +
                    DBContract.User.COLUMN_NAME + " VARCHAR(255)," +
                    DBContract.User.COLUMN_OFFICE + " VARCHAR(255)," +
                    DBContract.User.COLUMN_PASSWORD + " VARCHAR(255)," +
                    DBContract.User.COLUMN_IS_ADM + " BOOLEAN," +
                    DBContract.User.COLUMN_EMAIL + " VARCHAR(255))";

    private static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + DBContract.User.TABLE_NAME;

    private static final String SQL_CREATE_CLIENT =
            "CREATE TABLE " + DBContract.Client.TABLE_NAME + " (" +
                    DBContract.Client._ID + " INTEGER PRIMARY KEY," +
                    DBContract.Client.COLUMN_NAME + " VARCHAR(255)," +
                    DBContract.Client.COLUMN_EMAIL + " VARCHAR(255)," +
                    DBContract.Client.COLUMN_CNPJ + " VARCHAR(255)," +
                    DBContract.Client.COLUMN_PHONE + " VARCHAR(255)," +
                    DBContract.Client.COLUMN_PAYMENT_METHOD + " VARCHAR(255)," +
                    DBContract.Client.COLUMN_LOCATION + " VARCHAR(255))";

    private static final String SQL_DELETE_CLIENT =
            "DROP TABLE IF EXISTS " + DBContract.Client.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "startup.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_CLIENT);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_USER);
        db.execSQL(SQL_DELETE_CLIENT);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}