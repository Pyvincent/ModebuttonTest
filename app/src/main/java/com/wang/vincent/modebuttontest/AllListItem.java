package com.wang.vincent.modebuttontest;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.List;


/**
 * Created by vincent on 16-9-22.
 */

public class AllListItem {

    GetDeviceData getDeviceData = null;
    private View headView;
    private View footerView;

    public AllListItem(ListView list_param, List<ParameDev> mData, Context mContext) {

        getDeviceData=new GetDeviceData();

        final LayoutInflater inflater=LayoutInflater.from(mContext);
        headView=inflater.inflate(R.layout.head_list,null,false);
        footerView=inflater.inflate(R.layout.footer_list,null,false);

        mData.add(new ParameDev("系统版本号（点击添加到自己后边）", Build.DISPLAY, R.mipmap.ic_launcher));
        mData.add(new ParameDev("CPU", getDeviceData.getCpuName(), R.mipmap.ic_launcher));
        mData.add(new ParameDev("CPU核心数", "" + getDeviceData.getNumCores(), R.mipmap.ic_launcher));
        mData.add(new ParameDev("获取rom大小", "" + getDeviceData.getTotalInternalMemorySize(), R.mipmap.ic_launcher));
        mData.add(new ParameDev("分辨率", getDeviceData.getScreenResolution(mContext), R.mipmap.ic_launcher));
        mData.add(new ParameDev("模块版本(getProp)", getDeviceData.getBaseBand(), R.mipmap.ic_launcher));

        //添加表头和表尾需要写在setAdapter方法调用之前！！！
        list_param.addFooterView(footerView);
        list_param.addHeaderView(headView);

    }



}
