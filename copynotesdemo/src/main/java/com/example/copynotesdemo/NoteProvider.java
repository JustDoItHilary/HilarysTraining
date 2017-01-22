//package com.example.copynotesdemo;
//
//import android.content.ContentProvider;
//import android.content.ContentValues;
//import android.content.UriMatcher;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.net.Uri;
//import android.support.annotation.Nullable;
//
//import com.example.copynotesdemo.db.NoteSQLiteOpenHelper;
//import com.example.copynotesdemo.model.NoteEntity;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
///**
// * Created by Hilary on 2016/11/24.
// * contentProvider
// */
//
//public class NoteProvider extends ContentProvider {
//    private static final String DB_NAME = "note.db";
//    private SQLiteOpenHelper mHelper;
//    private static final UriMatcher mMatcher;
//    private static final String authority = "note_authority";
//    private static final String TAG = "NoteProvider";
//    private static final int URI_NOTE = 0;
//    private static final int URI_NOTE_ITEM = 1;
//
//    static {
//        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//        mMatcher.addURI(authority, "note", URI_NOTE);
//        mMatcher.addURI(authority, "note/#", URI_NOTE_ITEM);
//    }
//
//    @Override
//    public boolean onCreate() {
//        mHelper= NoteSQLiteOpenHelper.getInstance(getContext());
////        mHelper = new NoteDBHelper(getContext());
//        return false;
//    }
//
//    @Nullable
//    @Override
//    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
//        SQLiteDatabase db = mHelper.getReadableDatabase();
//        Cursor cursor;
//        switch (mMatcher.match(uri)) {
//            case URI_NOTE:
//                List<NoteEntity> noteEntityList = new ArrayList<>();
//                cursor = db.query(NoteEntity.TABLE_NAME, null, null, null, null, null, NoteEntity.ID + "DESC");
//                cursor.moveToFirst();
//                while (!cursor.isAfterLast() && (cursor.getString(1) != null)) {
//
////                    UserInfo user = new UserInfo();
////                    user.setId(cursor.getString(0));
////                    user.setUserId(cursor.getString(1));
////                    user.setToken(cursor.getString(2));
////                    user.setTokenSecret(cursor.getString(3));
////                    if (!isSimple) {
////                        user.setUserName(cursor.getString(4));
////                        ByteArrayInputStream stream = new ByteArrayInputStream(cursor.getBlob(5));
////                        Drawable icon = Drawable.createFromStream(stream, "image");
////                        user.setUserIcon(icon);
////                    }
//                    noteEntityList.add(bindValues(cursor));
//                    cursor.moveToNext();
//                }
//                cursor.close();
//                break;
//            case URI_NOTE_ITEM:
//                break;
//            default:
//                break;
//        }
//        return null;
//    }
//
//    private NoteEntity bindValues(Cursor cursor) {
//        NoteEntity entity = new NoteEntity();
//        entity.setId(cursor.getInt(0));
//        entity.setType(cursor.getInt(1));
//        entity.setBgColorId(cursor.getInt(2));
//        entity.setCreateData(cursor.getInt(3));
//        entity.setMess(cursor.getString(4));
//        return entity;
//    }
//
//    public void insertNote(NoteEntity entity) {
//        ContentValues values = new ContentValues();
//        values.put(NoteEntity.ID, entity.getId());
//        values.put(NoteEntity.TYPE, entity.getType());
//        values.put(NoteEntity.BG_COLOR_ID, entity.getBgColorId());
//        values.put(NoteEntity.CREATE_DATE, entity.getCreateData());
//        values.put(NoteEntity.MESS, entity.getMess());
//
//    }
//
//    @Nullable
//    @Override
//    public String getType(Uri uri) {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public Uri insert(Uri uri, ContentValues values) {
//        SQLiteDatabase db=mHelper.getWritableDatabase();
//        db.insert(NoteEntity.TABLE_NAME,NoteEntity.ID,values);
//        return null;
//    }
//
//    @Override
//    public int delete(Uri uri, String selection, String[] selectionArgs) {
//        return 0;
//    }
//
//    @Override
//    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
//        return 0;
//    }
//}
