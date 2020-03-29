package com.moving.kotlin.frame.abs.adapter;

import android.view.View;

public interface OnItemClickListener<T> {
    /**
     * recyclerView的item点击事件
     * @param v
     * @param data
     * @param position
     */
    void onItemClickListener(View v, T data, int position);
}
