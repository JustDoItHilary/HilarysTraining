package com.example.testrxjava;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilary on 2016/8/8.
 * 具体观察者
 */
public class MyConcreateObservable implements MyObservable {
    private List<MyObserver> observers=new ArrayList<>();

    @Override
    public void add(MyObserver observer) {
        observers.add(observer);
    }
    @Override
    public void remove(MyObserver observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyMyObserver(String str) {
        for (MyObserver observer:observers){
            observer.update(str);
        }
    }
}
