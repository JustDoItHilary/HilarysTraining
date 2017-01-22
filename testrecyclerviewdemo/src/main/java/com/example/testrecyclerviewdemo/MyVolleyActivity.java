package com.example.testrecyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilary on 2016/9/10.
 * volley
 */
public class MyVolleyActivity extends Activity {
    private TextView mTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_layout);
        mTv = (TextView) findViewById(R.id.tv_http);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://www.baidu.com", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                Log.e("babanana",s);
//                mTv.setText(s);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Log.e("babanana",volleyError.toString());
//            }
//        });
//        requestQueue.add(stringRequest);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://api.1-blog.com/biz/bizserver/article/list.do", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Article mArticle = new Gson().fromJson(jsonObject.toString(), Article.class);
                List<Article.detail> mList = mArticle.getDetail();
                String title = mList.get(0).getTitle();
                mTv.setText(title);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private class Article {
        private String desc;
        private String status;
        private List<detail> detail = new ArrayList<detail>();

        public List<Article.detail> getDetail() {
            return detail;
        }

        public void setDetail(List<Article.detail> detail) {
            this.detail = detail;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


        public class detail {
            private String title;
            private String article_url;
            private String my_abstract;
            private String article_type;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getArticle_url() {
                return article_url;
            }

            public void setArticle_url(String article_url) {
                this.article_url = article_url;
            }

            public String getMy_abstract() {
                return my_abstract;
            }

            public void setMy_abstract(String my_abstract) {
                this.my_abstract = my_abstract;
            }

            public String getArticle_type() {
                return article_type;
            }

            public void setArticle_type(String article_type) {
                this.article_type = article_type;
            }
        }
    }
}
