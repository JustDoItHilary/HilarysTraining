package com.example.weexdemo.weex;

import android.content.Context;

/**
 * Created by Hilary on 2016/11/24.
 */

public class AndroidUtils {


    public static int dp2px(Context context, int dp) {
        return (int) (dp * (context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
