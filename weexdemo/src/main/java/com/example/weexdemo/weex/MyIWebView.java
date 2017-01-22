package com.example.weexdemo.weex;

import android.view.View;

/**
 * Created by Hilary
 * on 2016/12/30.
 */

public interface MyIWebView{
    public View getView();
    public void destroy();
    public void loadUrl(String url);
    public void reload();
    public void goBack();
    public void goForward();
    public void setShowLoading(boolean shown);
    public void setOnErrorListener(MyIWebView.OnErrorListener listener);
    public void setOnPageListener(MyIWebView.OnPageListener listener);

    public interface OnErrorListener {
        public void onError(String type, Object message);
    }

    public interface OnPageListener {
        public void onReceivedTitle(String title);
        public void onPageStart(String url);
        public void onPageFinish(String url, boolean canGoBack, boolean canGoForward);
        public void onPageChange(int progress);
    }
}
