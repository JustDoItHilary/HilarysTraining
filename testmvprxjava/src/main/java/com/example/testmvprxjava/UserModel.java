package com.example.testmvprxjava;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Hilary on 2016/8/4.
 *
 */
public class UserModel {

//    public Observable<User> getUser(){
//        Observable <User> user =Observable.create(new Observable.OnSubscribe<User>() {
//            @Override
//            public void call(Subscriber<? super User> subscriber) {
//                User user =new User();
//                user.setUserName("lalalalalala");
//                subscriber.onNext(user);
//            }
//        });


    List<User> list = new ArrayList<>();

    private void setList() {
        for (int i = 0; i < 3; i++) {
            User user1 = new User();
            user1.setUserName("哈哈哈" + String.valueOf(i));
            list.add(user1);
        }

    }

    public Observable<List<User>> getUser() {
        list.clear();
        setList();
        Observable<List<User>> user = Observable.just(list).subscribeOn(Schedulers.io());
        return user;
    }

    public Observable<User> getUserObject(){
        list.clear();
        setList();
        Observable<User> user=Observable.from(list);
        return user;
    }
}
