package com.example.testclockdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Hilary on 2016/8/20.
 *
 */
public class ExtSignClockTimeView extends LinearLayout {


    private int h = 0;
    private int w = 0;
    private int parentTop = 0;
    private int parentBottom = 0;
    private float fullAngelFactor = 30f;
    private float fullScaleFactor = 1;

    public ExtSignClockTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setParentHeight(int height) {
        h = height;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        int top = getTop();
        int bottom=getBottom();
        float half=parentTop+h/2;
        float mm=half+(bottom-top)/2;
        if (top<half&&top>half-(bottom-top)){
            Paint paint=new Paint();
//            paint.setColor(Color.RED);
//            canvas.drawRoundRect(getLeft(),getTop(),getRight(),getBottom(),w,h,paint);
            canvas.drawColor(Color.parseColor("#ffffcc"));
        }else {
            canvas.drawColor(Color.parseColor("#ffffff"));
        }
        float rotate = 0;
        float scale = calcuylateScale(top,bottom, h);

        Matrix m = canvas.getMatrix();
//        m.preTranslate(-2 / getWidth(), -2 / getHeight());
//        m.postScale(1, scale);
//        m.postTranslate(2 / getWidth(), 2 / getHeight());
//        m.postRotate(rotate);
        canvas.concat(m);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private float calculateAngel(int top, int h) {
        float result = 0f;
        if (top < h / 2f) {
            result = (top - (h / 2f)) / (h / 2f) * fullAngelFactor;
        } else if (top > h / 2f) {
            result = (top - (h / 2f)) / (h / 2f) * fullAngelFactor;
        }
        return result;
    }

    private float calcuylateScale(int top,int bottom, int h) {
        float result = 0f;




        result = (1f - 1f / 2f * Math.abs((top - h / 2f)) / (h / 2f)) * fullScaleFactor;
        return result;

    }

    public void setParentTop(int top) {
        this.parentTop=top;
    }

    public void setParentBottom(int bottom) {
        this.parentBottom=bottom;
    }

    public void setParentWidth(int width) {
        this.w=width;
    }
}
