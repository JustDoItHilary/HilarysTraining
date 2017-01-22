//package com.example.weexdemo.weex;
//
//
//
//import android.graphics.BitmapFactory;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import com.taobao.weex.common.WXModule;
//import com.taobao.weex.common.WXModuleAnno;
//import com.taobao.weex.common.WXResponse;
//import com.taobao.weex.http.Status;
//import com.taobao.weex.utils.WXLogUtils;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.taobao.weex.http.WXStreamModule.STATUS;
//import static com.taobao.weex.http.WXStreamModule.STATUS_TEXT;
//
///**
// * Created by Hilary
// * on 2016/12/23.
// */
//
//public class MyStreamModule extends WXModule {
//
//    @WXModuleAnno
//    public void fetch(String optionsStr,String callback){
//        JSONObject optionsObj = null;
//        try {
//            optionsObj = JSON.parseObject(optionsStr);
//        }catch (JSONException e){
//            WXLogUtils.e("", e);
//        }
//
//        boolean invaildOption = optionsObj==null || optionsObj.getString("url")==null;
//        if(invaildOption){
//            if(callback != null) {
//                Map<String, Object> resp = new HashMap<>();
//                resp.put("ok", false);
//                resp.put(STATUS_TEXT, Status.ERR_INVALID_REQUEST);
//                callback.invoke(resp);
//            }
//            return;
//        }
//        String method = optionsObj.getString("method");
//        String url = optionsObj.getString("url");
//        JSONObject headers = optionsObj.getJSONObject("headers");
//        String body = optionsObj.getString("body");
//        String type = optionsObj.getString("type");
//        int timeout = optionsObj.getIntValue("timeout");
//
//        BitmapFactory.Options.Builder builder = new BitmapFactory.Options.Builder()
//                .setMethod(!"GET".equals(method)
//                        &&!"POST".equals(method)
//                        &&!"PUT".equals(method)
//                        &&!"DELETE".equals(method)
//                        &&!"HEAD".equals(method)
//                        &&!"PATCH".equals(method)?"GET":method)
//                .setUrl(url)
//                .setBody(body)
//                .setType(type)
//                .setTimeout(timeout);
//
//        extractHeaders(headers,builder);
//        final BitmapFactory.Options options = builder.createOptions();
//        sendRequest(options, new ResponseCallback() {
//            @Override
//            public void onResponse(WXResponse response, Map<String, String> headers) {
//                if(callback != null) {
//                    Map<String, Object> resp = new HashMap<>();
//                    if(response == null|| "-1".equals(response.statusCode)){
//                        resp.put(STATUS,"-1");
//                        resp.put(STATUS_TEXT,Status.ERR_CONNECT_FAILED);
//                    }else {
//                        resp.put(STATUS, response.statusCode);
//                        int code = Integer.parseInt(response.statusCode);
//                        resp.put("ok", (code >= 200 && code <= 299));
//                        if (response.originalData == null) {
//                            resp.put("data", null);
//                        } else {
//                            String respData = readAsString(response.originalData,
//                                    headers!=null?getHeader(headers,"Content-Type"):""
//                            );
//                            resp.put("data",parseJson(respData,options.getType()));
//                        }
//                        resp.put(STATUS_TEXT, Status.getStatusText(response.statusCode));
//                    }
//                    resp.put("headers", headers);
//                    callback.invoke(resp);
//                }
//            }
//        }, progressCallback);
//    }
//}
