package com.example.gra_memory.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gra_memory.data.db.tables.Photos;

/**
 * Created by lenovo on 09.02.2018.
 */

class DBHelper extends SQLiteOpenHelper {
    private final static int DB_VERSION = 2;
    private final static String DB_NAME = "AppDB.db";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "
                        + Photos.TABLE_NAME
                        + " ( "
                        + Photos.Columns.PHOTO_ID
                        + " integer primary key, "
                        + Photos.Columns.PHOTO_CATEGORY
                        + " text, "
                        + Photos.Columns.PHOTO_URI
                        + " text )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // kasujemy bazę
        db.execSQL("DROP TABLE IF EXISTS " + Photos.TABLE_NAME);
        // tworzymy nową bazę
        onCreate(db);
    }
}