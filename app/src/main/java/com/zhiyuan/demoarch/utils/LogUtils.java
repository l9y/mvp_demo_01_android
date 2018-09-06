package com.zhiyuan.demoarch.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.zhiyuan.demoarch.BuildConfig;

/**
 * @author zhiyuan
 * @date 2018/9/6
 */

public class LogUtils {

    public static void i(@NonNull String tag, @NonNull String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }
}
