package com.moving.kotlin.frame.abs.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moving.kotlin.frame.abs.adapter.AbsAdapter;

public abstract class ItemHolder<T>{

    protected Context mContext;
    protected View itemView;
    protected AbsAdapter adapter;
    protected ViewGroup parent;

    public ItemHolder(Context context){
        mContext=context;
    }

    /**
     * 获取每个holder的视图
     * @param parent
     * @return
     */
    protected View getView(ViewGroup parent, AbsAdapter adapter){
        this.adapter=adapter;
        this.parent=parent;
        itemView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        init();
        return itemView;
    }
    /**
     * 在这里做一些初始化
     */
    protected void init(){}
    /**
     * 绑定试图
     * @param data
     * @param position
     */
    public abstract void bindView(T data, int position);


    /**
     * 获取布局ID
     * @return
     */
    protected abstract int getLayoutId();
//    public int getLayoutPosition(){
//        return adapter.holder.getLayoutPosition();
//    }


    /**
     * 当recyclerview里面嵌套n个recycelview时,每个recyclervew的itemClick,都需要在所在的holder里面处理ItemClick，就造成入口很多，查找困难
     *
     * 我们采用的措施：使用内部监听器，回调给外部的外部的监听器，这样就保证一个recyclerview里面只有一个入口回调，在处理回调的时候判断数据的类型即可
     * @param v
     * @param data
     * @param position
     */
    public void innerCallOutOnItemClickLister(View v, Object data, int position){
        if(adapter.onItemClickListener!=null){
            adapter.onItemClickListener.onItemClickListener(v,data,position);
        }
    }
}
