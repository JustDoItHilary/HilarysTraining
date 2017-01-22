package com.example.copynotesdemo.model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Hilary on 2016/11/24.
 */

public class NoteEntity implements Parcelable {
    public static final String AUTHORITY = "authority";
    public static final Uri CONTENT_NOTE_URI = Uri.parse("content://" + AUTHORITY + "/note");

    public static final String TABLE_NAME = "note";
    public static final String ID = "_id";
    public static final String TYPE = "note_type";
    public static final String BG_COLOR_ID = "note_bg";
    public static final String CREATE_DATE = "create_date";
    public static final String MESS = "mess";

    public static final int INDEX_ID = 0;
    public static final int INDEX_TYPE = 1;
    public static final int INDEX_BG_COLOR = 2;
    public static final int INDEX_CREATE_DATE = 3;
    public static final int INDEX_MESS = 4;

    private String mess;
    private int id;
    private int bgColorId;
    private long createData;
    private int type;
    private String time;

    public NoteEntity(int i, int i1, long time, String mess) {
        this.type = i;
        this.bgColorId = i1;
        this.createData = time;
        this.mess = mess;
    }

    public NoteEntity() {

    }

    public NoteEntity(String s, String time) {
        this.mess = s;
        setTime(time);
    }

    protected NoteEntity(Parcel in) {
        mess = in.readString();
        id = in.readInt();
        bgColorId = in.readInt();
        createData = in.readLong();
        type = in.readInt();
        time = in.readString();
    }

    public static final Creator<NoteEntity> CREATOR = new Creator<NoteEntity>() {
        @Override
        public NoteEntity createFromParcel(Parcel in) {
            return new NoteEntity(in);
        }

        @Override
        public NoteEntity[] newArray(int size) {
            return new NoteEntity[size];
        }
    };

    public NoteEntity(Context context, Cursor cursor) {
        this.id = cursor.getInt(INDEX_ID);
        this.type = cursor.getInt(INDEX_TYPE);
        this.bgColorId = cursor.getInt(INDEX_BG_COLOR);
        this.createData = cursor.getLong(INDEX_CREATE_DATE);
        this.mess = cursor.getString(INDEX_MESS);
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getCreateData() {
        return createData;
    }

    public void setCreateData(long createData) {
        this.createData = createData;
        Date date = Calendar.getInstance().getTime();
        date.setTime(createData);
        this.time = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBgColorId() {
        return bgColorId;
    }

    public void setBgColorId(int bgColorId) {
        this.bgColorId = bgColorId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        Date date = null;
        try {
            date = (new SimpleDateFormat("yyyy/MM/dd HH:mm")).parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        this.createData = date.getTime();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mess);
        dest.writeInt(id);
        dest.writeInt(bgColorId);
        dest.writeLong(createData);
        dest.writeInt(type);
        dest.writeString(time);
    }
}
