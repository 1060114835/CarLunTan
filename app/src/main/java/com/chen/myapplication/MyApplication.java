package com.chen.myapplication;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context context;

    public MyApplication() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
