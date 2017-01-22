package com.example.testswipemenulistviewdemo.multitype;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Hilary on 2016/11/17.
 * type
 */

public class InputType {
    protected static final int TYPE_1 = 0;
    protected static final int TYPE_2 = 1;
    protected static final int TYPE_3 = 2;
    protected static final int TYPE_4 = 3;
    protected static final int TYPE_5 = 4;
    protected static final int TYPE_6 = 5;
    protected String type;

    public InputType() {
    }

    public InputType(String type_1) {
        this.type = type_1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static RecyclerView.ViewHolder getView(int viewType, Context context) {
        switch (viewType){
            case TYPE_1:
                return new FirstView(context).onCreateViewHolder();
            case TYPE_2:
                return new FirstView(context).onCreateViewHolder();
            case TYPE_3:
                return new FirstView(context).onCreateViewHolder();
            case TYPE_4:
                return new FirstView(context).onCreateViewHolder();
            case TYPE_5:
                return new FirstView(context).onCreateViewHolder();
            case TYPE_6:
                return new FirstView(context).onCreateViewHolder();
        }
        return null;
    }

}
