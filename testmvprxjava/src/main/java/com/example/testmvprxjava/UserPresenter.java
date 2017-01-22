package com.example.testmvprxjava;


import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Hilary on 2016/8/3.
 * 控制器
 */
public class UserPresenter {

    private UserView mUserView;
    private UserModel mUserModel;


    public UserPresenter(UserView userView) {
        this.mUserView = userView;
        mUserModel= new UserModel();
    }

    public void getUser() {

//        Observable<User> observable=Observable.create(new Observable.OnSubscribe<User>() {
//                    @Override
//                    public void call(Subscriber<? super User> subscriber) {
//                        User user=new User();
//                        user.setUserName("HAHAHAHAHAHA");
//                        subscriber.onNext(user);
//            }
//        });

//        userModel.getUser().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<User>() {
//            @Override
//            public void onCompleted() {
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(User user) {
//                mUserView.setUser(user);
//            }
//        });
//        userModel.getUser().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<User>() {
//            @Override
//            public void call(final User user) {
//                SystemClock.sleep(1000);
//
//                mUserView.setUser(user);
////                new android.os.Handler().postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
////                        mUserView.setUser(user);
////                        Log.e("User","User is "+user.getUserName());
////                    }
////                },2000);
//            }
//        });
        mUserModel.getUser().observeOn(Schedulers.newThread()).subscribe(new Action1<List<User>>() {
            @Override
            public void call(List<User> users) {
                mUserView.setUser(users);
            }
        });
    }

    public void getUserObject(){
        mUserModel.getUserObject().observeOn(Schedulers.newThread()).subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                mUserView.setUserObject(user);
            }
        });
    }

    public void userConcat() {
        Observable.concat(Observable.just(1,2,3),Observable.just(4,5,6),
                Observable.just(7,8,9))
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        mUserView.serListUseConcat(integer);
                    }
                });
    }

//    public Observable<List<User>> getUserList() {
//        return mUserModel.getUser().observeOn(Schedulers.newThread())
//                .concatWith();
//    }
}
