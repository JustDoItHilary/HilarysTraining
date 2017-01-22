package com.example.weexdemo.weex;

import android.util.Log;
import android.widget.Toast;

import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Hilary
 * on 2016/12/2.
 */

public class LogModule extends WXModule {
    private static final String TAG = "LogModule";


    @WXModuleAnno(runOnUIThread = true)
    public void printLog(String msg) {
//        String mess="";
//        try {
//            msg= URLDecoder.decode(msg,"utf-8");
//            JSONObject jsonObject=new JSONObject(msg);
//            mess=jsonObject.optString("e");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        Log.e(TAG, "printLog: "+msg);
//        Toast.makeText(mWXSDKInstance.getContext(), msg, Toast.LENGTH_SHORT).show();
    }





}

