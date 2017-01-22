package com.example.testswipemenulistviewdemo.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by Hilary on 2016/11/11.
 */

public class MyDrawable extends Drawable {
    private Paint paint;
    private Paint paintStroke;
    private Bitmap bitmap;
    private int radius;

    public MyDrawable(Bitmap bitmap) {
        this.bitmap = bitmap;
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        if (paint == null) {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(shader);
        }
        if (paintStroke==null){
            paintStroke=new Paint();
            paintStroke.setStyle(Paint.Style.STROKE);
            paintStroke.setColor(Color.RED);
            paintStroke.setAntiAlias(true);
            paintStroke.setStrokeWidth(2);
        }
        this.radius = Math.min(bitmap.getWidth(), bitmap.getHeight()) / 2;
    }

    @Override
    public void setFilterBitmap(boolean filter) {
        super.setFilterBitmap(filter);
    }

    @Override
    public void draw(Canvas canvas) {
        if (radius > 0) {
            canvas.drawCircle(bitmap.getWidth()/2, radius, radius, paint);
            canvas.drawCircle(bitmap.getWidth()/2, radius+2, radius, paintStroke);
//            canvas.drawColor(0x22ff0000);
        }
    }

    @Override
    public int getIntrinsicHeight() {
        return radius * 2;
    }

    @Override
    public int getIntrinsicWidth() {
        return radius * 2;
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
