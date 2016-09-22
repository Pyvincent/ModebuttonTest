package com.wang.vincent.modebuttontest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

import static com.wang.vincent.modebuttontest.R.id.img_icon;
import static com.wang.vincent.modebuttontest.R.id.txt_aName;

/**
 * Created by vincent on 16-9-22.
 */

public class AnimalAdapter extends BaseAdapter {

    private LinkedList<Animal> mData;
    private Context mContext;

    public AnimalAdapter(LinkedList<Animal> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        //第一次的时候加载xml,之后不再加载xml，这样大大提高效率
        //inflate()每次都要加载一次xml，其实这个convertView是系统提供给我们的可供服用的View 的缓存对象，那就坐下判断咯，修改下，优化后的代码：
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_animal, parent, false);
            holder = new ViewHolder();
            holder.imageview = (ImageView) convertView.findViewById(img_icon);
            holder.txt_aName = (TextView) convertView.findViewById(txt_aName);
            holder.txt_aSpeak = (TextView) convertView.findViewById(R.id.txt_aSpeak);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageview.setBackgroundResource(mData.get(position).getaIcon());
        holder.txt_aName.setText(mData.get(position).getaName());
        holder.txt_aSpeak.setText(mData.get(position).getaSpeak());
        return convertView;
    }
    //下面类用来优化getItem
    static class ViewHolder {
        ImageView imageview;
        TextView txt_aName;
        TextView txt_aSpeak;
    }
}


