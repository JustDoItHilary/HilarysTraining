package com.example.testrecyclerviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/10/17.
 */

public class MyLayoutView extends ViewGroup {

    public MyLayoutView(Context context) {
        super(context);
    }

    public MyLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(getChildCount()>0){
            View childView=getChildAt(0);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount()>0){
            View childView=getChildAt(0);
            int trans=100;
            childView.layout(trans,trans,childView.getMeasuredWidth()+trans,childView.getMeasuredHeight()+trans);
        }
    }
}
