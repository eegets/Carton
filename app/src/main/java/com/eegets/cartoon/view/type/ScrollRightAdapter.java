package com.eegets.cartoon.view.type;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.eegets.cartoon.R;
import com.eegets.cartoon.model.bean.DataBean;
import com.moving.kotlin.frame.abs.adapter.AbsAdapter;
import com.moving.kotlin.frame.abs.holder.ItemHolder;


/**
 * Created by Raul_lsj on 2018/3/28.
 */

public class ScrollRightAdapter extends AbsAdapter<DataBean> {


    public ScrollRightAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemHolder<DataBean> createItemHolder(int viewType) {
        return new CreateHolder(mContext);
    }

    class CreateHolder extends ItemHolder<DataBean> {

        public CreateHolder(Context context) {
            super(context);
        }

        @Override
        public void bindView(DataBean data, int position) {
            ImageView scroll_img = itemView.findViewById(R.id.scroll_img);
            TextView title_text = itemView.findViewById(R.id.title_text);
            TextView desc_text = itemView.findViewById(R.id.desc_text);
            TextView type_text = itemView.findViewById(R.id.type_text);
            if(TextUtils.isEmpty(data.getNovelImg())){
                Glide.with(mContext)
                        .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582365560136&di=0982dda3495ee02de3a13dfc88f3b585&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201507%2F04%2F20150704212949_PSfNZ.jpeg")
                        .into(scroll_img);
            } else {
                Glide.with(mContext).load(data.getNovelImg()).into(scroll_img);
            }
            title_text.setText(data.getNovelName()+"");
            desc_text.setText(data.getNovelSummary()+"");
            type_text.setText(data.getNovelAuthor()+"");
        }

        @Override
        protected int getLayoutId() {
            return R.layout.scroll_right;
        }
    }
}
