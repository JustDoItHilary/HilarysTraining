package com.example.testrecyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilary on 2016/9/9.
 * http-httpClient-httpURLConnect
 */
public class MyHttpActivity extends Activity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_layout);
        mTv = (TextView) findViewById(R.id.tv_http);
        new Thread(new Runnable() {
            @Override
            public void run() {
                useHttpURLConnectionPost("http://www.baidu.com");
//                String str = useHttpURLConnectionPost("http://www.baidu.com");
//                Message message = Message.obtain();
//                message.what = 1;
//                message.obj = str;
//                handler.sendMessage(message);
            }
        }).start();
    }

    private void useHttpURLConnectionPost(String s) {

        try {
            URL url = new URL(s);//获取URL

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();//获取HttpURLConnection

            httpURLConnection.setConnectTimeout(15000);//设置链接超时时间
            httpURLConnection.setReadTimeout(15000);//设置读取超时时间
            httpURLConnection.setRequestMethod("POST");//设置请求方法
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");//添加Header
            httpURLConnection.setDoInput(true);//接收输入流
            httpURLConnection.setDoOutput(true);//传递参数时需开启

            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();

            int code = httpURLConnection.getResponseCode();//获取响应代码

//            String response = httpURLConnection.getResponseMessage();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));//解析数据
            String line = null;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }

            Log.i("wangshu", "请求状态码:" + code + "\n请求结果:\n" + response);
            Message message = Message.obtain();
            message.what = 1;
            message.obj = response;
            handler.sendMessage(message);//异步更新

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                mTv.setText(msg.obj.toString());
            }
        }
    };

    public static void postParams2(OutputStream output,List<NameValuePair>paramsList) throws IOException{
        StringBuilder mStringBuilder=new StringBuilder();
        for (NameValuePair pair:paramsList){
            if(!TextUtils.isEmpty(mStringBuilder)){
                mStringBuilder.append("&");
            }
            mStringBuilder.append(URLEncoder.encode(pair.getName(),"UTF-8"));
            mStringBuilder.append("=");
            mStringBuilder.append(URLEncoder.encode(pair.getValue(),"UTF-8"));
        }
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(output,"UTF-8"));
        writer.write(mStringBuilder.toString());
        writer.flush();
        writer.close();
    }

    public static void postParams(OutputStream output, List<String> paramsList) throws IOException {
        StringBuilder mStringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(mStringBuilder)) {
            mStringBuilder.append("&");
        }
//        mStringBuilder.append(URLEncoder.encode(paramsList.get(0), "UTF-8"));
//        mStringBuilder.append("=");
//        mStringBuilder.append(URLEncoder.encode(paramsList.get(1), "UTF-8"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));
        writer.write(mStringBuilder.toString());
        writer.flush();
        writer.close();
    }

    public static HttpURLConnection getHttpURLConnection(String url) {
        HttpURLConnection mHttpURLConnection = null;
        try {
            URL mUrl = new URL(url);
            mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            //设置链接超时时间
            mHttpURLConnection.setConnectTimeout(15000);
            //设置读取超时时间
            mHttpURLConnection.setReadTimeout(15000);
            //设置请求参数
            mHttpURLConnection.setRequestMethod("POST");
            //添加Header
            mHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            //接收输入流
            mHttpURLConnection.setDoInput(true);
            //传递参数时需要开启
            mHttpURLConnection.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mHttpURLConnection;
    }


    private String useHttpUrlConnectionPost(String url) {
        InputStream mInputStream = null;
        String respose = null;

        HttpURLConnection mHttpURLConnection = getHttpURLConnection(url);
        try {
            List<NameValuePair> postParams = new ArrayList<>();
            //要传递的参数
            postParams.add(new BasicNameValuePair("username", "moon"));
            postParams.add(new BasicNameValuePair("password", "123"));
            postParams2(mHttpURLConnection.getOutputStream(), postParams);
            mHttpURLConnection.connect();
            mInputStream = mHttpURLConnection.getInputStream();
            int code = mHttpURLConnection.getResponseCode();
            respose = converStreamToString(mInputStream);
            Log.i("wangshu", "请求状态码:" + code + "\n请求结果:\n" + respose);
            mInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respose;
    }

    private String converStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        String respose = sb.toString();
        return respose;
    }

    private static class NameValuePair {

        private String name;
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private class BasicNameValuePair extends NameValuePair {
        public BasicNameValuePair(String username, String moon) {
            super();
        }
    }
}
