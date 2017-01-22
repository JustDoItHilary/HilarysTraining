package com.example.testswipemenulistviewdemo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.testswipemenulistviewdemo.view.MyTouchEventListView;
import com.example.testswipemenulistviewdemo.view.MyTouchEventViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hilary on 2016/11/1.
 */

public class TouchEventActivity extends Activity {

    private MyTouchEventListView myDifferentListView;
    //    private List<ApplicationInfo> mList = new ArrayList();
    private List<String> mList = new ArrayList();
    private MenuInflater menuInflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different);
//        mList = getPackageManager().getInstalledApplications(PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        mList = Arrays.asList(this.getResources().getStringArray(R.array.dididi));
        myDifferentListView = (MyTouchEventListView) findViewById(R.id.different_listview);
        myDifferentListView.setAdapter(new MyAdapter(this));

        menuInflater = new MenuInflater(this);

    }


    private class MyAdapter extends BaseAdapter {

        private Context mContext;

        public MyAdapter(Context context) {
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new MyTouchEventViewGroup(mContext);
            }
            MyTouchEventViewGroup view = (MyTouchEventViewGroup) convertView;
//            view.setData(mList.get(position).loadLabel(getPackageManager()),mList.get(position).loadIcon(getPackageManager()));
            Bitmap bitmap = BitmapFactory.decodeResource(TouchEventActivity.this.getResources(), R.drawable.drawable2);
            Drawable drawable = new BitmapDrawable(bitmap);
            view.setData(mList.get(position), TouchEventActivity.this.getDrawable(R.mipmap.ic_launcher));
            return convertView;
        }
    }


    private void chageType() {
        Resources resources = TouchEventActivity.this.getResources();
        InputStream inputStream = resources.openRawResource(R.mipmap.ic_launcher);
        Bitmap bitmap;
        Drawable drawable;

        bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher);
        bitmap = BitmapFactory.decodeStream(inputStream);
        bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher);

        drawable = resources.getDrawable(R.mipmap.ic_launcher);
        drawable = new BitmapDrawable(bitmap);
        drawable = new BitmapDrawable(inputStream);

        bitmap = ((BitmapDrawable) drawable).getBitmap();

        resources.getDimension(R.dimen.activity_horizontal_margin);
        resources.openRawResource(R.raw.raw1);

        AssetManager assetManager = this.getAssets();
        try {
            InputStream inputStream1 = assetManager.open("data.txt");
            int length=inputStream.available();
            byte[] buffer=new byte[length];
            inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
