package com.example.weexdemo.test;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

import com.example.weexdemo.R;


/**
 * Created by Hilary
 * on 2017/1/14.
 */

public class MyProgressBar extends ProgressBar {
    private static final int INTERPOLATOR_ACCELERATE = 0;
    private static final int INTERPOLATOR_LINEAR = 1;
    private static final int INTERPOLATOR_ACCELERATEDECELERATE = 2;
    private static final int INTERPOLATOR_DECELERATE = 3;

    public MyProgressBar(Context context) {
        this(context, null);
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
//        super(context,attrs); //不可以引用父类的含有两个参数的构造函数，需要设置默认类型
        this(context, attrs, R.attr.my_Style);
    }

    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle){
        if (isInEditMode()) {
            setIndeterminateDrawable(new MyProgressDrawable.Builder(context).build());
            return;
        }

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

        //interpolator
        Interpolator interpolator = null;
        if (iInterpolator == -1) {
            interpolator = getInterpolator();
        }
        if (interpolator == null) {
            switch (iInterpolator) {
                case INTERPOLATOR_ACCELERATEDECELERATE:
                    interpolator = new AccelerateDecelerateInterpolator();
                    break;
                case INTERPOLATOR_DECELERATE:
                    interpolator = new DecelerateInterpolator();
                    break;
                case INTERPOLATOR_LINEAR:
                    interpolator = new LinearInterpolator();
                    break;
                case INTERPOLATOR_ACCELERATE:
                default:
                    interpolator = new AccelerateInterpolator();
            }
        }

        int[] colors = null;
        //colors
        if (colorsId != 0) {
            colors = res.getIntArray(colorsId);
        }

        MyProgressDrawable.Builder builder = new MyProgressDrawable.Builder(context)
                .speed(speed)
                .progressiveStartSpeed(speedProgressiveStart)
                .progressiveStopSpeed(speedProgressiveStop)
                .interpolator(interpolator)
                .sectionsCount(sectionsCount)
                .separatorLength(separatorLength)
                .strokeWidth(strokeWidth)
                .reversed(reversed)
                .mirrorMode(mirrorMode)
                .progressiveStart(progressiveStartActivated)
                .gradients(gradients);

        if (backgroundDrawable != null) {
            builder.backgroundDrawable(backgroundDrawable);
        }

        if (generateBackgroundWithColors) {
            builder.generateBackgroundUsingColors();
        }

        if (colors != null && colors.length > 0)
            builder.colors(colors);
        else
            builder.color(color);

        MyProgressDrawable d = builder.build();
        setIndeterminateDrawable(d);
    }

//    public void applyStyle(int styleResId) {
//        TypedArray a = getContext().obtainStyledAttributes(null, R.styleable.MyProgressBar, 0, styleResId);
//
//        if (a.hasValue(R.styleable.MyProgressBar_my_color)) {
//            setMyProgressDrawableColor(a.getColor(R.styleable.MyProgressBar_my_color, 0));
//        }
//        if (a.hasValue(R.styleable.MyProgressBar_my_colors)) {
//            int colorsId = a.getResourceId(R.styleable.MyProgressBar_my_colors, 0);
//            if (colorsId != 0) {
//                int[] colors = getResources().getIntArray(colorsId);
//                if (colors != null && colors.length > 0)
//                    setMyProgressDrawableColors(colors);
//            }
//        }
//        if (a.hasValue(R.styleable.MyProgressBar_my_sections_count)) {
//            setMyProgressDrawableSectionsCount(a.getInteger(R.styleable.MyProgressBar_my_sections_count, 0));
//        }
//        if (a.hasValue(R.styleable.MyProgressBar_my_stroke_separator_length)) {
//            setMyProgressDrawableSeparatorLength(a.getDimensionPixelSize(R.styleable.MyProgressBar_my_stroke_separator_length, 0));
//        }
//        if (a.hasValue(R.styleable.MyProgressBar_my_stroke_width)) {
//            setMyProgressDrawableStrokeWidth(a.getDimension(R.styleable.MyProgressBar_my_stroke_width, 0));
//        }
//        if (a.hasValue(R.styleable.MyProgressBar_my_speed)) {
//            setMyProgressDrawableSpeed(a.getFloat(R.styleable.MyProgressBar_my_speed, 0));
//        }
//        if (a.hasValue(R.styleable.MyProgressBar_my_progressiveStart_speed)) {
//            setMyProgressDrawableProgressiveStartSpeed(a.getFloat(R.styleable.MyProgressBar_my_progressiveStart_speed, 0));
//        }
//        if (a.hasValue(R.styleable.MyProgressBar_my_progressiveStop_speed)) {
//            setMyProgressDrawableProgressiveStopSpeed(a.getFloat(R.styleable.MyProgressBar_my_progressiveStop_speed, 0));
//        }
//        if (a.hasValue(R.styleable.MyProgressBar_my_reversed)) {
//            setMyProgressDrawableReversed(a.getBoolean(R.styleable.MyProgressBar_my_reversed, false));
//        }
//        if (a.hasValue(R.styleable.MyProgressBar_my_mirror_mode)) {
//            setMyProgressDrawableMirrorMode(a.getBoolean(R.styleable.MyProgressBar_my_mirror_mode, false));
//        }
//        if (a.hasValue(R.styleable.MyProgressBar_my_progressiveStart_activated)) {
//            setProgressiveStartActivated(a.getBoolean(R.styleable.MyProgressBar_my_progressiveStart_activated, false));
//        }
//        if (a.hasValue(R.styleable.MyProgressBar_my_progressiveStart_activated)) {
//            setProgressiveStartActivated(a.getBoolean(R.styleable.MyProgressBar_my_progressiveStart_activated, false));
//        }
//        if (a.hasValue(R.styleable.MyProgressBar_my_gradients)) {
//            setMyProgressDrawableUseGradients(a.getBoolean(R.styleable.MyProgressBar_my_gradients, false));
//        }
//        if (a.hasValue(R.styleable.MyProgressBar_my_generate_background_with_colors)) {
//            if (a.getBoolean(R.styleable.MyProgressBar_my_generate_background_with_colors, false)) {
//                setMyProgressDrawableBackgroundDrawable(
//                        MyProgressBarUtils.generateDrawableWithColors(checkIndeterminateDrawable().getColors(), checkIndeterminateDrawable().getStrokeWidth()));
//            }
//        }
//        if (a.hasValue(R.styleable.MyProgressBar_my_interpolator)) {
//            int iInterpolator = a.getInteger(R.styleable.MyProgressBar_my_interpolator, -1);
//            Interpolator interpolator;
//            switch (iInterpolator) {
//                case INTERPOLATOR_ACCELERATEDECELERATE:
//                    interpolator = new AccelerateDecelerateInterpolator();
//                    break;
//                case INTERPOLATOR_DECELERATE:
//                    interpolator = new DecelerateInterpolator();
//                    break;
//                case INTERPOLATOR_LINEAR:
//                    interpolator = new LinearInterpolator();
//                    break;
//                case INTERPOLATOR_ACCELERATE:
//                    interpolator = new AccelerateInterpolator();
//                    break;
//                default:
//                    interpolator = null;
//            }
//            if (interpolator != null) {
//                setInterpolator(interpolator);
//            }
//        }
//        a.recycle();
//    }

    @Override
    protected synchronized void onDraw(Canvas canvas)  {
        super.onDraw(canvas);
        if (isIndeterminate() && getIndeterminateDrawable() instanceof MyProgressDrawable &&
                !((MyProgressDrawable) getIndeterminateDrawable()).isRunning()) {
            getIndeterminateDrawable().draw(canvas);
        }
    }

    @Override
    public void setInterpolator(Interpolator interpolator) {
        super.setInterpolator(interpolator);
        Drawable ret = getIndeterminateDrawable();
        if (ret != null && (ret instanceof MyProgressDrawable))
            ((MyProgressDrawable) ret).setInterpolator(interpolator);
    }

//    private MyProgressDrawable checkIndeterminateDrawable() {
//        Drawable ret = getIndeterminateDrawable();
//        if (ret == null || !(ret instanceof MyProgressDrawable))
//            throw new RuntimeException("The drawable is not a SmoothProgressDrawable");
//        return (MyProgressDrawable) ret;
//    }
//    public void setMyProgressDrawableInterpolator(Interpolator interpolator) {
//        checkIndeterminateDrawable().setInterpolator(interpolator);
//    }
//
//    public void setMyProgressDrawableColors(int[] colors) {
//        checkIndeterminateDrawable().setColors(colors);
//    }
//
//    public void setMyProgressDrawableColor(int color) {
//        checkIndeterminateDrawable().setColor(color);
//    }
//
//    public void setMyProgressDrawableSpeed(float speed) {
//        checkIndeterminateDrawable().setSpeed(speed);
//    }
//
//    public void setMyProgressDrawableProgressiveStartSpeed(float speed) {
//        checkIndeterminateDrawable().setProgressiveStartSpeed(speed);
//    }
//
//    public void setMyProgressDrawableProgressiveStopSpeed(float speed) {
//        checkIndeterminateDrawable().setProgressiveStopSpeed(speed);
//    }
//
//    public void setMyProgressDrawableSectionsCount(int sectionsCount) {
//        checkIndeterminateDrawable().setSectionsCount(sectionsCount);
//    }
//
//    public void setMyProgressDrawableSeparatorLength(int separatorLength) {
//        checkIndeterminateDrawable().setSeparatorLength(separatorLength);
//    }
//
//    public void setMyProgressDrawableStrokeWidth(float strokeWidth) {
//        checkIndeterminateDrawable().setStrokeWidth(strokeWidth);
//    }
//
//    public void setMyProgressDrawableReversed(boolean reversed) {
//        checkIndeterminateDrawable().setReversed(reversed);
//    }
//
//    public void setMyProgressDrawableMirrorMode(boolean mirrorMode) {
//        checkIndeterminateDrawable().setMirrorMode(mirrorMode);
//    }
//
//    public void setProgressiveStartActivated(boolean progressiveStartActivated) {
//        checkIndeterminateDrawable().setProgressiveStartActivated(progressiveStartActivated);
//    }
//
//    public void setMyProgressDrawableCallbacks(MyProgressDrawable.Callbacks listener) {
//        checkIndeterminateDrawable().setCallbacks(listener);
//    }
//
//    public void setMyProgressDrawableBackgroundDrawable(Drawable drawable) {
//        checkIndeterminateDrawable().setBackgroundDrawable(drawable);
//    }
//
//    public void setMyProgressDrawableUseGradients(boolean useGradients) {
//        checkIndeterminateDrawable().setUseGradients(useGradients);
//    }
//
//    public void progressiveStart() {
//        checkIndeterminateDrawable().progressiveStart();
//    }
//
//    public void progressiveStop() {
//        checkIndeterminateDrawable().progressiveStop();
//    }
    
}
