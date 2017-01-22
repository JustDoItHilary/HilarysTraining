package com.example.copynotesdemo.weex;

import android.widget.Toast;

import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

/**
 * Created by Hilary
 * on 2016/12/2.
 */

public class MyMoudle extends WXModule {

    @WXModuleAnno(runOnUIThread = true)
    public void printLog(String msg) {
        Toast.makeText(mWXSDKInstance.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}

