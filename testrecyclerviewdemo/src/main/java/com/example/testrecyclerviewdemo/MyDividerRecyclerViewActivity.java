package com.example.testrecyclerviewdemo;

import android.app.Activity;
import android.content.Context;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilary on 2016/9/9.
 * divider-recyclerView
 */
public class MyDividerRecyclerViewActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<MyCheckEntity> mList = new ArrayList<>();
    private MyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divider_recyclerview);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        initList(0);
        mAdapter = new MyAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<MyCheckEntity> initList(int sum) {
        List<MyCheckEntity> list = new ArrayList<>();
        for (int i = sum; i < sum + 100; i++) {
            list.add(new MyCheckEntity("太阳当空照 花儿对我笑。。。" + i, false));
        }
        mList.addAll(list);
        return list;
    }

    private class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        private Context mContext;

        MyAdapter(Context context) {
            this.mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            return new ViewHolder(linearLayout);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            if (position==2){
                holder.setVisibility(View.GONE);
            }
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mList.get(position).setEnable(isChecked);
                }
            });
            holder.tv.setText(mList.get(position).getMess());
            holder.checkBox.setChecked(mList.get(position).getEable());
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private CheckBox checkBox;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view=itemView;
            Context mContext = itemView.getContext();
            tv = new TextView(mContext);
            tv.setGravity(Gravity.CENTER);
            ((ViewGroup) itemView).addView(tv, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0F));
            checkBox = new CheckBox(mContext);
            ((ViewGroup) itemView).addView(checkBox, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        }

        public void setVisibility(int b) {
//            view.setVisibility(b);
//            int width=view.getWidth();     //0
//            int height=view.getHeight();  //0
//            ViewGroup.LayoutParams params=view.getLayoutParams();    //null
//            Log.e("list_item",width+"\n"+height+"\n"+params);
            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1);
//            params.height= (int) getResources().getDimension(R.dimen.listview_item_height);
            view.setLayoutParams(params);
        }
    }


    private class MyCheckEntity {
        private String mess;
        private boolean enable;

        public MyCheckEntity(String s, boolean b) {
            this.mess = s;
            this.enable = b;
        }

        public String getMess() {
            return mess;
        }

        public void setMess(String mess) {
            this.mess = mess;
        }

        public boolean getEable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }
    }
}
