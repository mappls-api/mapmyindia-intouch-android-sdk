package com.mapmyindia.intouchsdkdemo;

import android.app.Application;

import timber.log.Timber;

public class IntouchSampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
