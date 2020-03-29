package com.eegets.cartoon.view.type;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.eegets.cartoon.R;
import com.moving.kotlin.frame.abs.adapter.AbsAdapter;
import com.moving.kotlin.frame.abs.holder.ItemHolder;

public class ScrollLeftAdapter extends AbsAdapter<LeftScrollData> {


    public ScrollLeftAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemHolder<LeftScrollData> createItemHolder(int viewType) {
        return new CreateHolder(mContext);
    }
    class CreateHolder extends ItemHolder<LeftScrollData> {

        public CreateHolder(Context context) {
            super(context);
        }

        @Override
        public void bindView(LeftScrollData data, int position) {
            TextView tv = itemView.findViewById(R.id.left_text);
            tv.setText(data.getTypeName()+"");
            if (data.getSelect()) {
                tv.setBackgroundColor(0xff0068b7);
                tv.setTextColor(ContextCompat.getColor(mContext, R.color.c_ffffff));

                //以下是指定某一个TextView滚动的效果
                tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                tv.setFocusable(true);
                tv.setFocusableInTouchMode(true);
                tv.setMarqueeRepeatLimit(-1);
            } else {
                tv.setBackgroundColor(0xffffffff);
                tv.setTextColor(ContextCompat.getColor(mContext, R.color.c_000000));

                //失去焦点则停止滚动
                tv.setEllipsize(TextUtils.TruncateAt.END);
                tv.setFocusable(false);
                tv.setFocusableInTouchMode(false);
                tv.setMarqueeRepeatLimit(0);
            }

        }

        @Override
        protected int getLayoutId() {
            return R.layout.scroll_left;
        }
    }
}
