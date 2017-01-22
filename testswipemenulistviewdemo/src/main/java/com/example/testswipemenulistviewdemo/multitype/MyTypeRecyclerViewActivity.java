package com.example.testswipemenulistviewdemo.multitype;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilary on 2016/11/16.
 * 多类型 recycleview
 */

public class MyTypeRecyclerViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout content = new FrameLayout(this);
        setContentView(content, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(this);
        content.addView(swipeRefreshLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        RecyclerView recyclerView = new RecyclerView(this);
        swipeRefreshLayout.addView(recyclerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyTypeAdapter(this));
    }

    private class MyTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context mContext;
        private List<Integer> mList=new ArrayList<>();

        public MyTypeAdapter(Context context) {
            this.mContext = context;
            initList();
        }

        private void initList() {
            mList.clear();
            for (int i=0;i<6;i++){
                mList.add(i);
            }
        }

        @Override
        public int getItemViewType(int position) {
            return mList.get(position);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return InputType.getView(viewType,mContext);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyItemView)holder.itemView).onBindViewHolder();
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }




//    switch (viewType) {
//        case 0:
//            FrameLayout frame = new FrameLayout(mContext);
//            frame.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 104));
//            ImageView imageView = new ImageView(mContext);
//            frame.addView(imageView, new FrameLayout.LayoutParams(56, 56, Gravity.TOP | Gravity.CENTER_HORIZONTAL));
//            TextView textView = new TextView(mContext);
//            textView.setTextSize(18);
//            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 32, Gravity.CENTER_HORIZONTAL);
//            params.setMargins(0, 16, 0, 16);
//            frame.addView(textView, params);
//            viewHolder = new MyViewHolder(frame);
//            break;
//        case 1:
//            FrameLayout frame2 = new FrameLayout(mContext);
//            frame2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 96));
//            TextView textView21 = new TextView(mContext);
//            textView21.setTextSize(18);
//            FrameLayout.LayoutParams params21 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 32, Gravity.CENTER_HORIZONTAL);
//            params21.setMargins(0, 0, 0, 16);
//            frame2.addView(textView21, params21);
//            TextView textView22 = new TextView(mContext);
//            textView22.setTextSize(18);
//            FrameLayout.LayoutParams params22 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 32, Gravity.CENTER_HORIZONTAL);
//            params22.setMargins(0, 48, 0, 16);
//            frame2.addView(textView22, params22);
//            viewHolder = new MyViewHolder(frame2);
//            break;
//        case 2:
//            break;
//        case 3:
//            break;
//        case 4:
//            break;
//        case 5:
//            break;
//    }
}
