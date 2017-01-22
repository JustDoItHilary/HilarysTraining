package com.example.testrxjava;

/**
 * Created by Hilary on 2016/8/8.
 * 抽象被观察者
 */
public interface MyObservable  {

    void add(MyObserver observer);
    void remove(MyObserver observer);
    void notifyMyObserver(String str);
}
