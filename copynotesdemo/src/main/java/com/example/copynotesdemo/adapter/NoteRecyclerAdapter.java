package com.example.copynotesdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.copynotesdemo.model.NoteEntity;
import com.example.copynotesdemo.view.TextAndCheckCell;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Hilary on 2016/11/26.
 * recyclerAdapter
 */

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.NoteListViewHolder> {
    private Context mContext;
    private List<NoteEntity> mList = new ArrayList<>();
    private NoteOnClickListener mListener;

    public NoteRecyclerAdapter(Context context, NoteOnClickListener listener) {
        this.mContext = context;
        this.mListener = listener;
        mList.clear();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:dd");
        String time = format.format(Calendar.getInstance().getTime());
        mList.add(new NoteEntity("便签。。。", time));
        mList.add(new NoteEntity("便签。。。", time));
        mList.add(new NoteEntity("便签。。。", time));
        mList.add(new NoteEntity("便签。。。", time));
        mList.add(new NoteEntity("便签。。。", time));
        mList.add(new NoteEntity("便签。。。", time));
        mList.add(new NoteEntity("便签。。。", time));
        mList.add(new NoteEntity("便签。。。", time));
        mList.add(new NoteEntity("便签。。。", time));
        mList.add(new NoteEntity("便签。。。", time));
    }

    public void addList(List<NoteEntity> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public NoteListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteListViewHolder(new TextAndCheckCell(mContext));
    }

    @Override
    public void onBindViewHolder(NoteListViewHolder holder, int position) {
        TextAndCheckCell cell = (TextAndCheckCell) holder.itemView;
        final NoteEntity entity = mList.get(position);
        cell.setData(entity.getMess(), entity.getTime(), 0, true);
        cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(entity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class NoteListViewHolder extends RecyclerView.ViewHolder {

        public NoteListViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface NoteOnClickListener {
        void onClick(NoteEntity entity);
    }

}
