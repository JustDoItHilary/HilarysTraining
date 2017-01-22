package com.example.testswipemenulistviewdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.testswipemenulistviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilary on 2016/11/11.
 */

public class MyDrawableLayout extends FrameLayout {
    private List<Bitmap> mList=new ArrayList<>();
    private Context mContext;

    public MyDrawableLayout(Context context) {
        this(context, null);
    }

    public MyDrawableLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyDrawableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        initList();
        setWillNotDraw(false);
    }

    private void initList() {
        mList.clear();
        mList.add(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.beauty1));
        mList.add(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.moon_grey));
        mList.add(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.scenery));
        mList.add(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.beauty2));
        mList.add(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.beatch_grey));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth()/mList.size();
        for (int i=0;i<mList.size();i++){
            ImageView imageView=new ImageView(mContext);
            imageView.setImageDrawable(new MyDrawable(mList.get(i)));
            imageView.setColorFilter(0x6600ffff, PorterDuff.Mode.SRC_ATOP);
//            imageView.setColorFilter(0x66ff0000,PorterDuff.Mode.SRC_OUT);
            LayoutParams params=new LayoutParams(60,60,Gravity.LEFT);
            params.setMargins(i*width+width/2-30,0,0,0);
            addView(imageView,params);
        }
        setWillNotDraw(true);

    }
}
