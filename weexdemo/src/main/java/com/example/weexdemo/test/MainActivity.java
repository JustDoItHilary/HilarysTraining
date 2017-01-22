package com.example.weexdemo.test;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.weexdemo.R;

public class MainActivity extends AppCompatActivity {
    private ImageView progressImage;
    private AnimationDrawable frameAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_smoothprogressbar);
        ProgressBar progressBar=(ProgressBar)findViewById(R.id.progress);
//        progressBar.setIndeterminate(true);
//        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),R.mipmap.scenery);
//        progressBar.setIndeterminateDrawable(new BitmapDrawable(bitmap));
//        progressBar.setIndeterminateDrawable(getResources().getDrawable(R.mipmap.scenery));
//        progressBar.setIndeterminateDrawable(getResources().getDrawable(R.mipmap.scenery));
//        progressBar.set
//        progressImage = (ImageView) findViewById(R.id.progress_img);
//        progressImage.setImageResource(R.drawable.animationtest);
//        frameAnimation = (AnimationDrawable) progressImage.getDrawable();
//        TranslateAnimation myAnimation = new TranslateAnimation(-200.0f, 200.0f, 0.0f, 0.0f);
////        RotateAnimation myAnimation=new RotateAnimation(0,90);
////        myAnimation.setInterpolator(new LinearInterpolator());
////        myAnimation.setFillAfter(true);
//        myAnimation.setDuration(1800);
//        myAnimation.setRepeatCount(Animation.INFINITE);
//        progressImage.startAnimation(myAnimation);
//        frameAnimation.start();
//        progressBar.startAnimation(myAnimation);// progressbar 整体动


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        progressImage.clearAnimation();
        frameAnimation.stop();
        return super.onKeyDown(keyCode, event);
    }
}
