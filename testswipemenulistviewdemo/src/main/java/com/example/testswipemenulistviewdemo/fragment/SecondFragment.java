package com.example.testswipemenulistviewdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.testswipemenulistviewdemo.R;

/**
 * Created by Hilary on 2016/11/3.
 */

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.activity_fragment1,container,false);
        ImageView imageView=(ImageView) layout.findViewById(R.id.image);
        imageView.setImageResource(R.drawable.drawable1);
        return layout;
    }
}
