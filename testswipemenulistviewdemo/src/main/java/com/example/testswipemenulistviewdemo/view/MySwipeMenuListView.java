package com.example.testswipemenulistviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.testswipemenulistviewdemo.MySwipMenuAdapter;

/**
 * Created by Administrator on 2016/10/21.
 * 自定义Listview（item可以左滑/右滑）
 */

public class MySwipeMenuListView extends ListView {

    private Context mContext;
    private float mStartX;
    private float mStartY;
    private float mEndX;
    private float mEndY;
    private float mMoveX;
    private float mMoveY;
    private int disX;
    private MySwipMenuAdapter mAdapter;
    private int mTouchPosition;
    private MySwipeMenuLayout mView;


    public MySwipeMenuListView(Context context) {
        super(context);
        this.mContext = context;
    }

    public MySwipeMenuListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public MySwipeMenuListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        mAdapter = new MySwipMenuAdapter(mContext, adapter);
        super.setAdapter(mAdapter);
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        super.setOnItemClickListener(listener);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = (int) ev.getX();
                return false;
            case MotionEvent.ACTION_MOVE:
                if (mStartX - ev.getX() < 0) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                mStartX = ev.getX();
//                mStartY = ev.getY();
                mTouchPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
                View view = getChildAt(mTouchPosition - getFirstVisiblePosition());
                mView = (MySwipeMenuLayout) view;
                if (mView != null) {
                    mView.onSwip(ev);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mView != null) {
                    mView.onSwip(ev);
                    return true;
                }
//                mMoveX = mStartX - ev.getX();
//                mMoveY = mStartY - ev.getY();
//                 disX = (int) Math.abs(mMoveX);
//                int disY = (int) Math.abs(mMoveY);
//                if (disY < dp2px(80) && mView != null) {
//                    mView.layout((int) -mMoveX, mView.getTop(), (int) (mView.getWidth() - mMoveX), mView.getMeasuredHeight());
//                }
                break;
            case MotionEvent.ACTION_UP:
                if (mView != null) {
                    mView.onSwip(ev);
                }
//                mMoveX = mStartX - ev.getX();
//                mMoveY = mStartY - ev.getY();
//                int disX2 = (int) Math.abs(mMoveX);
//                float disY2 = Math.abs(mMoveY);
//                Log.e("TouchEvent", String.valueOf(mMoveX));
//                if (disX2 > dp2px(120) && disY2 < dp2px(80) && mView != null) {
//                    mView.layout(-dp2px(240), mView.getTop(), mView.getMeasuredWidth() - dp2px(240), mView.getMeasuredHeight());
//                } else if (mView != null) {
//                    Log.e("TouchEvent", String.valueOf(mView.getLeft()));
//                    mView.layout(0, mView.getTop(), mView.getWidth(), mView.getMeasuredHeight());
//                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private int dp2px(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        int px = (int) (dp * scale + 0.5f);
        return px;
    }

    public void setPosition(int position) {
//        this.mPosition = position;
//        Log.e("TouchEvent", String.valueOf(mPosition));
    }
}
