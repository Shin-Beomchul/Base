package com.godbeom.baseapp.util;

import android.util.Log;

import com.godbeom.baseapp.BuildConfig;

public class DLog {

    private static final boolean DEBUG = BuildConfig.DEBUG;
    private static final String TAG = "DLog";

    public static void d(String message) {
        if(DEBUG)
            Log.d(TAG, getMessage(message));
    }

    public static void v(String message) {
        if(DEBUG)
            Log.v(TAG, getMessage(message));
    }

    public static void i(String message) {
        if(DEBUG)
            Log.i(TAG, getMessage(message));
    }

    public static void w(String message) {
        if(DEBUG)
            Log.w(TAG, getMessage(message));
    }

    public static void e(String message) {
        if(DEBUG)
            Log.e(TAG, getMessage(message));
    }


    private static String getMessage(String message) {
        // message format customizing 필요 없음
        return message;
    }

}