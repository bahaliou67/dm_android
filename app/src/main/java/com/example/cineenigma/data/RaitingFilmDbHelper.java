package com.example.cineenigma.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RaitingFilmDbHelper extends SQLiteOpenHelper {
    // The database name
    private static final String DATABASE_NAME = "raitingfilm.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public RaitingFilmDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create a table to hold raitingfilm data
        final String SQL_CREATE_RAITING_FILM_TABLE = "CREATE TABLE " + RaitingFilmContract.RaitingFilmEntry.TABLE_NAME + " (" +
                RaitingFilmContract.RaitingFilmEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                RaitingFilmContract.RaitingFilmEntry.COLUMN_film_title + " TEXT NOT NULL, " +
                RaitingFilmContract.RaitingFilmEntry.COLUMN_film_username + " TEXT NOT NULL, " +
                RaitingFilmContract.RaitingFilmEntry.COLUMN_film_date + " TEXT NOT NULL, " +
                RaitingFilmContract.RaitingFilmEntry.COLUMN_film_scenario + " INTEGER NOT NULL, " +
                RaitingFilmContract.RaitingFilmEntry.COLUMN_film_realisation + " INTEGER NOT NULL, " +
                RaitingFilmContract.RaitingFilmEntry.COLUMN_film_music + " INTEGER NOT NULL, " +
                RaitingFilmContract.RaitingFilmEntry.COLUMN_film_commentaire + " TEXT NOT NULL " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_RAITING_FILM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RaitingFilmContract.RaitingFilmEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
