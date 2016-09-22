package com.wang.vincent.modebuttontest;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.LinkedList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by vincent on 16-9-22.
 */

public class AllListItem {

    //private List<Animal> mData = null;
    GetDeviceData getDeviceData = null;

    public AllListItem(List<Animal> mData,Context mContext) {

        getDeviceData=new GetDeviceData();


        mData.add(new Animal("系统版本号", Build.DISPLAY, R.mipmap.ic_launcher));
        mData.add(new Animal("CPU", getDeviceData.getCpuName(), R.mipmap.ic_launcher));
        mData.add(new Animal("CPU核心数", "" + getDeviceData.getNumCores(), R.mipmap.ic_launcher));
        mData.add(new Animal("获取rom大小", "" + getDeviceData.getTotalInternalMemorySize(), R.mipmap.ic_launcher));
        mData.add(new Animal("分辨率", getDeviceData.getScreenResolution(mContext), R.mipmap.ic_launcher));

        mData.add(new Animal("系统版本号", Build.DISPLAY, R.mipmap.ic_launcher));
        mData.add(new Animal("CPU", getDeviceData.getCpuName(), R.mipmap.ic_launcher));
        mData.add(new Animal("CPU核心数", "" + getDeviceData.getNumCores(), R.mipmap.ic_launcher));
        mData.add(new Animal("获取rom大小", "" + getDeviceData.getTotalInternalMemorySize(), R.mipmap.ic_launcher));
        mData.add(new Animal("分辨率", getDeviceData.getScreenResolution(mContext), R.mipmap.ic_launcher));
    }



}
