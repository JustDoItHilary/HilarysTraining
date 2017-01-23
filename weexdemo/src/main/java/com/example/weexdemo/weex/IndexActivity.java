package com.example.weexdemo.weex;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.weex.commons.AbsWeexActivity;
import com.alibaba.weex.commons.util.AppConfig;
import com.example.weexdemo.R;
import com.taobao.weex.WXSDKInstance;

public class IndexActivity extends AbsWeexActivity {

    private static final String TAG = "IndexActivity";
    private static final String DEFAULT_IP = "192.168.2.29";
    private static String sCurrentIp = DEFAULT_IP;//"127.0.0.1"; // your_current_IP

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        setContainer((ViewGroup) findViewById(R.id.index_container));

        String url=isLocalPage() ? AppConfig.getLocalUrl() : AppConfig.getLaunchUrl();
//        loadUrl("http://g.tbcdn.cn/weex/weex-tc/0.1.0/build/TC__Home.js?weex-samples");
//        loadUrl("http://192.168.100.140:8080/test/ui/zhy/js/test.js");
//        loadUrl("http://192.168.100.140:8080/test/ui/zhy/js/userlist.js");
//        loadUrl("https://raw.github.com/mozilla/pdf.js/gh-pages/build/pdf.js");
//        loadUrl("http://sources.ikeepstudying.com/js/jquery-1.8.3.min.js");
        loadUrl(url);
//        loadUrl("file://assert/dist/modules/listtest.js");
    }

    @Override
    protected boolean isLocalPage() {
         return TextUtils.equals(sCurrentIp, DEFAULT_IP);
    }


    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        Log.e(TAG, "onException: error:"+msg );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this,"叮咚 叮咚",Toast.LENGTH_SHORT).show();
    }
}

