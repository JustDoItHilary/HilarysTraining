package com.example.weexdemo.weex;

import android.widget.Toast;

import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

/**
 * Created by Hilary
 * on 2016/12/12.
 */

public class ToastModule extends WXModule {

    @WXModuleAnno(runOnUIThread = true)
    public void printToast(String msg){
        Toast.makeText(mWXSDKInstance.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
