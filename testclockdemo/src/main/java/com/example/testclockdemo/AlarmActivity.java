package com.example.testclockdemo;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Hilary on 2016/9/1.
 *
 */
public class AlarmActivity extends Activity {

    private AlarmManager alarmManager;
    private Calendar calendar;
    private PendingIntent pendingIntent;
    private Intent mServiceIntent;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        AccountManager accountManager=AccountManager.get(this);
        Account account=new Account("叮咚",getPackageName());
        accountManager.addAccountExplicitly(account,"123",null);
        findViewById(R.id.btn_setClock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hour = ((EditText) findViewById(R.id.edit_hour)).getText().toString();
                String minute = ((EditText) findViewById(R.id.edit_minute)).getText().toString();

                id += 1;
                mServiceIntent = new Intent(AlarmActivity.this, AlarmService.class);
                mServiceIntent.putExtra("hour", hour);
                mServiceIntent.putExtra("minute", minute);
                mServiceIntent.putExtra("id", id);
                AlarmActivity.this.startService(mServiceIntent);
//                setClock(Integer.parseInt(hour),Integer.parseInt(minute));
//                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            }
        });
        findViewById(R.id.btn_setExactClock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hour = ((EditText) findViewById(R.id.edit_hour)).getText().toString();
                String minute = ((EditText) findViewById(R.id.edit_minute)).getText().toString();
                setClock(Integer.parseInt(hour), Integer.parseInt(minute));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                }
            }
        });
        findViewById(R.id.btn_closeService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mServiceIntent!=null){
                AlarmActivity.this.stopService(mServiceIntent);}
            }
        });
    }

    private void setClock(int h, int m) {
        alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE, m);
        Intent intent = new Intent("com.example.clock");
        intent.putExtra("flag", 0);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("didididididididi", "destroy----------activity");
    }
}
