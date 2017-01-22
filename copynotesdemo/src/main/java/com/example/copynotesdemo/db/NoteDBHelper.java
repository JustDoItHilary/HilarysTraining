//package com.example.copynotesdemo.db;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import com.example.copynotesdemo.model.NoteEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Hilary on 2016/11/24.
// * sql helper
// */
//
//public class NoteDBHelper {
//    private static final String TAG = "NoteDBHelper";
//    private static final String DB_NAME = "note.db";
//    private static SQLiteOpenHelper mHelper;
//
//    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + NoteEntity.TABLE_NAME + "(" +
//            NoteEntity.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//            NoteEntity.TYPE + " INTEGER NOT NULL DEFAULT 0," +
//            NoteEntity.BG_COLOR_ID + " INTEGER NOT NULL DEFAULT 0," +
//            NoteEntity.CREATE_DATE + " LONG," +
//            NoteEntity.MESS + " TEXT"
//            + ");";
//
//    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NoteEntity.TABLE_NAME;
//
//    public NoteDBHelper(Context context) {
//        if (mHelper == null) {
//            mHelper = new SQLiteOpenHelper(context, DB_NAME, null, 1) {
//                @Override
//                public void onCreate(SQLiteDatabase db) {
//                    createNoteTable(db);
//                }
//
//                @Override
//                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//                }
//            };
//        }
//    }
//
//    private static void createNoteTable(SQLiteDatabase db) {
//        db.execSQL(CREATE_TABLE);
//        Log.d(TAG, "note table has been created");
//    }
//
//    public void dropNoteTable() {
//        mHelper.getWritableDatabase().execSQL(DROP_TABLE);
//    }
//
//    public void deleteNoteTable() {
//        mHelper.getWritableDatabase().delete(NoteEntity.TABLE_NAME, null, null);
//    }
//
//
//    public List<NoteEntity> queryNote() {
//        SQLiteDatabase db = mHelper.getReadableDatabase();
//        Cursor cursor;
//        List<NoteEntity> noteEntityList = new ArrayList<>();
//        cursor = db.query(NoteEntity.TABLE_NAME, null, null, null, null, null, null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast() && (cursor.getString(1) != null)) {
//            noteEntityList.add(bindValues(cursor));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return noteEntityList;
//    }
//
//    private NoteEntity bindValues(Cursor cursor) {
//        NoteEntity entity = new NoteEntity();
//        entity.setId(cursor.getInt(NoteEntity.INDEX_ID));
//        entity.setType(cursor.getInt(NoteEntity.INDEX_TYPE));
//        entity.setBgColorId(cursor.getInt(NoteEntity.INDEX_BG_COLOR));
//        entity.setCreateData(cursor.getInt(NoteEntity.INDEX_CREATE_DATE));
//        entity.setMess(cursor.getString(NoteEntity.INDEX_MESS));
//        return entity;
//    }
//
//    public void insertNote(NoteEntity entity) {
//        SQLiteDatabase db = mHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(NoteEntity.ID, (byte[]) null);
//        values.put(NoteEntity.TYPE, entity.getType());
//        values.put(NoteEntity.BG_COLOR_ID, entity.getBgColorId());
//        values.put(NoteEntity.CREATE_DATE, entity.getCreateData());
//        values.put(NoteEntity.MESS, entity.getMess());
//        //返回插入的条目数量
//        long id = db.insert(NoteEntity.TABLE_NAME, NoteEntity.ID, values);
//    }
//
//
//}
