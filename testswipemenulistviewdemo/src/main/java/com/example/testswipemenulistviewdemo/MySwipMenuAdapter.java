package com.example.testswipemenulistviewdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.example.testswipemenulistviewdemo.view.MySwipeMenuLayout;
import com.example.testswipemenulistviewdemo.view.MySwipeMenuView;

/**
 * Created by Hilary on 2016/10/21.
 * 自定义的适配器
 */

public class MySwipMenuAdapter extends BaseAdapter {
    private Context mContext;
    private ListAdapter mAdapter;
    private View mView;

    public MySwipMenuAdapter(Context mContext, ListAdapter adapter) {
        this.mContext=mContext;
        this.mAdapter=adapter;
    }

    @Override
    public int getCount() {
        return mAdapter.getCount();
    }

    @Override
    public Object getItem(int position) {
        return mAdapter.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return mAdapter.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mView=mAdapter.getView(position,convertView,parent);
        MySwipeMenuView swipeMenuView=new MySwipeMenuView(mContext);
        MySwipeMenuLayout layout=new MySwipeMenuLayout(mView,swipeMenuView);
        return layout;
    }

    public View getView(int mPosition) {
        return mView;
    }
}
