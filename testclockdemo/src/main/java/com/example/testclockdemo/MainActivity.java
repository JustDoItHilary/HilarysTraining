package com.example.testclockdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilary on 2016/8/20.
 * 添加闹钟
 */
public class MainActivity extends Activity implements AbsListView.OnScrollListener {


    private ListView mListView_H;
    private ListView mListView_M;
    private List<Integer> mHourList = new ArrayList<>();
    private List<Integer> mMinuteList = new ArrayList<>();
    private int mSmoothDistance;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ww_activity_ext_addclock);
        String a="a b c d ";
        String[]b=a.split(" ");
        String d="......";
        for (int c=0;c<b.length;c++){
            d=d+b[c];
        }
        Log.e("split",d);


        initHourList();
        initMinuteList();
    }

    private void initMinuteList() {
        mListView_M = (ListView) findViewById(R.id.ext_clock_list_minutes);
        mMinuteList.clear();
        for (int i = 1; i < 60; i++) {
            mMinuteList.add(i);
        }
        //解决魅族滑动问题
        mListView_M.setOverScrollMode(mListView_M.OVER_SCROLL_NEVER);
        mListView_M.setAdapter(new MinutesAdapter(this, mMinuteList));
        mListView_M.setVerticalScrollBarEnabled(false);
        mListView_M.setDividerHeight(0);
        mListView_M.setDivider(null);
        mListView_M.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == 0) {
                    int height = mListView_M.getChildAt(0).getHeight();
                    if (mSmoothDistance > height / 2) {
//                                                            mListView_H.setSelection(1 + firstVisible);
                        mListView_M.smoothScrollBy(-mSmoothDistance, 0);
                    } else {
//                                                        mListView_H.setSelection(firstVisible);
                        mListView_M.smoothScrollBy(mSmoothDistance, 0);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (totalItemCount == 0) {
                    return;
                }
                View firstView = view.getChildAt(0);
                if (null != firstView) {
                    getScrollY(mListView_M);//滚动距离
                }
            }
        });
        mListView_M.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListView_M.setSelection(position - 2);
            }
        });
    }

    private void initHourList() {
        mListView_H = (ListView) findViewById(R.id.ext_clock_list_hour);
        mHourList.clear();
        for (int i = 1; i <= 24; i++) {
            mHourList.add(i);
        }
        //解决魅族滑动问题
        mListView_H.setOverScrollMode(mListView_H.OVER_SCROLL_NEVER);
        mListView_H.setAdapter(new HourAdapter(this, mHourList));
        mListView_H.setVerticalScrollBarEnabled(false);
        mListView_H.setDividerHeight(0);
        mListView_H.setDivider(null);
        mListView_H.setOnScrollListener(new AbsListView.OnScrollListener() {

                                            @Override
                                            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                                                if (scrollState == 0) {
//                                                    int height = mListView_H.getChildAt(0).getHeight();
//                                                    if (mSmoothDistance > height / 2) {
////                                                            mListView_H.setSelection(1 + firstVisible);
//                                                        mListView_H.smoothScrollBy(-mSmoothDistance, 0);
//                                                    } else {
////                                                        mListView_H.setSelection(firstVisible);
//                                                        mListView_H.smoothScrollBy(mSmoothDistance, 0);
//                                                    }
//                                                }
                                            }

                                            @Override
                                            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                                                 int totalItemCount) {
                                                if (totalItemCount == 0) {
                                                    return;
                                                }
                                                /**到顶部添加数据*/
                                                if (firstVisibleItem <= 2) {
                                                    mListView_H.setSelection(mHourList.size() + 2);
                                                } else if (firstVisibleItem + visibleItemCount > mHourList.size()*3 - 2) {//到底部添加数据
                                                    mListView_H.setSelection(firstVisibleItem - mHourList.size());
                                                }
//                                                View firstView = view.getChildAt(0);
//                                                if (null != firstView) {
//                                                    getScrollY(mListView_H);//滚动距离
//                                                }
//                                                if (firstVisibleItem<2){
//                                                    mListView_H.setSelection(firstVisibleItem+mHourList.size());
//                                                }else if (firstVisibleItem>3*mHourList.size()-2){
//                                                    mListView_H.setSelection(firstVisibleItem-mHourList.size());
//                                                }
//                if ((visibleItemCount > 0) && (firstVisibleItem == 0)) {
//                    if (view.getChildAt(0).getTop() >= 0) {
//
//                        mListView_H.getChildAt(3-mListView_H.getFirstVisiblePosition()).findViewById(R.id.tv_addclock_item).setBackgroundColor(Color.RED);
////                        isFirstRow = true;
//                        View view1=view.getChildAt(0);
//                        view.getChildAt(0).setBackgroundColor(Color.RED);
//                        view.getChildAt(0).invalidate();
//                    }
//                }
//                else if ((totalItemCount > 0)
//                        && (view.getLastVisiblePosition()
//                        == totalItemCount - 1)) {
//                    if (view.getBottom()
//                            == view.getChildAt(
//                            view.getChildCount()-1)
//                            .getBottom()) {
//                        isLastRow = true;
//                    }
//                }
//                if ((visibleItemCount > 0)) {
//                    View mView = view.getChildAt(3);
//                    if (mView != null) {
//                        mView.setBackgroundColor(Color.GRAY);
//                    }
//                }
//                for (int i = 0; i < mListView_H.getChildCount(); i++) {
//                    mListView_H.getChildAt(i).invalidate();
//                }
//                for (int i = 0; i < mListView_H.getChildCount(); i++) {
//
//                }
                                            }
                                        }
        );
        mListView_H.setFooterDividersEnabled(true);
        mListView_H.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                               @Override
                                               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                   mListView_H.setSelection(position);
//                                                   mListView_H.invalidate();
                                               }
                                           }

        );
        mListView_H.setSelection(mHourList.size());
    }

    public void getScrollY(ListView view) {
        View c = view.getChildAt(0);
        if (c == null) {
            return;
        }
        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();
        int smoothDistance = -top + firstVisiblePosition * c.getHeight();
        Log.e("scrollDistance", String.valueOf(smoothDistance));
        View firstView = view.getChildAt(0);
        int height = firstView.getHeight();
        int num = Math.abs(smoothDistance / height);
        int l = smoothDistance - num * height;
        Log.e("smoothDistance", String.valueOf(l));
        this.mSmoothDistance = l;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int i1, int i2) {

    }

    private class HourAdapter extends BaseAdapter {
        private List<Integer> mTimeList = new ArrayList<>();
        private Context mContext;

        public HourAdapter(Context context, List<Integer> list) {
            this.mContext = context;
            this.mTimeList = list;
        }

        @Override
        public int getCount() {
            if (mTimeList != null) {
                return mTimeList.size() * 3;
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return mTimeList.get(position % mTimeList.size());
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.ww_activity_ext_addclock_item, parent, false);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_addclock_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(String.valueOf(getItem(position)));
            Log.e("item", String.valueOf(getItem(position)));
            return convertView;
        }
    }

    private class MinutesAdapter extends BaseAdapter {
        private List<Integer> mTimeList = new ArrayList<>();
        private Context mContext;

        public MinutesAdapter(Context context, List<Integer> list) {
            this.mContext = context;
            this.mTimeList = list;
        }

        @Override
        public int getCount() {
            return mTimeList.size() * 3;
        }

        @Override
        public Object getItem(int position) {
            return mTimeList.get(position % mTimeList.size());
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.ww_activity_ext_addclock_item, null);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_addclock_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setGravity(Gravity.LEFT);
            viewHolder.textView.setText(String.valueOf(getItem(position)));
            return convertView;
        }
    }

    private class ViewHolder {
        public TextView textView;
    }

}
