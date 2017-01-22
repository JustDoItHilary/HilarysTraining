package com.example.testswipemenulistviewdemo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.testswipemenulistviewdemo.view.MySwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilary on 2016/10/20.
 * 左滑/右滑
 */

public class SimpleActivity extends Activity {
    private List<ApplicationInfo> mList = new ArrayList();
    private MySwipeMenuListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        final ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setMessage("正在进行中。。。");
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
////        dialog.setContentView(R.layout.activity_main);          //dialog.setContentView()必须放于dialog.show()方法后面
//        final Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                dialog.dismiss();
//                super.handleMessage(msg);
//            }
//        };
//        handler.sendEmptyMessageDelayed(0, 2000);

        mList = getPackageManager().getInstalledApplications(PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        mListView = (MySwipeMenuListView) findViewById(R.id.listview);
        mListView.setAdapter(new MyAdapter(this));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListView.setPosition(position);
            }
        });
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
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = new LinearLayout(mContext);
                convertView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                viewHolder = new ViewHolder((LinearLayout) convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv.setText(mList.get(position).loadLabel(getPackageManager()));
            viewHolder.imageView.setImageDrawable(mList.get(position).loadIcon(getPackageManager()));
            return convertView;
        }

        private class ViewHolder {
            private TextView tv;
            private ImageView imageView;

            public ViewHolder(LinearLayout linearLayout) {
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                imageView = new ImageView(mContext);
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(dp2px(60),dp2px(60));
                imageView.setLayoutParams(params);
                imageView.setColorFilter(Color.parseColor("#ffcccc"), PorterDuff.Mode.DST_ATOP);
                linearLayout.addView(imageView);
                tv = new TextView(mContext);
                tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                tv.setGravity(Gravity.CENTER_VERTICAL);
                linearLayout.addView(tv);
            }
        }
    }

    private int dp2px(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        int px = (int) (dp * scale + 0.5f);
        return px;
    }
}
