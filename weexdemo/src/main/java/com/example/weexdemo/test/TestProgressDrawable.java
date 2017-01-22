package com.example.weexdemo.test;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Animatable;
import android.os.SystemClock;
import android.view.animation.Interpolator;

/**
 * Created by Hilary
 * on 2017/1/17.
 */

public class TestProgressDrawable extends Drawable implements Animatable {

    private static final long FRAME_DURATION = 1000 / 60;
    private final static float OFFSET_PER_FRAME = 0.01f;

    private Interpolator mInterpolator;
    private Rect mBounds;
    private Paint mPaint;
    private int[] mColors;
    private int mColorsIndex;
    private boolean mRunning;
    private float mCurrentOffset;
    private float mFinishingOffset;
    private int mSeparatorLength;
    private int mSectionsCount;
    private float mSpeed;
    private float mProgressiveStartSpeed;
    private float mProgressiveStopSpeed;
    private boolean mReversed;
    private boolean mNewTurn;
    private boolean mMirrorMode;
    private float mMaxOffset;
    private boolean mFinishing;
    private boolean mProgressiveStartActivated;
    private int mStartSection;
    private int mCurrentSections;
    private float mStrokeWidth;
    private Drawable mBackgroundDrawable;
    private boolean mUseGradients;
    private int[] mLinearGradientColors;
    private float[] mLinearGradientPositions;

    public TestProgressDrawable(Interpolator interpolator,
                                int sectionsCount,
                                int separatorLength,
                                int[] colors,
                                int color,
                                float strokeWidth,
                                float speed,
                                float progressiveStartSpeed,
                                float progressiveStopSpeed,
                                boolean reversed,
                                boolean mirrorMode,
                                boolean progressiveStartActivated) {
        mRunning = false;
        mInterpolator = interpolator;
        mSectionsCount = sectionsCount;
        mStartSection = 0;
        mCurrentSections = mSectionsCount;
        mSeparatorLength = separatorLength;
        mSpeed = speed;
        mProgressiveStartSpeed = progressiveStartSpeed;
        mProgressiveStopSpeed = progressiveStopSpeed;
        mReversed = reversed;
        mColors =  new int[]{color};
        mColorsIndex = 0;
        mMirrorMode = mirrorMode;
        mFinishing = false;
        mStrokeWidth = strokeWidth;

        mMaxOffset = 1f / mSectionsCount;

        mPaint = new Paint();
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setDither(false);
        mPaint.setAntiAlias(false);

        mProgressiveStartActivated = progressiveStartActivated;
    }

    @Override
    public void start() {
//        if (mProgressiveStartActivated) {
//            resetProgressiveStart(0);
//        }
        if (isRunning()) return;
        scheduleSelf(mUpdater, SystemClock.uptimeMillis() + FRAME_DURATION);
        invalidateSelf();
    }

    @Override
    public void stop() {
        if (!isRunning()) return;
        mRunning = false;
        unscheduleSelf(mUpdater);
    }

    @Override
    public void scheduleSelf(Runnable what, long when) {
        mRunning = true;
        super.scheduleSelf(what, when);
    }

    private final Runnable mUpdater = new Runnable() {

        @Override
        public void run() {
            mCurrentOffset += (OFFSET_PER_FRAME * mSpeed);

            if (mCurrentOffset >= mMaxOffset) {
                mNewTurn = true;
                mCurrentOffset -= mMaxOffset;
            }

            if (isRunning())
                scheduleSelf(mUpdater, SystemClock.uptimeMillis() + FRAME_DURATION);

            invalidateSelf();
        }
    };

    @Override
    public boolean isRunning() {
        return mRunning;
    }

    public boolean isFinishing() {
        return mFinishing;
    }

    @Override
    public void draw(Canvas canvas) {
        mBounds = getBounds();
        canvas.clipRect(mBounds);

        //new turn
        if (mNewTurn) {
            mColorsIndex = decrementColor(mColorsIndex);
            mNewTurn = false;

            if (isFinishing()) {
                mStartSection++;

                if (mStartSection > mSectionsCount) {
                    stop();
                    return;
                }
            }
            if (mCurrentSections < mSectionsCount) {
                mCurrentSections++;
            }
        }

        drawStrokes(canvas);
    }

    private void drawStrokes(Canvas canvas) {
        if (mReversed) {
            canvas.translate(mBounds.width(), 0);
            canvas.scale(-1, 1);
        }

        float prevValue = 0f;
        int boundsWidth = mBounds.width();
        if (mMirrorMode) boundsWidth /= 2;
        int width = boundsWidth + mSeparatorLength + mSectionsCount;
        int centerY = mBounds.centerY();
        float xSectionWidth = 1f / mSectionsCount;

        float startX;
        float endX;
        float firstX = 0;
        float lastX = 0;
        float prev;
        float end;
        float spaceLength;
        float xOffset;
        float ratioSectionWidth;
        float sectionWidth;
        float drawLength;
        int currentIndexColor = mColorsIndex;

        if (mStartSection == mCurrentSections && mCurrentSections == mSectionsCount) {
            firstX = canvas.getWidth();
        }

        for (int i = 0; i <= mCurrentSections; ++i) {
            xOffset = xSectionWidth * i + mCurrentOffset;
            prev = Math.max(0f, xOffset - xSectionWidth);
            ratioSectionWidth = Math.abs(mInterpolator.getInterpolation(prev) -
                    mInterpolator.getInterpolation(Math.min(xOffset, 1f)));
            sectionWidth = (int) (width * ratioSectionWidth);

            if (sectionWidth + prev < width)
                spaceLength = Math.min(sectionWidth, mSeparatorLength);
            else
                spaceLength = 0f;

            drawLength = sectionWidth > spaceLength ? sectionWidth - spaceLength : 0;
            end = prevValue + drawLength;
            if (end > prevValue && i >= mStartSection) {
                float xFinishingOffset = mInterpolator.getInterpolation(Math.min(mFinishingOffset, 1f));
                startX = Math.max(xFinishingOffset * width, Math.min(boundsWidth, prevValue));
                endX = Math.min(boundsWidth, end);
                drawLine(canvas, boundsWidth, startX, centerY, endX, centerY, currentIndexColor);
                if (i == mStartSection) { // first loop
                    firstX = startX - mSeparatorLength;
                }
            }
            if (i == mCurrentSections) {
                lastX = prevValue + sectionWidth; //because we want to keep the separator effect
            }

            prevValue = end + spaceLength;
            currentIndexColor = incrementColor(currentIndexColor);
        }

    }

    private void drawLine(Canvas canvas, int canvasWidth, float startX, float startY, float stopX, float stopY, int currentIndexColor) {
        mPaint.setColor(mColors[currentIndexColor]);

        canvas.drawLine(startX, startY, stopX, stopY, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    private int incrementColor(int colorIndex) {
        ++colorIndex;
        if (colorIndex >= mColors.length) colorIndex = 0;
        return colorIndex;
    }

    private int decrementColor(int colorIndex) {
        --colorIndex;
        if (colorIndex < 0) colorIndex = mColors.length - 1;
        return colorIndex;
    }
}
