package com.example.copynotesdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.copynotesdemo.AndroidUtils;
import com.example.copynotesdemo.R;

/**
 * Created by Hilary on 2016/11/24.
 * 右侧带 icon 的view
 */

public class TextAndCheckCell extends LinearLayout {
    private Context mContext;
    private boolean mIsDraw;
    private Paint mPaint;
    private TextView mTitleTv;
    private TextView mTimeTv;
    private ImageView mCheckImage;
    private int mPadding;

    public TextAndCheckCell(Context context) {
        this(context, null);
    }

    public TextAndCheckCell(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextAndCheckCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext=context;
        if (mPaint == null) {
            //抗锯齿
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setAlpha(255);
        }
        setOrientation(HORIZONTAL);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        mTitleTv = new TextView(context);
        mTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        mTitleTv.setTextColor(0xff000000);
        mTitleTv.setSingleLine(true);
        mTitleTv.setMaxLines(1);
        mTitleTv.setEllipsize(TextUtils.TruncateAt.END);
        mTitleTv.setGravity(Gravity.CENTER_VERTICAL|Gravity.START);
        LayoutParams titleParams = new LayoutParams(LayoutParams.MATCH_PARENT, AndroidUtils.dp2px(mContext,48), 1.0f);
        titleParams.setMargins(16, 0, 8, 0);
        addView(mTitleTv, titleParams);

        mTimeTv = new TextView(context);
        mTimeTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        mTimeTv.setTextColor(0x55000000);
        mTimeTv.setSingleLine(true);
        mTimeTv.setMaxLines(1);
        mTimeTv.setEllipsize(TextUtils.TruncateAt.END);
        mTimeTv.setGravity(Gravity.CENTER_VERTICAL|Gravity.END);
        LayoutParams timeParams = new LayoutParams(LayoutParams.MATCH_PARENT, AndroidUtils.dp2px(mContext,48), 1.0f);
        timeParams.setMargins(16, 0, 8, 0);
        addView(mTimeTv, timeParams);

        mCheckImage = new ImageView(context);
        mCheckImage.setImageResource(R.mipmap.ic_done_grey600_24dp);
//        mCheckImage.setVisibility(GONE);
        LayoutParams checkParams = new LayoutParams(48, 48);
        checkParams.gravity = Gravity.END|Gravity.CENTER_VERTICAL;
        checkParams.setMargins(8,0,16,0);
        mCheckImage.setColorFilter(0xff66ffff);
        addView(mCheckImage, checkParams);
    }

    public void setData(String s,String t, int i, boolean b) {
        mTitleTv.setText(s);
        mTimeTv.setText(t);
        this.mPadding = i;
        this.mIsDraw = b;
        setWillNotDraw(!b);
    }

    public void setImageResource(int resource) {
        mCheckImage.setImageResource(resource);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mIsDraw) {
            mPaint.setColor(0xFF66ff99);
            canvas.drawLine(AndroidUtils.dp2px(mContext,mPadding), canvas.getHeight() - 1, canvas.getWidth() - AndroidUtils.dp2px(mContext,mPadding), canvas.getHeight(), mPaint);
        }
    }
}
