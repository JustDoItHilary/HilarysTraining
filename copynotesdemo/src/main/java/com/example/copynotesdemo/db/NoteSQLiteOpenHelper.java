package com.example.copynotesdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.copynotesdemo.model.NoteEntity;

/**
 * Created by Hilary on 2016/11/26.
 */

public class NoteSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "note_data.db";
    private static final int DB_VERSION = 1;
    private static NoteSQLiteOpenHelper mInstance;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + NoteEntity.TABLE_NAME + "(" +
            NoteEntity.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NoteEntity.TYPE + " INTEGER NOT NULL DEFAULT 0," +
            NoteEntity.BG_COLOR_ID + " INTEGER NOT NULL DEFAULT 0," +
            NoteEntity.CREATE_DATE + " LONG," +
            NoteEntity.MESS + " TEXT"
            + ");";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NoteEntity.TABLE_NAME;

    public static synchronized NoteSQLiteOpenHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NoteSQLiteOpenHelper(context);
        }
        return mInstance;
    }

    private NoteSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
