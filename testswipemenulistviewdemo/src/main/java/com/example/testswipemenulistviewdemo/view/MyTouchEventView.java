package com.example.testswipemenulistviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Hilary on 2016/11/1.
 */

public class MyTouchEventView extends TextView {
    public MyTouchEventView(Context context) {
        super(context);
    }

    public MyTouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouchEventView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("TouchEvent", "View  "+"onTouchEvent  "+ev.getAction());
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.e("TouchEvent", "View  "+"onTouchEvent  "+ev.getAction());
                return true;
            case MotionEvent.ACTION_UP:
                Log.e("TouchEvent", "View  "+"onTouchEvent  "+ev.getAction());
                return false;
        }
        Log.e("TouchEvent", "View  "+"onTouchEvent  "+ev.getAction());
        return false;
    }
}
