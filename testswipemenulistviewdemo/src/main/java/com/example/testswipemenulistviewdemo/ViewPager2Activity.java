package com.example.testswipemenulistviewdemo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.testswipemenulistviewdemo.fragment.FirstFragment;
import com.example.testswipemenulistviewdemo.fragment.SecondFragment;
import com.example.testswipemenulistviewdemo.view.MyActionBarView;
import com.example.testswipemenulistviewdemo.view.ViewPagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilary on 2016/11/7.
 * HOME
 */

public class ViewPager2Activity extends Activity {
    //    List<View> mList = new ArrayList<>();
    List<Fragment> mFragList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private ViewPager mViewPager;
    private MyViewPagerAdapter mAdapter;
    private ViewPagerTitleView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_viewpager2);
        FrameLayout root = new FrameLayout(this);
        setContentView(root, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        FrameLayout actionBarLayout=new FrameLayout(this);
        FrameLayout.LayoutParams barParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120);
        actionBarLayout.setLayoutParams(barParams);
        actionBarLayout.setBackgroundColor(getResources().getColor(R.color.bar));
        MyActionBarView actionBarView = new MyActionBarView(this);
        FrameLayout.LayoutParams barParams1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
        barParams1.setMargins(10,0,10,10);
        actionBarLayout.addView(actionBarView,barParams1);
        root.addView(actionBarLayout);

         mViewPager = new ViewPager(this);
        FrameLayout.LayoutParams pagerParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        pagerParams.setMargins(0, 120, 0, 60);
        mViewPager.setLayoutParams(pagerParams);
        root.addView(mViewPager);

         mTitleView = new ViewPagerTitleView(this);
        FrameLayout.LayoutParams titleParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100, Gravity.BOTTOM);
        root.addView(mTitleView,titleParams);

        initList();
        initViewPager();
    }

    private void initList() {
        mTitleList.add("人之初");
        mTitleList.add("性本善");
        mTitleList.add("性相近");
//        mList.add(View.inflate(this, R.layout.page1, null));
//        mList.add(View.inflate(this, R.layout.page2, null));
//        mList.add(View.inflate(this, R.layout.page3, null));
        mFragList.add(new FirstFragment());
        mFragList.add(new SecondFragment());
        mFragList.add(new FirstFragment());
    }

    private void initViewPager() {
//        mTitleView = (ViewPagerTitleView) findViewById(R.id.titleView);
//        mViewPager = (ViewPager) findViewById(R.id.viewpager);
//        mAdapter = new MyViewPagerAdapter(getFragmentManager(), mList);
        mAdapter = new MyViewPagerAdapter(this.getFragmentManager(), mFragList);
        mViewPager.setAdapter(mAdapter);
        mTitleView.setViewPager(mViewPager);
        mViewPager.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
    }

    class MyViewPagerAdapter extends PagerAdapter {
        private FragmentManager mFragManager;
        private List<Fragment> mList = new ArrayList<>();

        MyViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> mList) {
            super();
            this.mFragManager = fragmentManager;
            this.mList = mList;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = mList.get(position);
            if (!fragment.isAdded()) {
                FragmentTransaction saction = mFragManager.beginTransaction();
                saction.add(fragment, fragment.getClass().getSimpleName());
                //只有commit才会执行
                saction.commit();
                //立即执行 移除fragment时需要addtobackstack（）否则无法回退（被销毁）
                mFragManager.executePendingTransactions();
            }
            View view = fragment.getView();
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position).getView());
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }
}
