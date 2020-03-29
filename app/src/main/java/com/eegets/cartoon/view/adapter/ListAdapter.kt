package com.eegets.cartoon.view.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.eegets.cartoon.R
import com.eegets.cartoon.model.bean.DataBean
import com.moving.kotlin.frame.abs.adapter.AbsAdapter
import com.moving.kotlin.frame.abs.holder.ItemHolder
import com.moving.kotlin.frame.ext.loadImage



class ListAdapter(val context: Context?) :AbsAdapter<DataBean>(context) {
    override fun createItemHolder(viewType: Int): ItemHolder<DataBean> {
        return ListHolder(context)
    }

    class ListHolder(context: Context?): ItemHolder<DataBean>(context) {
        private var item_image: ImageView? = null
        private var item_text: TextView? = null
        override fun bindView(data: DataBean?, position: Int) {
            item_image = itemView.findViewById<ImageView>(R.id.item_image)
            item_text = itemView.findViewById<TextView>(R.id.item_home_text)
            if(data?.novelImg.isNullOrEmpty()){
                item_image?.loadImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582365560136&di=0982dda3495ee02de3a13dfc88f3b585&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201507%2F04%2F20150704212949_PSfNZ.jpeg")
            } else {
                item_image?.loadImage(data?.novelImg)
            }
            item_text?.text = data?.novelName
        }

        override fun getLayoutId(): Int {
            return R.layout.item_home_layout
        }

    }
}