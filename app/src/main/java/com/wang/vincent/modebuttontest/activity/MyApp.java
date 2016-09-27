package com.wang.vincent.modebuttontest.activity;

import android.app.Application;

/**
 * Created by vincent on 16-9-27.
 */

public class MyApp extends Application {
    private String myState;
    private static MyApp instance;

    //在任意的地方获取实例： MyApp.getInstance()

    public static MyApp getInstance(){
        return instance;
    }

    public String getMyState() {
        return myState;
    }

    public void setMyState(String myState) {
        this.myState = myState;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
}
