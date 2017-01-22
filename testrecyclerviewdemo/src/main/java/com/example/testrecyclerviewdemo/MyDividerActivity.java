package com.example.testrecyclerviewdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilary on 2016/9/9.
 * 自定义Divider
 */
public class MyDividerActivity extends Activity {
    private ListView mListView;
    private DividerAdapter mAdapter;
    private List<MyCheckEntity> mList = new ArrayList<>();
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divider_layout);
//        mList.addAll(getIntent().getStringArrayListExtra("ringtone"));
        initList(0);
        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new DividerAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        mAdapter.setCallBack(new DividerAdapter.OnCallBack() {
            @Override
            public void onLoad() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<MyCheckEntity> list = initList(mList.size());
                        mAdapter.addList(list);
                    }
                }, 2000);
            }
        });
        mListView.setDivider(this.getResources().getDrawable(R.drawable.insert_divider));
//        mListView.setDivider(this.getResources().getDrawable(R.mipmap.ic_launcher));
//        mListView.setDivider(new ColorDrawable(Color.parseColor("#ffcccc")));
        mListView.setDividerHeight((int) getResources().getDimension(R.dimen.activity_vertical_margin));

    }

    private List<MyCheckEntity> initList(int sum) {
        List<MyCheckEntity> list = new ArrayList<>();
        for (int i = sum; i < sum + 10; i++) {
            list.add(new MyCheckEntity("太阳当空照 花儿对我笑。。。" + i,false));
        }
        mList.addAll(list);
        return list;
    }


    private static class DividerAdapter extends BaseAdapter {
        private Context mContext;
        private List<MyCheckEntity> mList = new ArrayList<>();
        private OnCallBack mCallBack;


        public DividerAdapter(Context context, List<MyCheckEntity> list) {
            this.mContext = context;
            addList(list);
        }

        private void addList(List<MyCheckEntity> list) {
            mList.addAll(list);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList.size() + 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == mList.size()) {
                return 0;
            }
            return 1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            int type = getItemViewType(position);
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            if (type == 0) {
                if (convertView == null) {
                    convertView = layoutInflater.inflate(R.layout.ww_test_list_item, parent, false);
                }
                mCallBack.onLoad();
            } else if (type == 1) {
                LinearLayout linearLayout;
                if (convertView == null) {
                    linearLayout = new LinearLayout(mContext);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    TextView textView = new TextView(mContext);
                    textView.setGravity(Gravity.CENTER);
                    linearLayout.addView(textView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120, 1.0F));
                    CheckBox checkBox = new CheckBox(mContext);
                    linearLayout.addView(checkBox, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    convertView = linearLayout;
                }
                linearLayout = (LinearLayout) convertView;
                ((TextView) linearLayout.getChildAt(0)).setText(mList.get(position).getMess());
                ((CheckBox)linearLayout.getChildAt(1)).setChecked(mList.get(position).getEable());
                Log.e("list_item",position+"      "+convertView.getWidth()+"      "+convertView.getHeight()+"\n"+convertView.getLayoutParams());
//                if (position==2){
//                    Log.e("list_item",convertView.getWidth()+"\n"+convertView.getHeight()+"\n"+convertView.getLayoutParams());
//                    ViewGroup.LayoutParams params=convertView.getLayoutParams();
//                    params.height=1;
//                    convertView.setLayoutParams(params);
//                }
                linearLayout.getChildAt(1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mList.get(position).setEnable(!mList.get(position).getEable());
                    }
                });
            }
            return convertView;
        }

        private void setCallBack(OnCallBack callBack) {
            mCallBack = callBack;
        }

        interface OnCallBack {
            void onLoad();
        }
    }

    private class MyCheckEntity {
        private String mess;
        private boolean enable;

        public MyCheckEntity(String s, boolean b) {
            this.mess=s;
            this.enable=b;
        }

        public String getMess() {
            return mess;
        }

        public void setMess(String mess) {
            this.mess = mess;
        }

        public boolean getEable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }
    }
}
