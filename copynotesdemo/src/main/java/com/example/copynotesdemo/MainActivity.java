package com.example.copynotesdemo;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.copynotesdemo.db.NoteContentObserver;
import com.example.copynotesdemo.model.NoteEntity;
import com.example.copynotesdemo.adapter.NoteCursorAdapter;
import com.example.copynotesdemo.adapter.NoteRecyclerAdapter;


public class MainActivity extends AppCompatActivity {
    private NoteRecyclerAdapter mAdapter;
    private NoteCursorAdapter mCursorAdapter;
    private BackgroundQueryHandler mHandler;
    private NoteContentObserver mObserver;
    static final String[] NOTE_HANDLER_PROJECTION = new String[]{
            NoteEntity.ID,
            NoteEntity.TYPE,
            NoteEntity.BG_COLOR_ID,
            NoteEntity.CREATE_DATE,
            NoteEntity.MESS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mHandler = new BackgroundQueryHandler(this.getContentResolver());

        setContentView();
    }

    private void setContentView() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        setContentView(root);

        TextView title_barTv = new TextView(this);
        title_barTv.setTextSize(16);
        title_barTv.setTextColor(0xaa000000);
        title_barTv.setBackgroundResource(R.mipmap.title_bar_bg);
        title_barTv.setText("文件夹名字");
        title_barTv.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams titleBarTvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleBarTvParams.gravity = Gravity.CENTER_VERTICAL | Gravity.START;
        root.addView(title_barTv, titleBarTvParams);

        ListView listView = new ListView(this);
        listView.setBackgroundColor(0x22000000);
        mCursorAdapter = new NoteCursorAdapter(this);
        listView.setAdapter(mCursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
//                intent.putExtra("NOTE", );
//                startActivityForResult(intent, 0);
            }
        });
        root.addView(listView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0F));

        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setBackgroundColor(0x22ff0000);
        mAdapter = new NoteRecyclerAdapter(this, new NoteRecyclerAdapter.NoteOnClickListener() {
            @Override
            public void onClick(NoteEntity entity) {
                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                intent.putExtra("NOTE", entity);
                startActivityForResult(intent, 0);
            }
        });
        recyclerView.setAdapter(mAdapter);
        recyclerView.setVisibility(View.GONE);
        root.addView(recyclerView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0F));

        TextView addTv = new TextView(this);
        addTv.setBackgroundResource(R.drawable.add_note_bg_selector);
        root.addView(addTv);
        addTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.startQuery(0, null, NoteEntity.CONTENT_NOTE_URI, NOTE_HANDLER_PROJECTION, null, null, NoteEntity.ID + " DESC");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(mObserver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
//            NoteDBHelper helper = new NoteDBHelper(this);
//            mAdapter.addList(helper.queryNote());
        }
    }

    private final class BackgroundQueryHandler extends AsyncQueryHandler {
        public BackgroundQueryHandler(ContentResolver contentResolver) {
            super(contentResolver);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            mCursorAdapter.changeCursor(cursor);

            mObserver = new NoteContentObserver(mHandler, cursor);
            getContentResolver().registerContentObserver(NoteEntity.CONTENT_NOTE_URI, false, mObserver);
        }
    }

}
