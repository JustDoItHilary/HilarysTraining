package com.example.testswipemenulistviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.testswipemenulistviewdemo.R;

/**
 * Created by Hilary on 2016/11/11.
 */

public class IndicatorView extends View {
    private Paint paint;
    private Paint selectPaint = new Paint(1);
    private float radius = 8.0F;
    private float padding=8.0f;
    private int count;
    private int selectPonit=1;

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(context.getResources().getColor(R.color.lucentred));
        if (paint == null) {
            paint = new Paint(1);
            paint.setColor(Color.RED);
        }
        if (selectPaint == null) {
            selectPaint = new Paint(1);
            selectPaint.setColor(Color.BLUE);
        }
    }

    public void setCount(int count) {
        this.count = count;
        invalidate();
    }

    public void setSelectPonit(int selectPonit) {
        this.selectPonit = selectPonit+1;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.setMeasuredDimension((int)((float)(this.getPaddingLeft() + this.getPaddingRight()) + (float)count*2 * this.radius +(count+1)*padding), heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 1; i < count+1; i++) {
            canvas.drawCircle( this.getPaddingLeft() +i*(radius*2+padding)-radius, this.getPaddingTop() + this.getHeight() / 2, radius, paint);
        }
        if (selectPonit > 0) {
            canvas.drawCircle(this.getPaddingLeft()  +selectPonit*(radius*2+padding)-radius, this.getPaddingTop() + this.getHeight() / 2, radius, selectPaint);
        }
    }
}
