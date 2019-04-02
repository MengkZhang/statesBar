package com.example.status_bar;

import android.app.Application;
import android.content.Context;

/**
 * Created by Mengk on 2019/4/2
 * Describe :
 */
public class MyApp extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
