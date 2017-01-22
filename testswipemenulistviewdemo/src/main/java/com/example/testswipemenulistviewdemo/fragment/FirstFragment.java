package com.example.testswipemenulistviewdemo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.testswipemenulistviewdemo.view.IndicatorPagerView;
import com.example.testswipemenulistviewdemo.view.MyDrawableLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by Hilary on 2016/11/3.
 * fir
 */

public class FirstFragment extends Fragment {
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        FrameLayout root = new FrameLayout(mContext);
        final SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(mContext);
        FrameLayout.LayoutParams refreshParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.TOP);
        root.addView(swipeRefreshLayout, refreshParams);
        RecyclerView recyclerView = new RecyclerView(mContext);
        swipeRefreshLayout.addView(recyclerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(new MyRecyclerViewAdapter());
        return root;
    }

    private class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private List<MyEntity> mList = new ArrayList();

        MyRecyclerViewAdapter(){
            initList();
        }

        private void initList() {
            mList.clear();
            mList.add(new MyEntity(1));
            mList.add(new MyEntity(2));
        }

        @Override
        public int getItemViewType(int position) {
            switch (mList.get(position).getType()){
                case MyEntity.TYPE_FIR:
                    return 1;
                case MyEntity.TYPE_SEC:
                    return 2;
                case MyEntity.TYPE_THIR:
                    return 3;
            }
            return 0;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder viewHolder=null;
            if (viewType==1){
                IndicatorPagerView indicatorPagerView=new IndicatorPagerView(mContext);
                indicatorPagerView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300));
                viewHolder=new MyViewHolder(indicatorPagerView);
            }else if (viewType==2){
                MyDrawableLayout drawableLayout=new MyDrawableLayout(mContext);
                drawableLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,60));
                viewHolder=new MyViewHolder(drawableLayout);
            }else if (viewType==3){

            }
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

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

    class MyEntity {
        private static final int TYPE_FIR = 1;
        private static final int TYPE_SEC = 2;
        private static final int TYPE_THIR = 3;
        private int type;

        public MyEntity(int i) {
            this.type=i;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

}
