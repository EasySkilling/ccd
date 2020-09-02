package com.lloydfinch.counrtycode;

import android.app.Application;

import com.lloydfinch.lib_ccd.CCDInitializer;

/**
 * Name: MainApplication
 * Author: lloydfinch
 * Function: MainApplication
 * Date: 2020-09-02 12:01
 * Modify: lloydfinch 2020-09-02 12:01
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CCDInitializer.init(this);
    }
}
