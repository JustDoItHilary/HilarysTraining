package com.example.testrecyclerviewdemo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyVibrateActivity extends AppCompatActivity {
    private Switch mSwitch;
    private TextView mTv;
    private boolean mIsOpen;
    private Vibrator mVibrator;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = this;
        LayoutInflater inflater=LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.activity_divider_layout,null);
        float x = getResources().getDisplayMetrics().xdpi;
        float y = getResources().getDisplayMetrics().ydpi;
        Log.e("metrics", x + "\n" + y);
        saveFile2();

        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        mSwitch = (Switch) findViewById(R.id.switch_vibrate);
        mTv = (TextView) findViewById(R.id.tv_vibrate);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mIsOpen = isChecked;
                mSwitch.setChecked(mIsOpen);
                if (mIsOpen) {
                    mVibrator.vibrate(new long[]{1000, 10, 10, 10, 10}, 1);
                    mTv.setText("已开启");
                } else {
                    mVibrator.cancel();
                    mTv.setText("已关闭");
                }
            }
        });
        ImageView image_filter = (ImageView) findViewById(R.id.image);
        image_filter.setColorFilter(0x8affcccc, PorterDuff.Mode.DST_ATOP);
        Button mBtn_Divider = (Button) findViewById(R.id.btn_divider);
        mBtn_Divider.setOnClickListener(myOnClickListener);
        Button mBtn_Divider_Listview = (Button) findViewById(R.id.btn_divider_listview);
        mBtn_Divider_Listview.setOnClickListener(myOnClickListener);
        Button btn_http = (Button) findViewById(R.id.btn_http);
        btn_http.setOnClickListener(myOnClickListener);
        Button btn_volley = (Button) findViewById(R.id.btn_volley);
        btn_volley.setOnClickListener(myOnClickListener);
        Button btn_okhttp2 = (Button) findViewById(R.id.btn_okhttp2);
        btn_okhttp2.setOnClickListener(myOnClickListener);
//        Button btn_surface = (Button) findViewById(R.id.btn_surface);
//        btn_surface.setOnClickListener(myOnClickListener);
        Button btn_fileDialog = (Button) findViewById(R.id.btn_file_dialog);
        btn_fileDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = OpenPicFileDialog.createDialog(MyVibrateActivity.this, "叮咚", new OpenPicFileDialog.CallbackBundle() {
                    @Override
                    public void callback(Bundle bundle) {

                    }
                }, ".png;.jpg;", getImages());
                dialog.show();
            }
        });

        getDefaultRingtone(RingtoneManager.TYPE_ALL);
        getDefaultRingtoneUri(RingtoneManager.TYPE_ALL);
        getRingtoneList(RingtoneManager.TYPE_ALL);
//        getRingtone(RingtoneManager.TYPE_ALL);
        List<String> list = getRingtoneTitleList(RingtoneManager.TYPE_ALL);
//        getRingtoneUriPath(RingtoneManager.TYPE_ALL);
//        getRingtoneByUriPath(RingtoneManager.TYPE_ALL);
    }

    private Map<String, Integer> getImages() {
        final Map<String, Integer> images = new HashMap<>();
        // 下面几句设置各文件类型的图标， 需要你先把图标添加到资源文件夹
        images.put(OpenPicFileDialog.sRoot, R.mipmap.file_dialog_root);   // 根目录图标
        images.put(OpenPicFileDialog.sParent, R.mipmap.file_dialog_folder_up);    //返回上一层的图标
        images.put(OpenPicFileDialog.sFolder, R.mipmap.file_dialog_folder);   //文件夹图标
        images.put("png", R.mipmap.images);   //wav文件图标
        images.put("jpg", R.mipmap.images);   //wav文件图标
        return images;
    }

    View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.btn_divider:
                    intent = new Intent(MyVibrateActivity.this, MyDividerRecyclerViewActivity.class);
                    List<String> list = getListFromFile();
                    intent.putStringArrayListExtra("ringtone", (ArrayList<String>) list);
                    break;
                case R.id.btn_divider_listview:
                    intent = new Intent(MyVibrateActivity.this, MyDividerActivity.class);
                    break;
                case R.id.btn_http:
                    intent = new Intent(MyVibrateActivity.this, MyHttpActivity.class);
                    break;
                case R.id.btn_volley:
                    intent = new Intent(MyVibrateActivity.this, MyVolleyActivity.class);
                    break;
//                case R.id.btn_surface:
//                    intent = new Intent(MyVibrateActivity.this, MySurfaceViewActivity.class);
//                    break;
                case R.id.btn_okhttp2:
                    intent = new Intent(MyVibrateActivity.this, MyOkHttp2Activity.class);
                    break;
            }
            startActivity(intent);
        }
    };

    public Ringtone getDefaultRingtone(int type) {
        return RingtoneManager.getRingtone(mContext, RingtoneManager.getActualDefaultRingtoneUri(mContext, type));
    }

    public Uri getDefaultRingtoneUri(int type) {
        return RingtoneManager.getActualDefaultRingtoneUri(mContext, type);
    }

    public List<Ringtone> getRingtoneList(int type) {
        List<Ringtone> resArr = new ArrayList<Ringtone>();
        RingtoneManager manager = new RingtoneManager(mContext);
        manager.setType(type);
        Cursor cursor = manager.getCursor();
        int count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            resArr.add(manager.getRingtone(i));
        }
        return resArr;
    }

    public Ringtone getRingtone(int type, int pos) {
        RingtoneManager manager = new RingtoneManager(mContext);
        manager.setType(type);
        return manager.getRingtone(pos);
    }

    public List<String> getRingtoneTitleList(int type) {
        List<String> resArr = new ArrayList<String>();
        RingtoneManager manager = new RingtoneManager(mContext);
        manager.setType(type);
        Cursor cursor = manager.getCursor();
        if (cursor.moveToFirst()) {
            do {
                resArr.add(cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX));
            } while (cursor.moveToNext());
        }
        return resArr;
    }

    public String getRingtoneUriPath(int type, int pos, String def) {
        RingtoneManager manager = new RingtoneManager(mContext);
        manager.setType(type);
        Uri uri = manager.getRingtoneUri(pos);
        return uri == null ? def : uri.toString();
    }

    public Ringtone getRingtoneByUriPath(int type, String uriPath) {
        RingtoneManager manager = new RingtoneManager(mContext);
        manager.setType(type);
        Uri uri = Uri.parse(uriPath);
        return manager.getRingtone(mContext, uri);
    }

    private void saveFile() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/dididada");
        List<String> list = new ArrayList<>();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile2() {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        List<String> list = getRingtoneTitleList(RingtoneManager.TYPE_ALL);
        try {
            fileOutputStream = openFileOutput("dididada", MODE_PRIVATE);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<String> getListFromFile() {
        List<String> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = openFileInput("dididada");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list.addAll((ArrayList<String>) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void readSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", 0);
        sharedPreferences.getString("name", "didong");
    }

    private void writesharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", "didong");
        editor.commit();

    }

}
