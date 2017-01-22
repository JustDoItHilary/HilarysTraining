package com.example.testswipemenulistviewdemo.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.testswipemenulistviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilary on 2016/11/11.
 */

public class IndicatorPagerView extends FrameLayout {
    private IndicatorView indicatorView;
    private Context mContext;

    public IndicatorPagerView(Context context) {
        this(context,null);
    }

    public IndicatorPagerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IndicatorPagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        setBackgroundColor(0x22ff0000);
        ViewPager viewPager = new ViewPager(mContext);
        FrameLayout.LayoutParams pagerParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(viewPager, pagerParams);
        indicatorView = new IndicatorView(mContext);
        final FrameLayout.LayoutParams indicatorParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 40, Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        indicatorParams.setMargins(0, 0, 0, 10);
        addView(indicatorView, indicatorParams);

        viewPager.setAdapter(new DrawablePagerAdapter(mContext));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                indicatorView.setSelectPonit(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class DrawablePagerAdapter extends PagerAdapter {
        private List<ImageView> mList = new ArrayList<>();
        private Context mContext;

        public DrawablePagerAdapter(Context mContext) {
            this.mContext = mContext;
            initList();
        }

        private void initList() {
            mList.clear();
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(R.mipmap.scenery);
            mList.add(imageView);
            ImageView imageView1 = new ImageView(mContext);
            imageView1.setImageResource(R.mipmap.ic_launcher);
            mList.add(imageView1);
            ImageView imageView2 = new ImageView(mContext);
            imageView2.setImageResource(R.mipmap.scenery);
            mList.add(imageView2);
        }

        @Override
        public int getCount() {
            indicatorView.setCount(mList.size());
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }
    }
}
