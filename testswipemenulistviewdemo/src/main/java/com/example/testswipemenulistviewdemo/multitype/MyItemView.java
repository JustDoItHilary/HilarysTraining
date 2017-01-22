package com.example.testswipemenulistviewdemo.multitype;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Created by Hilary on 2016/11/17.
 * itemView
 */

public abstract class MyItemView extends FrameLayout {
    public MyItemView(Context context) {
        this(context,null);
    }

    public MyItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    protected abstract RecyclerView.ViewHolder onCreateViewHolder();
    protected abstract void onBindViewHolder();
}
