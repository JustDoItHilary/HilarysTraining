package com.example.testswipemenulistviewdemo.multitype;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.telecom.PhoneAccount;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testswipemenulistviewdemo.R;
import com.example.testswipemenulistviewdemo.view.MyDrawable;

/**
 * Created by Hilary on 2016/11/17.
 */

public class FirstView extends MyItemView {
    private Context mContext;
    private TextView textView;
    private ImageView imageView;

    public FirstView(Context context) {
        this(context, null);
    }

    public FirstView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FirstView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        imageView = new ImageView(mContext);
        imageView.setImageDrawable(new MyDrawable(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.scenery)));
        LayoutParams params1 = new LayoutParams(200, 200, Gravity.CENTER);
        addView(imageView, params1);
        textView = new TextView(mContext);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(0x55ff0066);
        LayoutParams params2 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.setMargins(0, 280, 0, 10);
        addView(textView, params2);
    }

    @Override
    protected MyFirstViewHolder onCreateViewHolder() {

        return new MyFirstViewHolder(this);
    }

    @Override
    protected void onBindViewHolder() {
        textView.setText("太阳当空照 花儿对我笑。。。");
    }

    class MyFirstViewHolder extends RecyclerView.ViewHolder {

        public MyFirstViewHolder(View itemView) {
            super(itemView);

        }
    }
}
