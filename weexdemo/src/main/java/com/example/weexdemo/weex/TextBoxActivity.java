package com.example.weexdemo.weex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.taobao.weex.bridge.WXBridgeManager;

/**
 * Created by Hilary
 * on 2016/12/5.
 */

public class TextBoxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final String mSDKInstanceId = intent.getStringExtra("sdkinstance");
        final String callback = intent.getStringExtra("callback");
        String name = intent.getStringExtra("name");
        String value = intent.getStringExtra("value");
        String warn = intent.getStringExtra("warn");
        String defaultValue = intent.getStringExtra("def");
        TextBoxInputView view = new TextBoxInputView(this, name, value, defaultValue, warn, new TextBoxInputView.OnBackListener() {
            @Override
            public void setOnBack(String name, String value, String defaultValue, String warnning) {
                finish();

                WXBridgeManager.getInstance().callback(mSDKInstanceId, callback, "叮咚 叮咚 " + value);
            }
        });
        setContentView(view);
    }
}
