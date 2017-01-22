package com.example.copynotesdemo.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.example.copynotesdemo.model.NoteEntity;

/**
 * Created by Hilary on 2016/11/26.
 */

public class NoteProvider extends ContentProvider {
    private static final String TAG = "NoteProvider";
    private static NoteSQLiteOpenHelper mHelper;
    private static UriMatcher mMatcher;
    private static final int CODE_URI_NOTE = 1;
    private static final int CODE_URI_NOTE_ITEM = 2;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(NoteEntity.AUTHORITY, NoteEntity.TABLE_NAME, CODE_URI_NOTE);
        mMatcher.addURI(NoteEntity.AUTHORITY, NoteEntity.TABLE_NAME + "/#", CODE_URI_NOTE_ITEM);
    }

    @Override
    public boolean onCreate() {
        mHelper = NoteSQLiteOpenHelper.getInstance(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (mMatcher.match(uri)) {
            case CODE_URI_NOTE:
                cursor = db.query(NoteEntity.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CODE_URI_NOTE_ITEM:
                long id = ContentUris.parseId(uri);
                cursor = db.query(NoteEntity.TABLE_NAME, projection, NoteEntity.ID + "=" + id + parseSelection(selection), selectionArgs, null, null, sortOrder);
                break;
            default:
                break;
        }
        if (cursor != null) {
//            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        long code = 0;
        switch (mMatcher.match(uri)) {
            case CODE_URI_NOTE:
                //code 指的是所在行的id
                code = db.insert(NoteEntity.TABLE_NAME, NoteEntity.ID, values);
                Log.e(TAG, "insert: code=" + code);
                break;
        }
        if (code > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return ContentUris.withAppendedId(uri, code);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int code = 0;
        switch (mMatcher.match(uri)) {
            case CODE_URI_NOTE:
                code = db.delete(NoteEntity.TABLE_NAME, selection, selectionArgs);
                break;
            case CODE_URI_NOTE_ITEM:
                long id = ContentUris.parseId(uri);
                code = db.delete(NoteEntity.TABLE_NAME, NoteEntity.ID + "=" + id + parseSelection(selection), selectionArgs);
                uri = NoteEntity.CONTENT_NOTE_URI;
        }
        if (code > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return code;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int code = 0;
        switch (mMatcher.match(uri)) {
            case CODE_URI_NOTE:
                code = db.update(NoteEntity.TABLE_NAME, values, selection, selectionArgs);
                break;
            case CODE_URI_NOTE_ITEM:
                long id = ContentUris.parseId(uri);
                code = db.update(NoteEntity.TABLE_NAME, values, NoteEntity.ID + "=" + id + parseSelection(selection), selectionArgs);
                break;

        }
        if (code > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return code;
    }

    private String parseSelection(String selection) {
        return (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : "");
    }
}
