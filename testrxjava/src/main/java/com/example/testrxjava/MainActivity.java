package com.example.testrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;


public class MainActivity extends Activity {
    private TextView mTextView;
    Subscriber<String> subscriber;
    Observable<String> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                observable.subscribe(subscriber);
                Log.e("update","MIMIMI");
            }
        };


        MyObserver myObserver1 = new MyConcreateObserver();
        MyObserver myObserver2 = new MyConcreateObserver();
        MyObserver myObserver3 = new MyConcreateObserver();

        final MyObservable myObservable = new MyConcreateObservable();

        myObservable.add(myObserver1) ;
        myObservable.add(myObserver2);
        myObservable.add(myObserver3);

        findViewById(R.id.btn_observers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myObservable.notifyMyObserver("BABANANA");
            }
        });

        mTextView = (TextView) findViewById(R.id.text);
        observable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("MIMIMIMIMIMI");
                    }
                }
        );
        findViewById(R.id.btn_observer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg=Message.obtain();
                handler.sendMessageDelayed(msg,2000);
            }
        });
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT);
            }

            @Override
            public void onNext(String s) {
                mTextView.setText(s);
            }
        };


    }

}
