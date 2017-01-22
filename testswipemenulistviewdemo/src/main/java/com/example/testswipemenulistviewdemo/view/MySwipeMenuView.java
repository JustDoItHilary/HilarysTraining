package com.example.testswipemenulistviewdemo.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.testswipemenulistviewdemo.R;

/**
 * Created by Hilary on 2016/10/21.
 * 自定义listview左滑显示出来的布局
 */

public class MySwipeMenuView extends LinearLayout {
    private Context mContext;

    public MySwipeMenuView(Context context) {
        super(context);
        this.mContext=context;
        init();
    }

    public MySwipeMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        init();
    }

    public MySwipeMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        init();
    }

    private void init() {
        TextView open=new TextView(mContext);
        LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(dp2px(60),dp2px(60));
        open.setLayoutParams(params2);
        open.setText("Open");
        open.setBackgroundColor(Color.parseColor("#ffccff"));
        open.setGravity(Gravity.CENTER);
        open.setTextSize(20.f);
        addView(open);
        ImageView image=new ImageView(mContext);
        image.setColorFilter(Color.parseColor("#ffccdd"), PorterDuff.Mode.DST_ATOP);
        image.setImageResource(R.mipmap.ic_launcher);
        image.setLayoutParams(params2);
        addView(image);
    }

    private int dp2px(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        int px = (int) (dp * scale + 0.5f);
        return px;
    }
}
