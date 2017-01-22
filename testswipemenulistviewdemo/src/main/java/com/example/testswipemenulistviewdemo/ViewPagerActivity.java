package com.example.testswipemenulistviewdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilary on 2016/11/3.
 * viewpager
 */

//想要标题固定需要自定义View 实现辩题效果
public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    //    private PagerTabStrip mViewPagerTabStrip;
    List<View> mList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private MyViewPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_viewpager);
//        mViewPagerTabStrip = (PagerTabStrip) findViewById(R.id.pagerTabStrip);
        initList();
//        mViewPagerTabStrip.setDrawFullUnderline(true);
//        mViewPagerTabStrip.setTabIndicatorColor(this.getResources().getColor(R.color.colorPrimary));

        // 得到窗体的默认宽高
        initViewPager();
    }

    private void initList() {
        mTitleList.add("人之初");
        mTitleList.add("性本善");
        mTitleList.add("性相近");
        mList.add(View.inflate(this, R.layout.page1, null));
        mList.add(View.inflate(this, R.layout.page2, null));
        mList.add(View.inflate(this, R.layout.page3, null));
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        // 初始化三个page对应的视图
//        LinearLayout ll1 = new LinearLayout(this);
//        LinearLayout ll2 = new LinearLayout(this);
//        LinearLayout ll3 = new LinearLayout(this);
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        ll1.setLayoutParams(params);
////        ll1.setBackgroundColor(Color.RED);
//        mList.add(ll1);
//        ll2.setLayoutParams(params);
////        ll2.setBackgroundColor(Color.GREEN);
//        mList.add(ll2);
//        ll3.setLayoutParams(params);
////        ll3.setBackgroundColor(Color.BLUE);
//        mList.add(ll3);
        mAdapter = new MyViewPagerAdapter(getFragmentManager(), mList);
        mViewPager.setAdapter(mAdapter);
        // 设置首次加载的界面,如果希望首次加载的页面发生改变需要改变currIndex的初始值

    }

    class MyViewPagerAdapter extends PagerAdapter {
        private FragmentManager mFragManager;
        private List<View> mList = new ArrayList<>();

        MyViewPagerAdapter(FragmentManager fragmentManager, List<View> mList) {
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
            container.addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView(mList.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }
}
