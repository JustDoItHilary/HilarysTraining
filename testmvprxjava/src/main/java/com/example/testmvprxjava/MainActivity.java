package com.example.testmvprxjava;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity implements UserView {

    private UserPresenter mUserPresenter;
    private TextView mTextView;
    private final List<User> mUsers = new ArrayList<>();
    private Handler drawTextHandler;
    private  CompositeSubscription mCompositeSubsciption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawTextHandler = new Handler(getApplicationContext().getMainLooper());
        setContentView(R.layout.activity_main);
        mCompositeSubsciption=new CompositeSubscription();

        mTextView = (TextView) findViewById(R.id.text);
        mUserPresenter = new UserPresenter(this);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserPresenter.getUser();
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserPresenter.getUserObject();
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Subscriber subscriber3=getSubscribe();
                getInt(0).subscribe(subscriber3);
                mCompositeSubsciption.add(subscriber3);
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListUseInterval();
            }
        });
        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserPresenter.userConcat();
            }
        });
//        new UserModel().getUser().observeOn(Schedulers.newThread())
//                .subscribe(new Action1<List<User>>() {
//                    @Override
//                    public void call(List<User> users) {
//                        mUsers.clear();
//                        mUsers.addAll(users);
//                        popUser(0);
//                    }
//                });

//        new UserModel().getUser().subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.computation())
//                .concatMap(new Func1<User, Observable<User>>() {
//            @Override
//            public Observable<User> call(User user) {
//                return Observable.just(user);
//            }
//        }).window(2, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Observable<User>>() {
//                    @Override
//                    public void call(Observable<User> userObservable) {
//                        userObservable.subscribe(new Action1<User>() {
//                            @Override
//                            public void call(User user) {
//                                Log.e("User","User print begin");
//                                mTextView.append(user.getUserName()+"\n");
//                                Log.e("User","User is "+user.getUserName());
//                            }
//                        });
//                    }
//                });

    }

    private void setListUseInterval() {
        //interval 每隔一段时间发射一个数字，运行在computation Scheduler,所以如果在view中显示需要在主线程订阅
        final Observable observable = Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread());
        final Subscriber subscriber = new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "This use Interval!", Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                if (aLong < 3) {
                    mTextView.setText(aLong.toString());
                    Log.e("interval", "This use Interval!" + aLong);
                }else {
                    onCompleted();
                }
            }
        };
        observable.subscribe(subscriber);
        mCompositeSubsciption.add(subscriber);
    }

    private Subscriber<Object> getSubscribe() {
        return new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                mTextView.setText("COMPLETED" + "\n");
            }

            @Override
            public void onError(Throwable e) {
                mTextView.setText("ERROR");
            }

            @Override
            public void onNext(final Object o) {
                drawTextHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.append("NEXT:" + o + "\n");
                    }
                }, 300);
            }
        };
    }

    private Observable<Integer> getInt(int i) {
        if (i < 0 || i > 10) {
            return null;
        }
        return Observable.just(i).concatMap(new Func1<Integer, Observable<? extends Integer>>() {
            @Override
            public Observable<? extends Integer> call(Integer integer) {
                Observable observable;
                if (integer == 10) {
                    observable = Observable.just(10);
                } else {
                    observable = Observable.just(integer).concatWith(getInt(integer + 1));
                }
                return observable;
            }
        });
    }


    private boolean popUser(final int index) {
        if (index < 0 || index >= mUsers.size()) {
            return false;
        }
        drawTextHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTextView.append(mUsers.get(index).getUserName() + "\n");
                //递归算法
                popUser(index + 1);
            }
        }, 300);
        return true;
    }

    @Override
    public void setUser(List<User> users) {
        mUsers.clear();
        mUsers.addAll(users);
        popUser(0);
    }

    @Override
    public void setUserObject(final User user) {
        //Thread.sleep();使当前方法所在的线程睡眠
        //Thread.sleep();占用线程资源,在sleep时挂起,仍不允许其他的调用,阻碍了系统线程的调度
        //Thread.sleep()只适用于"有限度"的循环场景,即最多重试N次或者倒计时等
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        drawTextHandler.post(new Runnable() {
            @Override
            public void run() {
                mTextView.setText(user.getUserName());
            }
        });
//        drawTextHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mTextView.setText(user.getUserName());
//                Log.e("UserObject","userObject is "+ user.getUserName());
//            }
//        },1000);
    }

    @Override
    public void setUserList(List<User> users) {
        mUsers.clear();
        mUsers.addAll(users);
        indexUser();
    }

    @Override
    public void serListUseConcat(final Integer integer) {
        //不成功，并不能一个一个的显示出来，只显示最后一个
                mTextView.setText(Long.toString(integer));
                Log.e("CONCAT","Concat " + integer );
    }


    public void indexUser() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeSubsciption.unsubscribe();
    }
}
