package com.example.testswipemenulistviewdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.testswipemenulistviewdemo.multitype.MyTypeRecyclerViewActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(frameLayout);

        SwipeRefreshLayout refreshLayout = new SwipeRefreshLayout(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(16, 16, 16, 16);
        frameLayout.addView(refreshLayout, params);

        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this));
        refreshLayout.addView(recyclerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        long time = System.currentTimeMillis();
        List<Long> list = new ArrayList<>();
        String timeStr = "2016-11-11 10:10:10";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        Date date = new Date();
        for (int i = 0; i < 100; i++) {
            try {
                list.add(format.parse(timeStr).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            date.setTime(list.get(i));
            list1.add(format.format(date));
        }
        long time2 = System.currentTimeMillis() - time;
        Log.e("time", String.valueOf(time2));
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private Context mContext;
        private List<String> mList = new ArrayList<>();

        MyAdapter(Context context) {
            this.mContext = context;
            initList();
        }

        private void initList() {
            mList.clear();
            mList.add(MyInputType.TYPE_LEFT_SLIP_LISTVIEW, "左滑recyclerview");
            mList.add(MyInputType.TYPE_TOUCHEVENT_DISTRIBUTE, "自定义listview分析分发机制");
            mList.add(MyInputType.TYPE_VIEWPAGER, "viewpager（浸式状态栏）");
            mList.add(MyInputType.TYPE_SLIP_TITLE_VIEWPAGER, "带滑动标题的viewpager（带indicator的viewpager，自定义圆形drawable）");
            mList.add(MyInputType.TYPE_TRANSLUCENT_CARDVIEW, "半透明dialog（浸式状态栏）");
            mList.add(MyInputType.TYPE_MULTI_TYPE_RECYCLERVIEW, "多类型recyclerView（参考自multiType）");
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(new TextView(mContext));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            ((TextView) holder.itemView).setText(mList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    switch (position) {
                        case MyInputType.TYPE_LEFT_SLIP_LISTVIEW:
                            intent.setClass(MainActivity.this, SimpleActivity.class);
                            break;
                        case MyInputType.TYPE_TOUCHEVENT_DISTRIBUTE:
                            intent.setClass(MainActivity.this, TouchEventActivity.class);
                            break;
                        case MyInputType.TYPE_MULTI_TYPE_RECYCLERVIEW:
                            intent.setClass(MainActivity.this, MyTypeRecyclerViewActivity.class);
                            break;
                        case MyInputType.TYPE_SLIP_TITLE_VIEWPAGER:
                            intent.setClass(MainActivity.this, ViewPager2Activity.class);
                            break;
                        case MyInputType.TYPE_TRANSLUCENT_CARDVIEW:
                            intent.setClass(MainActivity.this, CardActivity.class);
                            break;
                        case MyInputType.TYPE_VIEWPAGER:
                            intent.setClass(MainActivity.this, ViewPagerActivity.class);
                            break;
                    }
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ((TextView) itemView).setTextSize(18);
            ((TextView) itemView).setGravity(Gravity.CENTER_VERTICAL);
        }
    }

    class MyInputType {
        private static final int TYPE_LEFT_SLIP_LISTVIEW = 0;
        private static final int TYPE_TOUCHEVENT_DISTRIBUTE = 1;
        private static final int TYPE_VIEWPAGER = 2;
        private static final int TYPE_SLIP_TITLE_VIEWPAGER = 3;
        private static final int TYPE_TRANSLUCENT_CARDVIEW = 4;
        private static final int TYPE_MULTI_TYPE_RECYCLERVIEW = 5;
    }
}
