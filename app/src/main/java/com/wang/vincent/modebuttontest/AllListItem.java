package com.wang.vincent.modebuttontest;

import android.content.Context;
import android.os.Build;
import java.util.List;

/**
 * Created by vincent on 16-9-22.
 */

public class AllListItem {

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
