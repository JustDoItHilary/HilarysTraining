package com.example.testswipemenulistviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.testswipemenulistviewdemo.R;


/**
 * Created by Hilary on 2016/11/7.
 * 自定义viewpager的title
 */

public class ViewPagerTitleView extends LinearLayout {
    private Paint mPaint;
    private Context mContext;
    private ViewPager mPager;
    private LinearLayout mLayout;
    private LinearLayout.LayoutParams params1;
    private float mDistense = 0;
    private int mPosition =0;

    public ViewPagerTitleView(Context context) {
        this(context, null);
    }

    public ViewPagerTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.colorAccent));
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mLayout = new LinearLayout(context);
        mLayout.setOrientation(LinearLayout.HORIZONTAL);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 20, 0, 20);
        addView(mLayout, params);

        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);

        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(60,MeasureSpec.EXACTLY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = mLayout.getWidth() / mPager.getAdapter().getCount();
        canvas.drawRect(mLayout.getLeft() + (mPosition + mDistense) * width, mLayout.getBottom(), mLayout.getLeft() + (mPosition + 1 + mDistense) * width, mLayout.getBottom() + dp(6), mPaint);
    }

    public void setData() {
        for (int i = 0; i < mPager.getAdapter().getCount(); i++) {
            TextView textView = new TextView(mContext);
            textView.setTextSize(18);
            textView.setText(mPager.getAdapter().getPageTitle(i));
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(params1);
            final int finalI = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPager.setCurrentItem(finalI);
                }
            });
            mLayout.addView(textView);
        }
    }

    public void setViewPager(ViewPager pager) {
        this.mPager = pager;
        if (mPager.getAdapter() != null) {
            setData();
            mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    mDistense = positionOffset;
                    mPosition=position;
                    invalidate();
                }

                @Override
                public void onPageSelected(int position) {
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    public int dp(float var0) {
        float density = 2.0f;
        return var0 == 0.0F ? 0 : (int) Math.ceil((double) (density * var0));
    }
}
