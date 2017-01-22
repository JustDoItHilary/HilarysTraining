package com.example.testrecyclerviewdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2016/9/23.
 */

public class MyURLWebActivity extends Activity {
    private String tag="bababababababa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initWebView();
    }

    private void initWebView() {
        WebView webView=(WebView)findViewById(R.id.webview);
        webView.loadUrl("http://sohu.com/");
        webView.setWebViewClient(new MyWebViewClient());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onLoadResource(WebView view, String url) {
            Log.d(tag, "onLoadResource: url="+url);
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d(tag, "onPageFinished: url="+url);
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d(tag, "onPageStarted: url="+url);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            Log.d(tag, "onReceivedError: errorCode="+errorCode+", desc="+description+", failingUrl="+failingUrl);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

    }
}
