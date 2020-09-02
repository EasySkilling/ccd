package com.lloydfinch.lib_ccd.lib_tools.action;

/**
 * Name: Singleton
 * Author: lloydfinch
 * Function: Singleton
 * Date: 2020-05-28 11:32
 * Modify: lloydfinch 2020-05-28 11:32
 */
public abstract class Singleton<T> {
    private T mInstance;

    public abstract T create();

    public final T get() {
        if (mInstance == null) {
            synchronized (this) {
                mInstance = create();
            }
        }
        return mInstance;
    }
}
