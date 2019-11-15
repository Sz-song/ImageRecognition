package com.yuanyu.imagerecognition;

import android.app.Application;
import android.content.Context;



/**
 */
public class MyApplication extends Application {
    private static Context context;
    public static String getToken() {
        return token;
    }
    public static void setToken(String token) {
        MyApplication.token = token;
    }
    private static String token = "";
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        context=getApplicationContext();

        instance = this;

    }


    public static Context getContext(){
       return context;
    }

    public static  MyApplication getInstance(){return  instance;}


}
