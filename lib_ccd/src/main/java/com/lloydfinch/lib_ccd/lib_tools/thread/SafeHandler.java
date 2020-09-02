package com.lloydfinch.lib_ccd.lib_tools.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/**
 * Name: SafeHandler
 * Author: lloydfinch
 * Function: SafeHandler
 * Date: 2020-05-20 14:34
 * Modify: lloydfinch 2020-05-20 14:34
 */
public class SafeHandler {

    private Handler handler;

    public SafeHandler() {
        handler = new Handler(Looper.getMainLooper());
    }

    public SafeHandler(Looper looper) {
        handler = new Handler(looper);
    }

    public void post(Runnable task) {
        postDelay(task, 0);
    }

    public void postDelay(Runnable task, long time) {
        postAtTime(task, SystemClock.uptimeMillis() + time);
    }

    public void postAtTime(Runnable task, long time) {
        handler.postAtTime(task, time);
    }

    public void remove(Runnable task) {
        handler.removeCallbacks(task);
    }

    public void release() {
        handler.removeCallbacksAndMessages(null);
    }
}
