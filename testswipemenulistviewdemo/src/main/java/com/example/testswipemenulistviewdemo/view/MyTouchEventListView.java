package com.example.testswipemenulistviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by Hilary on 2016/11/1.
 */

public class MyTouchEventListView extends ListView {
    public MyTouchEventListView(Context context) {
        super(context);
    }

    public MyTouchEventListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouchEventListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("TouchEvent", "ListView  "+"onIntercept  "+ev.getAction());
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.e("TouchEvent", "ListView  "+"onIntercept  "+ev.getAction());
                return false;
            case MotionEvent.ACTION_UP:
                Log.e("TouchEvent", "ListView  "+"onIntercept  "+ev.getAction());
                return false;
        }
        Log.e("TouchEvent", "ListView  "+"onIntercept  "+ev.getAction());
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("TouchEvent", "ListView  "+"onTouchEvent  "+ev.getAction());
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.e("TouchEvent", "ListView  "+"onTouchEvent  "+ev.getAction());
                return false;
            case MotionEvent.ACTION_UP:
                Log.e("TouchEvent", "ListView  "+"onTouchEvent  "+ev.getAction());
                return false;
        }
        Log.e("TouchEvent", "ListView  "+"onTouchEvent  "+ev.getAction());
        return false;
    }
}
