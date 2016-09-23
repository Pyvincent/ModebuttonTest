package com.wang.vincent.modebuttontest.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by vincent on 16-9-23.
 * 本类用来定义一个万能的适配器，使用泛型
 */

public abstract class MyAdapter<T> extends BaseAdapter {

    private LinkedList<T> mData;
    //private Context mContext;
    private int mLayoutres; //布局id

    public MyAdapter() {
        //super();
    }

    public MyAdapter(LinkedList<T> mData, int mLayoutres) {
        this.mData = mData;
        this.mLayoutres = mLayoutres;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.bind(parent.getContext(),
                convertView, parent, mLayoutres, position);
        bindView(viewHolder, getItem(position));
        return viewHolder.getItemView();

    }

    public abstract void bindView(ViewHolder viewHolder, T obj);


public static class ViewHolder {
    ImageView imageView;
    TextView textView;

    private SparseArray<View> mViews;   //存储ListView中的item中的View
    private View item;                  //存放convertView
    private int position;              //curse
    private Context context;            //上下文

    public ViewHolder(Context context, ViewGroup parent, int layoutRes) {
        mViews = new SparseArray<>();
        this.context = context;
        View convertView = LayoutInflater.from(context).inflate(layoutRes, parent, false);
        convertView.setTag(this);
        item = convertView;
    }


    public static ViewHolder bind(Context context,
                                  View convertView,
                                  ViewGroup parent,
                                  int layoutRes,
                                  int position) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder(context, parent, layoutRes);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.item = convertView;
        }
        viewHolder.position = position;
        return viewHolder;
    }


    public <T extends View> T getView(int id) {
        T t = (T) mViews.get(id);
        if (t == null) {
            t = (T) item.findViewById(id);
            mViews.put(id, t);
        }
        return t;
    }

    /**
     * 获取当前条目
     */
    public View getItemView() {
        return item;
    }

    /**
     * 获取条目位置
     */
    public int getItemPosition() {
        return position;
    }

    /**
     * 设置文字
     */
    public ViewHolder setText(int id, CharSequence text) {
        View view = getView(id);
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
        return this;
    }

    /**
     * 设置图片
     */
    public ViewHolder setImageResource(int id, int drawableRes) {
        View view = getView(id);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(drawableRes);
        } else {
            view.setBackgroundResource(drawableRes);
        }
        return this;
    }

    /**
     * 设置点击监听
     */
    public ViewHolder setOnClickListener(int id, View.OnClickListener listener) {
        getView(id).setOnClickListener(listener);
        return this;
    }

    /**
     * 设置可见
     */
    public ViewHolder setVisibility(int id, int visible) {
        getView(id).setVisibility(visible);
        return this;
    }

    /**
     * 设置标签
     */
    public ViewHolder setTag(int id, Object obj) {
        getView(id).setTag(obj);
        return this;
    }


}

    //添加元素
    public void add(T data) {
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

    public void add(int position, T data) {
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(position, data);
        notifyDataSetChanged();
    }
    //删除一列元素

    public void remove(T data) {
        if (mData != null) {
            mData.remove(data);
        }
        notifyDataSetChanged();

    }

    public void remove(int position) {
        if (mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }

    //删除所有元素
    public void clear() {
        if (mData != null) {
            mData.clear();
        }
        notifyDataSetChanged();
    }
}
