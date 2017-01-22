package com.example.testclockdemo;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Hilary on 2016/9/1.
 */
public class AlarmService extends Service {

    private AlarmReceiver mAlarmReceiver;

    private static class Authenticator extends AbstractAccountAuthenticator {
        private final Context context;

        public Authenticator(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options)
                throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle getAccountRemovalAllowed(AccountAuthenticatorResponse response, Account account) throws NetworkErrorException {
            return super.getAccountRemovalAllowed(response, account);
        }

        @Override
        public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
            return null;
        }

        @Override
        public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options)
                throws NetworkErrorException {
            return null;
        }

        @Override
        public String getAuthTokenLabel(String authTokenType) {
            return null;
        }

        @Override
        public Bundle hasFeatures(AccountAuthenticatorResponse response,
                                  Account account, String[] features)
                throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options)
                throws NetworkErrorException {
            return null;
        }

    }

    private static Authenticator authenticator = null;

    protected Authenticator getAuthenticator() {
        if (authenticator == null) {
            authenticator = new Authenticator(this);
        }
        return authenticator;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (intent.getAction().equals(AccountManager.ACTION_AUTHENTICATOR_INTENT)) {
            Log.e("didi","aaaaaaaaaaaaaaaaaaaaaa");
            return getAuthenticator().getIBinder();
        } else {
            return null;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter=new IntentFilter();
        Log.e("didi","oncreate--------service");
        intentFilter.addAction("com.example.clock");
        mAlarmReceiver=new AlarmReceiver();
        registerReceiver(mAlarmReceiver,intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("didi","start----service");
        String hour = intent.getStringExtra("hour");
        String minute = intent.getStringExtra("minute");
        int id = intent.getIntExtra("id", 0);
        AlarmManager alarmManager = (AlarmManager) getApplication().getSystemService(ALARM_SERVICE);
        Intent intent1 = new Intent("com.example.clock");
        //PendingIntent是对Intent 的封装，是一个token（节点）的对象引用，从池中获取
        // 一个参数是上下文
        //第二个参数是requestCode,如果requestCode 的值与从池中获取到的PendingIntent的值相同，将替换掉之前的PendingIntent中的值，重新使用该PendingIntent，使之前的PendingIntent失效
        //第三个参数是Intent，里面存储了需要传递的数据
        //第四个参数是PendingIntent所属的类型，总共包括四种类型，FLAG_CANCEL_CURRENT指获取到后将之前的销毁新建一个   FLAG_UPDATE_CURRENT 将之前的数据更新  FLAG_ONE_SHOT 只用一次，使用完后销毁 FLAG_NO_CREATE池中有一样的返回，如果没有返回null,并返回异常
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplication(), id, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mAlarmReceiver);
        Log.e("didididididididi","destroy-----service");

    }
}
