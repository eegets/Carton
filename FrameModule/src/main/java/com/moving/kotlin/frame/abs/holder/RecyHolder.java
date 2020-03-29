package com.moving.kotlin.frame.abs.holder;


import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import com.moving.kotlin.frame.abs.adapter.AbsAdapter;

public class RecyHolder<T> extends RecyclerView.ViewHolder {

    private ItemHolder<T> item;

    public RecyHolder(ViewGroup parent, ItemHolder<T> itemView, AbsAdapter adapter) {
        super(itemView.getView(parent,adapter));
        this.item = itemView;
    }

    public RecyHolder(View itemView) {
        super(itemView);
    }

    public void bindView(T t, int position) {
        item.bindView(t, position);
    }

    /**
     * 主要是为recyclelerview添加头或者脚
     *
     * @param itemView
     * @return
     */
    public static RecyHolder createHeaderOrFooterViewHolder(View itemView) {
        RecyHolder holder = new RecyHolder(itemView);
        return holder;
    }
}
