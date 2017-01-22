package com.example.copynotesdemo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.copynotesdemo.db.NoteSQLiteOpenHelper;
import com.example.copynotesdemo.model.NoteEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Hilary on 2016/11/24.
 * new Note
 */

public class NewNoteActivity extends AppCompatActivity {
    private EditText mEditText;
    private NoteEntity mEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            mEntity = getIntent().getParcelableExtra("NOTE");
        }
        initViwe();
    }

    private void initViwe() {
        FrameLayout frameLayout = new FrameLayout(this);
        setContentView(frameLayout);

        ImageView setBgImage = new ImageView(this);
        setBgImage.setImageResource(R.mipmap.bg_btn_set_color);
        setBgImage.setBackgroundColor(0x22ff0000);
        FrameLayout.LayoutParams setBgParams = new FrameLayout.LayoutParams(AndroidUtils.dp2px(this, 40), AndroidUtils.dp2px(this, 40));
        setBgParams.setMargins(8, 8, 0, 8);
        setBgParams.gravity = Gravity.START | Gravity.TOP;
        frameLayout.addView(setBgImage, setBgParams);
        setBgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView timeTv = new TextView(this);
        final String time;
        if (mEntity == null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:dd");
            time = format.format(Calendar.getInstance().getTime());
        } else {
            time = mEntity.getTime();
        }
        timeTv.setText(time);
        timeTv.setTextColor(0xff000000);
        timeTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        timeTv.setBackgroundColor(0x22ff0000);
        timeTv.setGravity(Gravity.CENTER_VERTICAL);
        FrameLayout.LayoutParams timeParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AndroidUtils.dp2px(this, 56));
        timeParams.setMargins(AndroidUtils.dp2px(this, 56), 0, AndroidUtils.dp2px(this, 56), 0);
        timeParams.gravity = Gravity.START | Gravity.TOP;
        frameLayout.addView(timeTv, timeParams);

        ImageView finishImage = new ImageView(this);
        finishImage.setImageResource(R.mipmap.ic_done_grey600_24dp);
        finishImage.setBackgroundColor(0x22ff0000);
        finishImage.setColorFilter(0xff66ff99);
        FrameLayout.LayoutParams finishParams = new FrameLayout.LayoutParams(AndroidUtils.dp2px(this, 40), AndroidUtils.dp2px(this, 40));
        finishParams.setMargins(0, 8, 8, 8);
        finishParams.gravity = Gravity.END | Gravity.TOP;
        frameLayout.addView(finishImage, finishParams);
        finishImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mess = mEditText.getText().toString();
                if (mess.equals("")) {
                    Toast.makeText(NewNoteActivity.this, "输入内容不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    NoteEntity entity = new NoteEntity(0, 0X22FF0000, System.currentTimeMillis(), mess);
//                NoteSQLiteOpenHelper sqLiteOpenHelper = new NoteSQLiteOpenHelper(NewNoteActivity.this);
                    ContentValues values = new ContentValues();
                    values.put(NoteEntity.ID, (byte[]) null);
                    values.put(NoteEntity.TYPE, entity.getType());
                    values.put(NoteEntity.BG_COLOR_ID, entity.getBgColorId());
                    values.put(NoteEntity.CREATE_DATE, entity.getCreateData());
                    values.put(NoteEntity.MESS, entity.getMess());
                    ContentResolver resolver = getContentResolver();
                    resolver.insert(NoteEntity.CONTENT_NOTE_URI, values);
//                NoteDBHelper helper = new NoteDBHelper(NewNoteActivity.this);
//                helper.insertNote(entity);
//                setResult(RESULT_OK, null);
                    finish();
                }
            }
        });

        ScrollView scrollView = new ScrollView(this);
        scrollView.setBackgroundColor(0x22ff0000);
        FrameLayout.LayoutParams scrollParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        scrollParams.setMargins(0, AndroidUtils.dp2px(this, 56), 0, 0);
        frameLayout.addView(scrollView, scrollParams);

        mEditText = new EditText(this);
        if (mEntity == null) {
            mEditText.setHint("输入便签内容");
        } else {
            mEditText.setText(mEntity.getMess());
        }
        mEditText.setBackgroundColor(0xffffffff);
        mEditText.setGravity(Gravity.START);
        mEditText.setFocusable(true);
        mEditText.setPadding(16, 8, 16, 8);
        mEditText.setMinHeight(AndroidUtils.dp2px(this, 120));
//        mEditText.setBackground(null);
        FrameLayout.LayoutParams editParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        editParams.setMargins(AndroidUtils.dp2px(this, 16), AndroidUtils.dp2px(this, 48), AndroidUtils.dp2px(this, 16), AndroidUtils.dp2px(this, 16));
        scrollView.addView(mEditText, editParams);

    }

}
