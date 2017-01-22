package com.example.testswipemenulistviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Hilary on 2016/10/22.
 * listview item的布局
 */

public class MySwipeMenuLayout extends LinearLayout {
    private View mConvertView;
    private MySwipeMenuView mSwipeMenuView;
    private float mStartX;
    private float mStartY;
    private float mEndX;
    private float mEndY;
    private float mMoveX;
    private float mMoveY;
    private int mTouchPosition;

    public MySwipeMenuLayout(Context context) {
        super(context);
    }

    public MySwipeMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySwipeMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MySwipeMenuLayout(View convertView, MySwipeMenuView swipeMenuView) {
        super(convertView.getContext());
        this.mConvertView =convertView;
        this.mSwipeMenuView=swipeMenuView;
        init();
    }

    private void init() {
        LayoutParams params=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mConvertView.setLayoutParams(params);
        addView(mConvertView);
        mSwipeMenuView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mSwipeMenuView);
    }

    public void onSwip(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                mStartY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveX = mStartX - ev.getX();
                mMoveY = mStartY - ev.getY();
                int disX = (int) Math.abs(mMoveX);
                int disY = (int) Math.abs(mMoveY);
//                Log.e("TouchEvent","dp2px "+dp2px(120));
                if (disY < dp2px(60)&&mMoveX>0&&mConvertView.getLeft()>-dp2px(120)) {
                    Log.e("TouchEvent","left "+mConvertView.getLeft());
//                    Log.e("TouchEvent","left "+mSwipeMenuView.getLeft());
                    Log.e("TouchEvent","width "+mSwipeMenuView.getWidth());
//                    Log.e("TouchEvent","measurewidth "+mSwipeMenuView.getMeasuredWidth());
                    Log.e("TouchEvent", "moveX "+String.valueOf(mMoveX));
                    int moveDis=mMoveX>dp2px(120)?dp2px(120):(int) mMoveX;
                    mConvertView.layout( -moveDis, mConvertView.getTop(), (mConvertView.getMeasuredWidth() - moveDis), mConvertView.getMeasuredHeight());
                    mSwipeMenuView.layout( (mConvertView.getMeasuredWidth() - moveDis), mConvertView.getTop(),
                            (mConvertView.getMeasuredWidth()+mSwipeMenuView.getMeasuredWidth() - moveDis), mConvertView.getMeasuredHeight());
                }
                break;
            case MotionEvent.ACTION_UP:
                mMoveX = mStartX - ev.getX();
                mMoveY = mStartY - ev.getY();
                int disX2 = (int) Math.abs(mMoveX);
                float disY2 = Math.abs(mMoveY);
                if (disX2 > dp2px(70) && disY2 < dp2px(60) && mConvertView != null&&mMoveX>0) {
                    mConvertView.layout(-dp2px(120), mConvertView.getTop(), mConvertView.getMeasuredWidth() - dp2px(120), mConvertView.getMeasuredHeight());
                    mSwipeMenuView.layout( (mConvertView.getMeasuredWidth() -dp2px(120)), mConvertView.getTop(),
                             (mConvertView.getMeasuredWidth()+mSwipeMenuView.getMeasuredWidth() -dp2px(120)), mConvertView.getMeasuredHeight());
                } else if (mConvertView != null) {
                    mConvertView.layout(0, mConvertView.getTop(), mConvertView.getMeasuredWidth(), mConvertView.getMeasuredHeight());
                    mSwipeMenuView.layout( (mConvertView.getMeasuredWidth() ), mConvertView.getTop(),
                             (mConvertView.getMeasuredWidth()+mSwipeMenuView.getMeasuredWidth()), mConvertView.getMeasuredHeight());
                }
                break;
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mSwipeMenuView.measure(MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(
                getMeasuredHeight(), MeasureSpec.EXACTLY));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mConvertView.layout(0, 0, getMeasuredWidth(),
                mConvertView.getMeasuredHeight());
        if (mMoveX>0) {
            mSwipeMenuView.layout(getMeasuredWidth(), 0,
                    getMeasuredWidth() + mSwipeMenuView.getMeasuredWidth(),
                    mConvertView.getMeasuredHeight());
        } else {
            mSwipeMenuView.layout(-mSwipeMenuView.getMeasuredWidth(), 0,
                    0, mConvertView.getMeasuredHeight());
        }
    }

    private int dp2px(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        int px = (int) (dp * scale + 0.5f);
        return px;
    }
}
