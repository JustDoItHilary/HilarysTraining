package com.example.copynotesdemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.plugins.RxJavaObservableExecutionHook;
import rx.schedulers.Schedulers;

/**
 * Created by Hilary
 * on 2017/1/4.
 */

public class TestActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {

                    }
                });
    }
}
