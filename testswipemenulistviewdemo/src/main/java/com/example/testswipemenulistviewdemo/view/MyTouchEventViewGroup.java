package com.example.testswipemenulistviewdemo.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Hilary on 2016/11/1.
 */

public class MyTouchEventViewGroup extends LinearLayout {
    public TextView tv;
    public ImageView imageView;

    public MyTouchEventViewGroup(Context mContext) {
        super(mContext);
        setOrientation(LinearLayout.HORIZONTAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView = new ImageView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dp2px(60), dp2px(60));
        imageView.setLayoutParams(params);
        imageView.setColorFilter(Color.parseColor("#ffcccc"), PorterDuff.Mode.DST_ATOP);
        addView(imageView);
        tv = new MyTouchEventView(mContext);
        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tv.setGravity(Gravity.CENTER_VERTICAL);
        addView(tv);
    }

    public MyTouchEventViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouchEventViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed,l,t,r,b);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("TouchEvent", "ViewGroup  "+"onIntercept  "+ev.getAction());
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.e("TouchEvent", "ViewGroup  "+"onIntercept  "+ev.getAction());
                return true;
            case MotionEvent.ACTION_UP:
                Log.e("TouchEvent", "ViewGroup  "+"onIntercept  "+ev.getAction());
                return false;
        }
        Log.e("TouchEvent", "ViewGroup  "+"onIntercept  "+ev.getAction());
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("TouchEvent", "ViewGroup  "+"onTouchEvent  "+ev.getAction());
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.e("TouchEvent", "ViewGroup  "+"onTouchEvent  "+ev.getAction());
                return false;
            case MotionEvent.ACTION_UP:
                Log.e("TouchEvent", "ViewGroup  "+"onTouchEvent  "+ev.getAction());
                return false;
        }
        Log.e("TouchEvent", "ViewGroup  "+"onTouchEvent  "+ev.getAction());
        return false;
    }


    private int dp2px(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        int px = (int) (dp * scale + 0.5f);
        return px;
    }

    public void setData(CharSequence charSequence, Drawable drawable) {
        tv.setText(charSequence);
        imageView.setImageDrawable(drawable);
    }
}
