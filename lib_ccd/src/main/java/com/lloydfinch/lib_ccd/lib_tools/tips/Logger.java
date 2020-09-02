package com.lloydfinch.lib_ccd.lib_tools.tips;

import android.util.Log;

import com.lloydfinch.lib_ccd.BuildConfig;


/**
 * Name: Logger
 * Author: lloydfinch
 * Function: Logger
 * Date: 2020-09-02 11:09
 * Modify: lloydfinch 2020-09-02 11:09
 */
public class Logger {

    private static String TAG = "Logger";
    private static boolean OPEN = BuildConfig.DEBUG;

    public static void d(Object msg) {
        if (msg == null) return;
        if (OPEN) Log.d(TAG, msg.toString());
    }

    public static void d(String TAG, Object msg) {
        if (msg == null) return;
        if (OPEN) Log.d(TAG, msg.toString());
    }

    public static void e(Object msg) {
        if (msg == null) return;
        if (OPEN) Log.e(TAG, msg.toString());
    }

    public static void e(String TAG, Object msg) {
        if (msg == null) return;
        if (OPEN) Log.e(TAG, msg.toString());
    }
}
