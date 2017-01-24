package com.example.weexdemo.test;

import android.app.Application;

import com.example.weexlibrary.commons.adapter.ImageAdapter;
import com.example.weexlibrary.commons.util.AppConfig;
import com.example.weexlibrary.commons.util.ExtensionManager;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;

public class WXApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        initDebugEnvironment(false, "DEBUG_SERVER_HOST");
        WXSDKEngine.addCustomOptions("appName", "WXSample");
        WXSDKEngine.addCustomOptions("appGroup", "WXApp");
        WXSDKEngine.initialize(this,
                new InitConfig.Builder()
                        .setImgAdapter(new ImageAdapter())
                        .build()
        );

//        Fresco.initialize(this);
        AppConfig.init(this);
        ExtensionManager.registerComponents(AppConfig.getComponents());
        ExtensionManager.registerModules(AppConfig.getModules());

    }

    /**
     * @param enable enable remote debugger. valid only if host not to be "DEBUG_SERVER_HOST".
     *               true, you can launch a remote debugger and inspector both.
     *               false, you can  just launch a inspector.
     * @param host   the debug server host, must not be "DEBUG_SERVER_HOST", a ip address or domain will be OK.
     *               for example "127.0.0.1".
     */
    private void initDebugEnvironment(boolean enable, String host) {
        if (!"DEBUG_SERVER_HOST".equals(host)) {
            WXEnvironment.sRemoteDebugMode = enable;
            WXEnvironment.sRemoteDebugProxyUrl = "ws://" + host + ":8088/debugProxy/native";
        }
    }

}
