package com.wang.vincent.modebuttontest;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBRReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

            boolean msg = intent.getIntExtra("state", 1) == 1 ? true : false;
            if (msg) {
                Toast.makeText(context, "维护模式 open", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "维护模式 close", Toast.LENGTH_SHORT).show();
            }
    }
}