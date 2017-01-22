package com.example.testrippledemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        TextView tv=(TextView)findViewById(R.id.tv);
//        Calendar calendar=Calendar.getInstance();
//
//        calendar.set(2016,9,9);
//        long week1=calendar.get(Calendar.WEEK_OF_YEAR);
//        long week4=calendar.get(Calendar.DAY_OF_WEEK);
//
//        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.set(2016,9,9);
//        long week2=calendar.get(Calendar.WEEK_OF_YEAR);
//        long week3=calendar.get(Calendar.DAY_OF_WEEK);
//
//        calendar.set(2016,12,9);
//        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//        String date=format.format(calendar.getTime());
//
//        calendar.set(2016,9,15);
//        long week5=calendar.get(Calendar.DAY_OF_WEEK);
//
//        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.set(2016,0,1);
//        long week6=calendar.get(Calendar.WEEK_OF_YEAR);
//
//        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.set(2016,0,3);
//        long week8=calendar.get(Calendar.WEEK_OF_YEAR);
//
//        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.set(2016,0,4);
//        long week9=calendar.get(Calendar.WEEK_OF_YEAR);
//
//        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.set(2016,0,10);
//        long week10=calendar.get(Calendar.WEEK_OF_YEAR);
//
//        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.set(2016,11,25);
//        long week11=calendar.get(Calendar.WEEK_OF_YEAR);
//
//        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.set(2016,11,30);
//        long week12=calendar.get(Calendar.WEEK_OF_YEAR);
//
//        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.set(2016,11,31);
//        long week7=calendar.get(Calendar.WEEK_OF_YEAR);
//
//        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        calendar.set(2017,0,1);
//        long week13=calendar.get(Calendar.WEEK_OF_YEAR);
//        tv.setText(String.valueOf(week1)+"\n"
//                +String.valueOf(week2)+"\n"
//                +String.valueOf(week4)+"\n"
//                +String.valueOf(week5)+"\n"
//                +String.valueOf(week3)+"\n"
//                +String.valueOf(week6)+"\n"
//                +String.valueOf(week8)+"\n"
//                +String.valueOf(week9)+"\n"
//                +String.valueOf(week10)+"\n"
//                +String.valueOf(week11)+"\n"
//                +String.valueOf(week12)+"\n"
//                +String.valueOf(week7)+"\n"
//                +String.valueOf(week13)+"\n"
//                +date);

//        setContentView(R.layout.activity_ripple_layout);

        setContentView(R.layout.activity_main);

//        DisplayMetrics metrics=new DisplayMetrics();
//        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        int width=metrics.widthPixels;
//        int height=metrics.heightPixels;
//        Log.e("metrics",width+"\n"+height);
//
//        Bitmap bitmap=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
//        Canvas canvas=new Canvas(bitmap);
//        Paint paint=new Paint();
//        paint.setColor(Color.CYAN);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(10);
//        canvas.drawRect(0,0,160,240,paint);
//        Drawable drawable=new BitmapDrawable(bitmap);
//        findViewById(R.id.root).setBackground(drawable);
        mTv=(TextView)findViewById(R.id.tv);
//        Log.e("metrics",mTv.getWidth()+"\n"+mTv.getHeight());

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int date=calendar.get(Calendar.DATE);
        Log.e("metrics",String.valueOf(date));
        int week=calendar.get(Calendar.WEEK_OF_YEAR)-1;

        Calendar cal= (Calendar) calendar.clone();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(year,Calendar.JANUARY,1);
        cal.add(Calendar.DATE,week*7);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        String dateText=format.format(cal.getTime());
        Log.e("metrics",dateText);
        mTv.setText(dateText);

//        //获取上周一的日期
//        Calendar cal2= (Calendar) calendar.clone();
//        calendar.set(2016,9,13);
//        int dayOfWeek=calendar.get(Calendar.DAY_OF_WEEK)-2;
//        Log.e("metrics",String.valueOf(dayOfWeek));
//        calendar.set(Calendar.DATE,date-dayOfWeek-7);
//        String beforeDayOfLastWeek=format.format(calendar.getTime());
//        mTv.setText(beforeDayOfLastWeek);

//        mTv.setText(beforeDayOfLastWeek);
    }

}
