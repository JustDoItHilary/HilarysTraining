package com.example.testrecyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Hilary on 2016/9/12.
 * okhttp3
 */
public class MyOkHttp2Activity extends Activity {
    private TextView mTv;

    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_layout);
        mTv=(TextView)findViewById(R.id.tv_http);

//        getAsynHttp();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                getSyncHttp();
//            }
//        }).start();
//        postAsynHttp();
//        postAsynFileHttp();
        downloadFileAsynHttp();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what==1){
                    String mess=msg.obj.toString();
                    mTv.setText(mess);
                }
            }
        };
    }

    private void getAsynHttp() {
        OkHttpClient okhttpClient=new OkHttpClient();
        final Request request=new Request.Builder().url("http://www.baidu.com").build();
        Call call=okhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Message msg=Message.obtain();
                msg.what=1;
                msg.obj=response.body().string();
                handler.sendMessage(msg);
            }
        });

    }
    private void getSyncHttp(){
        OkHttpClient okhttpClient=new OkHttpClient();
        Request request=new Request.Builder().url("http://www.baidu.com").build();
        Call call=okhttpClient.newCall(request);
        try {
            Response response=call.execute();
            if (response.isSuccessful()){
                Message msg=Message.obtain();
                msg.what=1;
                msg.obj=response.body().string();
                handler.sendMessage(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void postAsynHttp(){
        OkHttpClient okhttpClient=new OkHttpClient();
        RequestBody body=new FormEncodingBuilder().add("size","10").build();
        Request request=new Request.Builder().url("http://www.baidu.com").post(body).build();
        Call call=okhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(final Response response) throws IOException {
                Message msg=Message.obtain();
                msg.what=1;
                msg.obj=response.body().string();
                handler.sendMessage(msg);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            mTv.setText(response.body().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
            }
        });
    }

    private final static MediaType MEDIA_TYPE_DIDI=MediaType.parse("text/x-markdown; charset=utf-8");
    File file=new File("/sdcard/test.txt");

    private void postAsynFileHttp(){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url("https://api.github.com/markdown/raw").post(RequestBody.create(MEDIA_TYPE_DIDI,file)).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Message msg=Message.obtain();
                msg.what=1;
                msg.obj=response.body().string();
                handler.sendMessage(msg);
            }
        });
    }
    private void downloadFileAsynHttp(){
        OkHttpClient okhttpClient=new OkHttpClient();
        String url = "http://img.my.csdn.net/uploads/201603/26/1458988468_5804.jpg";
        Request request=new Request.Builder().url(url).build();
        Call call=okhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                InputStream is=response.body().byteStream();
                FileOutputStream os=new FileOutputStream(new File("/sdcard/test.jpg"));
                byte[] b=new byte[2048];
                int len=0;
                while((len=is.read(b))!=-1){
                    os.write(b,0,len);
                }
                os.flush();

            }
        });
    }
}
