package com.example.weexdemo.weex;

import android.text.TextUtils;
import android.widget.ImageView;

import com.example.weexdemo.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

import java.lang.reflect.Field;

/**
 * Created by Hilary
 * on 2017/1/11.
 */

class MyImageAdapter implements IWXImgLoaderAdapter {

    MyImageAdapter() {

    }

    @Override
    public void setImage(final String url, final ImageView view, WXImageQuality quality, final WXImageStrategy strategy) {

        WXSDKManager.getInstance().postOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (view == null || view.getLayoutParams() == null) {
                    return;
                }
                if (view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
                    return;
                }
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                if (TextUtils.isEmpty(url)) {
                    view.setImageBitmap(null);
                    return;
                }
                String temp = url;
                if (url.startsWith("//")) {
                    temp = "http:" + url;
                }
                Picasso.with(WXEnvironment.getApplication())
                        .load(temp)
                        .into(view, new Callback() {
                            @Override
                            public void onSuccess() {
                                if (strategy.getImageListener() != null) {
                                    strategy.getImageListener().onImageFinish(url, view, true, null);
                                }

                                if (!TextUtils.isEmpty(strategy.placeHolder)) {
                                    ((Picasso) view.getTag(strategy.placeHolder.hashCode())).cancelRequest(view);
                                }
                            }

                            @Override
                            public void onError() {
                                if (strategy.getImageListener() != null) {
                                    strategy.getImageListener().onImageFinish(url, view, false, null);
                                }
                            }
                        });
//                ImageLoader.getInstance().displayImage(url, view, ImageLoaderUtil.initOptions());

                if (url.startsWith("drawable://") || url.startsWith("mipmap://")) {
                    int drawableResId = getImageIdByReflect(url);
                    if (drawableResId != 0) {
                        view.setImageResource(drawableResId);
                    } else {
                        view.setImageBitmap(null);
                    }
                }


            }
        }, 0);
    }


    private int getImageIdByReflect(String url) {
        String[] imageRule = url.split("//");
        Field field = null;
        try {
            field = R.mipmap.class.getField(imageRule[1]);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        int resId = 0;
        if (field != null) {
            try {
                resId = field.getInt(field);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return resId;
    }


}
