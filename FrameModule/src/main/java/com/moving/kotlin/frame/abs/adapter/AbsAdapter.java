package com.moving.kotlin.frame.abs.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.moving.kotlin.frame.abs.holder.ItemHolder;
import com.moving.kotlin.frame.abs.holder.RecyHolder;
import com.moving.kotlin.frame.abs.layoutmanager.RecylerViewWrapperUtils;
import com.moving.kotlin.frame.log.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by llm
 */
public abstract class AbsAdapter<T> extends RecyclerView.Adapter<RecyHolder> implements View.OnClickListener {
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;
    protected SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    protected SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();
    public List<T> list = new ArrayList<T>();
    public OnItemClickListener onItemClickListener;
    protected Context mContext;
    /**
     * 数据上报的position集合，清空的情况：新建adapter、setData、clear
     */
    private Set<Integer> upVisible = new HashSet<>();

    public AbsAdapter(Context context) {
        this.mContext = context;
    }

    public AbsAdapter(Context context, List<T> list) {
        this.mContext = context;
        upVisible.clear();
        if (list != null) {
            this.list = list;
        }
    }

    public void setData(List<T> datas) {
        upVisible.clear();
        list = datas;
        notifyDataSetChanged();
    }

    public void setData(List<T> datas, int position) {
        list = datas;
        notifyItemInserted(position);
    }

    public void addData(List<T> datas) {
        if (datas != null && !datas.isEmpty() && list != null) {
            list.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        upVisible.clear();
        if (list != null) {
            list.clear();
        }
        notifyDataSetChanged();
    }

    public List<T> getData() {
        if(list == null){
            list = new ArrayList<T>();
        }
        return list;
    }

    @Override
    public RecyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyHolder holder;
        if (mHeaderViews.get(viewType) != null) {
            holder = RecyHolder.createHeaderOrFooterViewHolder(mHeaderViews.get(viewType));
        } else if (mFootViews.get(viewType) != null) {
            holder = RecyHolder.createHeaderOrFooterViewHolder(mFootViews.get(viewType));
        } else {
            holder = new RecyHolder(parent, createItemHolder(viewType), this);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyHolder holder, int position) {
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            return;
        }
        position = position - getHeadersCount();
        holder.bindView(getItem(position), position);
        if (onItemClickListener != null) {
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(this);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return getViewType(position - getHeadersCount());
    }

    /**
     * 获取item的type类型,子类继承的时候要设置多类型的布局，必须重新这个方法不要使用getItemViewType
     *
     * @param position
     * @return
     */
    public int getViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (list != null && !list.isEmpty()) {
            size = list.size();
        }
        return getHeadersCount() + getFootersCount() + size;
    }

    /**
     * 获取item
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        try{
            if (position >= 0 && position < list.size()) {
                return list.get(position);
            }
        } catch (Throwable throwable){
            Log.d("getItem(AbsAdapter.java)");
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        Object tag = v.getTag();
        if (tag instanceof Integer) {
            int postion = (int) tag;
            onItemClickListener.onItemClickListener(v, getItem(postion), postion);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    protected abstract ItemHolder<T> createItemHolder(int viewType);

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        RecylerViewWrapperUtils.onAttachedToRecyclerView(recyclerView, new RecylerViewWrapperUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                int viewType = AbsAdapter.this.getItemViewType(position);
                if (mHeaderViews.get(viewType) != null) {
                    return layoutManager.getSpanCount();
                } else if (mFootViews.get(viewType) != null) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null) {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(RecyHolder holder) {
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            RecylerViewWrapperUtils.setFullSpan(holder);
        } else {
            if (!upVisible.contains(position)) {
                onItemVisibleToUser(position);
                upVisible.add(position);
            }
        }
    }

    /**
     * RecyclerView 填充的item的埋点调用的方法
     *
     * @param position
     */
    public void onItemVisibleToUser(int position) {
    }

    protected boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    protected boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }

    public int getRealItemCount() {
        int size = 0;
        if (list != null) {
            size = list.size();
        }
        return size;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
        notifyDataSetChanged();
    }

    public SparseArrayCompat<View> getHeaderView() {
        return mHeaderViews;
    }

    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
        notifyDataSetChanged();
    }

    public SparseArrayCompat<View> getFooterView() {
        return mFootViews;
    }

    public void removeHeaderView(View view) {
        mHeaderViews.remove(mHeaderViews.indexOfValue(view));
        notifyDataSetChanged();
    }

    public void removeFootView(View view) {
        mFootViews.remove(mFootViews.indexOfValue(view));
        notifyDataSetChanged();
    }

    public void clearFootView() {
        mFootViews.clear();
        notifyDataSetChanged();
    }

    public void clearHeadView() {
        mHeaderViews.clear();
        notifyDataSetChanged();
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }

}
