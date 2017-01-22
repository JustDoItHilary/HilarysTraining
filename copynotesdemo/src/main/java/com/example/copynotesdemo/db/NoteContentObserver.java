package com.example.copynotesdemo.db;

import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;

/**
 * Created by Hilary
 * on 2016/11/30.
 */

public class NoteContentObserver extends ContentObserver {
    private Cursor mCursor;
    private Handler mHandler;
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     * @param cursor
     */
    public NoteContentObserver(Handler handler, Cursor cursor) {
        super(handler);
        this.mCursor=cursor;
        this.mHandler=handler;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        mCursor.requery();
        mHandler.sendEmptyMessage(0);
    }
}
