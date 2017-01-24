package com.example.weexdemo.weex;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.weexdemo.R;
import com.example.weexlibrary.commons.AbsWeexActivity;
import com.example.weexlibrary.commons.util.AppConfig;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXViewUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hilary
 * on 2016/11/28.
 */

public class WXActivity extends AbsWeexActivity implements IWXRenderListener {
    RelativeLayout viewGroup;
    private static final String DEFAULT_IP = "192.168.2.29";
    private static String CURRENT_IP = DEFAULT_IP; // your_current_IP
    private static final String WEEX_INDEX_URL = "http://" + CURRENT_IP + ":12580/examples/build/index.js";
    private WXSDKInstance mInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx);
        viewGroup = (RelativeLayout) findViewById(R.id.viewGroup);
        setContainer((ViewGroup) findViewById(R.id.index_container));

        mInstance = new WXSDKInstance(this);
        mInstance.registerRenderListener(new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {
                viewGroup.addView(view);
            }

            @Override
            public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

            }

            @Override
            public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

            }

            @Override
            public void onException(WXSDKInstance instance, String errCode, String msg) {

            }
        });
//        renderPage(mInstance, getPackageName(), WXFileUtils.loadAsset("hello.js", this), WEEX_INDEX_URL, null);

        loadUrl(AppConfig.getLocalUrl());
    }

    protected void renderPage(WXSDKInstance mInstance, String packageName, String template, String source, String jsonInitData) {
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, source);
        mInstance.renderByUrl(
                packageName,
                template,
                options,
                jsonInitData,
                WXViewUtils.getScreenWidth(this),
                WXViewUtils.getScreenHeight(this),
                WXRenderStrategy.APPEND_ASYNC);
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        setContentView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }
}
