package com.example.testrecyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/10/8.
 */

public class MyRippleActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple_layout);
        findViewById(R.id.tv_ripple_rect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ripple_rect","ripple_rect");
            }
        });
        findViewById(R.id.tv_ripple_circle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ripple_circle","ripple_circle");
            }
        });
        findViewById(R.id.btn_ripple_rect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ripple_rect","ripple_rect");
            }
        });
    }
}
