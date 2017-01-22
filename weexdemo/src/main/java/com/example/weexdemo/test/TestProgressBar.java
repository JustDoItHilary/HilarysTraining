package com.example.weexdemo.test;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;

import com.example.weexdemo.R;

/**
 * Created by Hilary
 * on 2017/1/16.
 */

public class TestProgressBar extends ProgressBar {

    public TestProgressBar(Context context) {
        this(context,null);
    }

    public TestProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.my_Style);
    }

    public TestProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs,defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
//        Drawable drawable=context.getResources().getDrawable(R.mipmap.scenery);
//        setIndeterminateDrawable(drawable);

        Resources res = context.getResources();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyProgressBar, defStyle, 0);


        final int color = a.getColor(com.example.weexdemo.R.styleable.MyProgressBar_my_color, res.getColor(R.color.my_default_color));
        final int sectionsCount = a.getInteger(R.styleable.MyProgressBar_my_sections_count, res.getInteger(R.integer.my_default_sections_count));
        final int separatorLength = a.getDimensionPixelSize(R.styleable.MyProgressBar_my_stroke_separator_length, res.getDimensionPixelSize(R.dimen.my_default_stroke_separator_length));
        final float strokeWidth = a.getDimension(R.styleable.MyProgressBar_my_stroke_width, res.getDimension(R.dimen.my_default_stroke_width));
        final float speed = a.getFloat(R.styleable.MyProgressBar_my_speed, Float.parseFloat(res.getString(R.string.my_default_speed)));
        final float speedProgressiveStart = a.getFloat(R.styleable.MyProgressBar_my_progressiveStart_speed, speed);
        final float speedProgressiveStop = a.getFloat(R.styleable.MyProgressBar_my_progressiveStop_speed, speed);
        final int iInterpolator = a.getInteger(R.styleable.MyProgressBar_my_interpolator, -1);
        final boolean reversed = a.getBoolean(R.styleable.MyProgressBar_my_reversed, res.getBoolean(R.bool.my_default_reversed));
        final boolean mirrorMode = a.getBoolean(R.styleable.MyProgressBar_my_mirror_mode, res.getBoolean(R.bool.my_default_mirror_mode));
        final int colorsId = a.getResourceId(R.styleable.MyProgressBar_my_colors, 0);
        final boolean progressiveStartActivated = a.getBoolean(R.styleable.MyProgressBar_my_progressiveStart_activated, res.getBoolean(R.bool.my_default_progressiveStart_activated));
        final Drawable backgroundDrawable = a.getDrawable(R.styleable.MyProgressBar_my_background);
        final boolean generateBackgroundWithColors = a.getBoolean(R.styleable.MyProgressBar_my_generate_background_with_colors, false);
        final boolean gradients = a.getBoolean(R.styleable.MyProgressBar_my_gradients, false);
        a.recycle();
        int strokeSeparatorLength;

        //interpolator
        Interpolator interpolator = null;
        if (iInterpolator == -1) {
            interpolator = getInterpolator();
        }
        int[] colors = null;
        //colors
        if (colorsId != 0) {
            colors = res.getIntArray(colorsId);
        }

        strokeSeparatorLength = res.getDimensionPixelSize(R.dimen.my_default_stroke_separator_length);
        TestProgressDrawable progressDrawable=new TestProgressDrawable(interpolator,
                sectionsCount,
                strokeSeparatorLength,
                colors,
                color,
                strokeWidth,
                speed,
                speed,
                speed,
                reversed,
                mirrorMode,
                progressiveStartActivated);

        setIndeterminateDrawable(progressDrawable);
    }


}
