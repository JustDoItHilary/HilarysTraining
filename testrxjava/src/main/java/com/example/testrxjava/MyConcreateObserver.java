package com.example.testrxjava;

import android.util.Log;

/**
 * Created by Hilary on 2016/8/8.
 * 具体观察者
 */
public class MyConcreateObserver implements MyObserver{

    @Override
    public void update(String str) {
        Log.e("update",str);
    }
}
