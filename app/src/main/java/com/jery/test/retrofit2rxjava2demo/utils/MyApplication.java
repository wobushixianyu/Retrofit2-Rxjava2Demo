package com.jery.test.retrofit2rxjava2demo.utils;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
