package com.example.testswipemenulistviewdemo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.id.list;
import static android.R.id.primary;

/**
 * Created by Hilary on 2016/11/12.
 * 自定义RecyclerView头部悬停
 */

public class ItemDecorationActivity extends Activity {
    private List<String> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<ApplicationInfo> list = getPackageManager().getInstalledApplications(PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        for (int i = 0; i < list.size(); i++) {
            mList.add((String) list.get(i).loadLabel(getPackageManager()));
        }
        String[] list2 = mList.toArray(new String[list.size()]);
        Arrays.sort(list2);
        mList.clear();
        mList = Arrays.asList(list2);
        FrameLayout root = new FrameLayout(this);
        setContentView(root);
        final SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(this);
        root.addView(swipeRefreshLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        RecyclerView recyclerView = new RecyclerView(this);
        swipeRefreshLayout.addView(recyclerView, new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT));
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyRecyclerAdapter(this));
        recyclerView.addItemDecoration(new MyItemDecoration());
//        recyclerView.addItemDecoration(new MyItemDecoration());
    }

    class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private Context mContext;

        public MyRecyclerAdapter(Context context) {
            this.mContext = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder viewHolder = null;
            TextView textView = new TextView(mContext);
            viewHolder = new MyViewHolder(textView);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(16);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ((TextView) holder.itemView).setText(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MyItemDecoration extends RecyclerView.ItemDecoration {
        private int mTitleHeight = 40;
        private Paint paint = new Paint();

        @Override//设置title所在的区域，
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            paint.setTextSize(30);
            int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
            if (position > -1) {
                if (position == 0) {
                    //设置上方高度为mTitleHeight的rect为title的绘制区域
                    outRect.set(0, mTitleHeight, 0, 0);
                } else {
                    if (!getFirstName(position).substring(0, 1).equals((getFirstName(position - 1)).substring(0, 1))) {
                        outRect.set(0, mTitleHeight, 0, 0);
                    } else {
                        outRect.set(0, 0, 0, 0);
                    }
                }
            }
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            int left = parent.getLeft();
            int right = parent.getRight();
            int count = parent.getChildCount();
            for (int i = 0; i < count; i++) {
                paint.setColor(0x44000000);
                View view = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
                int position = params.getViewLayoutPosition();
                int top = view.getTop();
                view.setBackgroundColor(0x22ff0000);
                //view不包含 itemDecoration 部分 canvas 指的是当前的整个屏幕
                Log.e("size","view:  "+view.getWidth()+"  "+view.getHeight());
                Log.e("size","canvas:  "+c.getWidth()+"  "+c.getHeight());
                Log.e("size","left:  "+left+";   paddingLeft:  "+parent.getPaddingLeft());
                if (position > 0) {
                    //绘制第一个view的title，由于存在悬停的title，防止出现两个title，所以取消该部分的绘制
//                    if (position == 0) {
//                        drawRectAndText(getFirstName(position).substring(0, 1), left, right, top, params, c);
//                    } else
                    if (!getFirstName(position).substring(0, 1).equals((getFirstName(position - 1)).substring(0, 1))) {
                        if (top>mTitleHeight){
                        drawRectAndText(getFirstName(position).substring(0, 1), left, right, top, params, c);}
                    }
                }
            }
        }

        private void drawRectAndText(String text, int left, int right, int top, RecyclerView.LayoutParams params, Canvas c) {
            Rect rect = new Rect(left, top - params.topMargin - mTitleHeight, right, top - params.topMargin);
            c.drawRect(rect, paint);
            paint.setColor(0xffff0000);
            paint.setTextSize(30);
//                        paint.getTextBounds(getFirstName(i),0,30,rect);
            c.drawText(text, left, top - params.topMargin - (mTitleHeight / 2), paint);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
            //获取layoutManager-------固定为LinearlayoutManager
            LinearLayoutManager manager = (LinearLayoutManager) parent.getLayoutManager();
            //获取在屏幕上显示的第一个view的position
            int position = manager.findFirstVisibleItemPosition();
            //获取在屏幕上显示的第一个view
            View child = parent.findViewHolderForLayoutPosition(position).itemView;
            if (getFirstName(position) != null && !getFirstName(position).substring(0, 1).equals((getFirstName(position +1)).substring(0, 1))) {
                int top = child.getTop();
//                //当child马上要离开屏幕(child.bottom<titleHeight)开始向上移动
                if (top + child.getHeight() < mTitleHeight) {
//                    //向上移动的距离就是小于titleHeight的距离
//                    c.translate(0, child.getHeight() + child.getTop() - mTitleHeight);
                    //clipRect() 得到的是进行剪切的区域clip(left,top,right,bottom)得到的就是(left,top,right,bottom)所划定的区域
                    c.clipRect(parent.getPaddingLeft(),parent.getPaddingTop(),parent.getRight()-parent.getPaddingRight(),child.getBottom());
                    Log.e("size","clip:  "+String.valueOf(child.getBottom()-parent.getPaddingTop()));
                }
            }
            paint.setColor(0x44000000);
            c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + mTitleHeight, paint);
            paint.setColor(0xffff0000);
            c.drawText(getFirstName(position).substring(0, 1), child.getPaddingLeft(), child.getPaddingTop() + mTitleHeight / 2, paint);
        }

        private String getFirstName(int position) {
            return String.valueOf(mList.get(position));
        }
    }
}
