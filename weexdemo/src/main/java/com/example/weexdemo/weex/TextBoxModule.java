package com.example.weexdemo.weex;

import android.content.Intent;
import android.widget.Toast;

import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import static android.R.attr.name;

/**
 * Created by Hilary
 * on 2016/12/2.
 */

public class TextBoxModule extends WXModule {

    @WXModuleAnno(runOnUIThread = true)
    public void editTextBoxInfo(String msg,String callback) {
        String name="";
        String value="";
        String def="";
        String warn="";
        try {
            msg= URLDecoder.decode(msg,"utf-8");
            JSONObject jsonObject=new JSONObject(msg);
            name=jsonObject.optString("name");
            value=jsonObject.optString("value");
            def=jsonObject.optString("def");
            warn=jsonObject.optString("warn");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Toast.makeText(mWXSDKInstance.getContext(), msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mWXSDKInstance.getContext(), TextBoxActivity.class);
        intent.putExtra("sdkinstance",mWXSDKInstance.getInstanceId());
        intent.putExtra("name",name);
        intent.putExtra("value",value);
        intent.putExtra("def",def);
        intent.putExtra("warn",warn);
        intent.putExtra("callback",callback);
        mWXSDKInstance.getContext().startActivity(intent);
    }



    interface OnFinishedListener{
       void onFinished();
    }
}

