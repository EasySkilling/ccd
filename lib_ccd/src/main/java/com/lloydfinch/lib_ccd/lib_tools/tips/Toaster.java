package com.lloydfinch.lib_ccd.lib_tools.tips;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.lloydfinch.lib_ccd.lib_tools.thread.ThreadTool;

/**
 * Name: Toaster
 * Author: lloydfinch
 * Function: Toaster，show toast in any place any thread
 * Date: 2020-09-02 11:06
 * Modify: lloydfinch 2020-09-02 11:06
 */
public class Toaster {
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static void showShort(CharSequence text) {
        if (TextUtils.isEmpty(text)) return;
        ThreadTool.postToUI(() -> Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show());
    }

    public static void showLong(CharSequence text) {
        if (TextUtils.isEmpty(text)) return;
        ThreadTool.postToUI(() -> Toast.makeText(mContext, text, Toast.LENGTH_LONG).show());
    }
}
