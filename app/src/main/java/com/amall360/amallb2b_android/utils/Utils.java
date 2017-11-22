package com.amall360.amallb2b_android.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

public final class Utils {

    @SuppressLint("StaticFieldLeak")
    private static Context mcotext;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull Context context) {
        Utils.mcotext = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (mcotext != null) return mcotext;
        throw new NullPointerException("u should init first");
    }
}