package com.example.mygardener.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mygardener.provider.PlantContract.PlantEntry;

public class PlantDbHelper extends SQLiteOpenHelper {

    // The database name
    private static final String DATABASE_NAME = "shushme.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public PlantDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create a table to hold the plants data
        final String SQL_CREATE_PLANTS_TABLE = "CREATE TABLE " + PlantContract.PlantEntry.TABLE_NAME + " (" +
                PlantContract.PlantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PlantContract.PlantEntry.COLUMN_PLANT_TYPE + " INTEGER NOT NULL, " +
                PlantContract.PlantEntry.COLUMN_CREATION_TIME + " TIMESTAMP NOT NULL, " +
                PlantContract.PlantEntry.COLUMN_LAST_WATERED_TIME + " TIMESTAMP NOT NULL)";

        sqLiteDatabase.execSQL(SQL_CREATE_PLANTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the table and create a new one.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PlantContract.PlantEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
