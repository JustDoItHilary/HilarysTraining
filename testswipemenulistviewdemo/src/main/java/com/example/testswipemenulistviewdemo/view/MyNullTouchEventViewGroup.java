package com.example.testswipemenulistviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Hilary on 2016/11/2.
 */

public class MyNullTouchEventViewGroup extends LinearLayout {
    public MyNullTouchEventViewGroup(Context context) {
        super(context);
    }

    public MyNullTouchEventViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNullTouchEventViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("TouchEvent", "ViewGroup  "+"onIntercept  "+ev.getAction());
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.e("TouchEvent", "ViewGroup  "+"onIntercept  "+ev.getAction());
                return false;
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
                return true;
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
}
