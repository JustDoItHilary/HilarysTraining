package com.example.testswipemenulistviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testswipemenulistviewdemo.R;

/**
 * Created by Hilary on 2016/11/10.
 * actionBar
 */

public class MyActionBarView extends FrameLayout {

    public MyActionBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyActionBarView(Context context) {
        this(context, null);
    }

    public MyActionBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setBackground(context.getDrawable(R.drawable.shape_action));
        FrameLayout layout = new FrameLayout(context);
        FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
        addView(layout, params);
        TextView textView = new TextView(context);
        LayoutParams textviewparams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 40, Gravity.TOP | Gravity.CENTER_VERTICAL);
        textviewparams.setMargins(4, 50, 4, 0);
        addView(textView, textviewparams);

        ImageView icon = new ImageView(context);
        LayoutParams iconParams = new LayoutParams(40, 40, Gravity.START | Gravity.CENTER_VERTICAL);
        iconParams.setMargins(4, 10, 0, 0);
        layout.addView(icon, iconParams);
        TextView textView1 = new TextView(context);
        LayoutParams tvParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 40, Gravity.START | Gravity.CENTER_VERTICAL);
        tvParams.setMargins(48, 10, 48, 0);
        layout.addView(textView1, tvParams);
        ImageView icon1 = new ImageView(context);
        LayoutParams iconParams1 = new LayoutParams(40, 40, Gravity.END | Gravity.CENTER_VERTICAL);
        iconParams.setMargins(0, 10, 4, 0);
        layout.addView(icon1, iconParams1);

        icon.setImageResource(R.mipmap.ic_launcher);
        icon1.setImageResource(R.mipmap.ic_launcher);
        textView1.setHint("太阳当空照，花儿对我笑。。。");
        textView.setText("小鸟说：早早早，你为什么背着小书包。。。");
    }


}
