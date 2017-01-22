package com.example.copynotesdemo.adapter;


import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.copynotesdemo.MainActivity;
import com.example.copynotesdemo.model.NoteEntity;
import com.example.copynotesdemo.view.TextAndCheckCell;

import java.util.ArrayList;

/**
 * Created by Hilary on 2016/11/26.
 */

public class NoteCursorAdapter extends CursorAdapter {
    private static final String TAG = "NoteCursorAdapter";
    private int mNotesCount;

    public NoteCursorAdapter(Context context) {
        super(context, null);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return new TextAndCheckCell(context);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        if (view instanceof TextAndCheckCell) {
            TextAndCheckCell cell = (TextAndCheckCell) view;
            final NoteEntity entity = new NoteEntity(context, cursor);
            cell.setData(entity.getMess(), entity.getTime(), 0, true);
            cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("确定删除？")
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ContentResolver resolver = context.getContentResolver();
                                    //对数据库的操作有两种方式：
                                    // 1. 直接调用 resolver 的方法操作；
                                    // 2. 调用 ContentProviderOperation.Builder 提供的方法（newInsert,newDelete...）
                                    //1. 调用 resolver 的方法，设置 selection 和 selectionArgs(过滤条件)
//                                    resolver.delete(NoteEntity.CONTENT_NOTE_URI, NoteEntity.ID + "=?", new String[]{String.valueOf(entity.getId())});

                                    //2. 调用 resolver 的方法，ContentUris 调用 withAppendedId() 方法将 id 拼接上，在 ContentProvider 中需要将 id 取出放到 where 语句中
                                    resolver.delete(ContentUris.withAppendedId(NoteEntity.CONTENT_NOTE_URI,entity.getId()),null,null);

                                    //3. 调用 ContentProviderOperation.Builder 提供的方法
//                                    ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
                                    //拼接 id 的方式
////                                    ContentProviderOperation.Builder builder = ContentProviderOperation
////                                            .newDelete(ContentUris.withAppendedId(NoteEntity.CONTENT_NOTE_URI, entity.getId()));
                                    //直接设置过滤条件
//                                    ContentProviderOperation.Builder builder = ContentProviderOperation
//                                            .newDelete(NoteEntity.CONTENT_NOTE_URI)
//                                            .withSelection(NoteEntity.ID + "=?", new String[]{String.valueOf(entity.getId())});
//                                    operationList.add(builder.build());
//                                    try {
//                                        ContentProviderResult[] results = resolver.applyBatch(NoteEntity.AUTHORITY, operationList);
//                                        notifyDataSetChanged();
//                                        Log.e(TAG, "onClick: results" + results);
//                                    } catch (RemoteException e) {
//                                        e.printStackTrace();
//                                    } catch (OperationApplicationException e) {
//                                        e.printStackTrace();
//                                    }
                                }
                            });
                    builder.show();

                }
            });
        }
    }

    @Override
    protected void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    public void changeCursor(Cursor cursor) {
        super.changeCursor(cursor);
        calcNotesCount();
    }

    private void calcNotesCount() {
        mNotesCount = 0;
        for (int i = 0; i < getCount(); i++) {
            Log.e(TAG, "calcNotesCount: count" + getCount());
            Cursor c = (Cursor) getItem(i);
            if (c != null) {
                mNotesCount++;
            } else {
                Log.e(TAG, "Invalid cursor");
                return;
            }
        }
    }
}
