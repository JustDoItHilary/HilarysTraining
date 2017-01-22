package com.example.testjacksondemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private JacksonTest mJacksonTest;
    private JacksonHelper mJackson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter(this));
        mRecyclerView.addItemDecoration(new MyItemDecoration());

        mJacksonTest = new JacksonTest();
        mJackson = new JacksonHelper();
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private Context mContext;
        private List<String> mList = new ArrayList<>();

        public MyAdapter(Context context) {
            this.mContext = context;
            intList();
        }

        private void intList() {
            mList.clear();
            mList.add("createObjectJson");
            mList.add("createArrayJson");
            mList.add("jsonToObjects");
            mList.add("jsonToObject");
            mList.add("objectsToJson");
            mList.add("objectToJson");
            mList.add("createArrayJson");
            mList.add("createArrayJson");
            mList.add("toObject");
            mList.add("toObjects");
            mList.add("fromJson");
            mList.add("fromJsons");
            mList.add("toList");
            mList.add("getList");
            mList.add("tv");
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder viewHolder = new MyViewHolder(new TextView(mContext));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            TextView textView = (TextView) holder.itemView;
            textView.setText(mList.get(position));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        switch (position) {
                            case 0:
                                mList.set(14,mJacksonTest.createObjectJson());
                                notifyDataSetChanged();
                                break;
                            case 1:
                               mList.set(14,mJacksonTest.createArrayJson());
                                notifyDataSetChanged();
                                break;
                            case 2:
                               mList.set(14,mJacksonTest.jsonToObjects());
                                notifyDataSetChanged();
                                break;
                            case 3:
                               mList.set(14,mJacksonTest.jsonToObject());
                                notifyDataSetChanged();
                                break;
                            case 4:
                               mList.set(14,mJacksonTest.objectsToJson());
                                notifyDataSetChanged();
                                break;
                            case 5:
                               mList.set(14,mJacksonTest.objectToJson());
                                notifyDataSetChanged();
                                break;
                            case 6:
//                       mList.set(14,mJacksonTest.getJsonString1());
                                notifyDataSetChanged();
                                break;
                            case 7:
                               mList.set(14,mJacksonTest.createObjectJson());
                                notifyDataSetChanged();
                                break;
                            case 8:
                               mList.set(14,mJacksonTest.toObject());
                                notifyDataSetChanged();
                                break;
                            case 9:
                               mList.set(14,mJacksonTest.toObjects());
                                notifyDataSetChanged();
                                break;
                            case 10:
                               mList.set(14,mJacksonTest.fromJson());
                                notifyDataSetChanged();
                                break;
                            case 11:
                               mList.set(14,mJacksonTest.fromJsons());
                                notifyDataSetChanged();
                                break;
                            case 12:
                               mList.set(14,mJacksonTest.toList());
                                notifyDataSetChanged();
                                break;
                            case 13:
                               mList.set(14,mJacksonTest.getList());
                                notifyDataSetChanged();
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200));
        }
    }

    class MyItemDecoration extends RecyclerView.ItemDecoration {
        private Paint mPaint = new Paint();
        private int mTitleHeight=10;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, mTitleHeight, 0, 0);
            mPaint.setColor(0x22ff0000);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
//            RecyclerView.LayoutParams params=(RecyclerView.LayoutParams) parent.getLayoutParams();
            int count = parent.getChildCount();
            int left = parent.getLeft();
            int right = parent.getRight();
            for (int i = 0; i < count; i++) {
                View child = parent.getChildAt(i);
//                int position=params.getViewLayoutPosition();
                int top = child.getTop();
                c.drawRect(left, top - mTitleHeight, right, top, mPaint);
            }
        }
    }
}
