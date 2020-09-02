package com.lloydfinch.lib_ccd.lib_tools.thread;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Name: ThreadTool
 * Author: lloydfinch
 * Function: ThreadTool，用于进行线程切换
 * Date: 2020-05-20 11:01
 * Modify: lloydfinch 2020-05-20 11:01
 */
public class ThreadTool {

    private static final String TAG = "ThreadTool";

    //true会自动捕获异常，false会抛出异常
    public static final boolean SAFE = true;

    //单任务池，任务排队处理
    public static final String SINGLE_QUEUE = "SINGLE";
    //多任务池，并发处理
    public static final String CACHE_CONCURRENT = "CACHE";
    //切换到UI线程
    private static SafeHandler UIHandler = new SafeHandler();

    public static Map<String, ExecutorService> TM = new HashMap<>();

    static {
        TM.put(SINGLE_QUEUE, Executors.newSingleThreadExecutor(new MThreadFactory(SINGLE_QUEUE)));
        TM.put(CACHE_CONCURRENT, Executors.newCachedThreadPool(new MThreadFactory(CACHE_CONCURRENT)));
    }

    public static void postAsyncSingle(Runnable task) {
        ExecutorService executor = TM.get(SINGLE_QUEUE);
        if (executor != null) {
            try {
                executor.execute(task);
            } catch (Throwable e) {
                if (!SAFE) {
                    throw e;
                }
            }
        }
    }

    public static void postAsyncCache(Runnable task) {
        ExecutorService executor = TM.get(CACHE_CONCURRENT);
        if (executor != null) {
            try {
                executor.execute(task);
            } catch (Throwable e) {
                if (!SAFE) {
                    throw e;
                }
            }
        }
    }

    public static void postToUI(Runnable task) {
        UIHandler.post(task);
    }

    public static void remove(Runnable task) {
        UIHandler.remove(task);
    }

    /**
     * 自定义线程池，指定线程名和编号
     */
    private static class MThreadFactory implements ThreadFactory {
        private String name;
        private int number = 0;

        public MThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            String tag = name + "-Thread" + "#" + number++;
            Thread thread = new Thread(r, tag);
            Log.d(TAG, "create thread " + thread.getName());
            return thread;
        }
    }

    private void t() {

    }
}
