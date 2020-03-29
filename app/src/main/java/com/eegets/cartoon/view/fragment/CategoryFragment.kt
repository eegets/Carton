package com.eegets.cartoon.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eegets.cartoon.R
import com.eegets.cartoon.api.requireCategoryResponse
import com.eegets.cartoon.api.requireHomeResponse
import com.eegets.cartoon.view.type.ScrollLeftAdapter
import com.eegets.cartoon.view.type.ScrollRightAdapter
import com.moving.kotlin.frame.log.Log
import com.secoo.commonsdk.ext.ExcuteObserverExt


class CategoryTabFragment : Fragment() {

    private var rec_left: RecyclerView? = null
    private var rec_right: RecyclerView? = null

    private var rootView: View? = null// 缓存Fragment view

    private val leftAdapter by lazy {
        ScrollLeftAdapter(context)
    }
    private val rightAdapter by lazy {
        ScrollRightAdapter(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.tab_category, null)
            rec_left = rootView?.findViewById(R.id.rec_left)
            rec_right = rootView?.findViewById(R.id.rec_right)
            initView()
            initRightView()
        }
        return rootView
    }


    fun initView () {

        rec_left?.adapter = leftAdapter
        rec_left?.layoutManager = LinearLayoutManager(activity)
        requireCategoryResponse().subscribe(ExcuteObserverExt({
            Log.d("----$it")

            for (index in it.data.indices) {
                it.data[index].select = index == 0
            }
            leftAdapter?.data = it.data

            setRightLayout(it.data[0].id.toString())
        },{
            Log.d("----")
        },{
            Log.d("----${it.message}")
        }))
        leftAdapter.setOnItemClickListener { v, data, position ->
            Log.d("--点击了--$position")
            val leftData = leftAdapter?.data
            for (index in leftData.indices) {
                leftData[index].select = index == position
            }
            leftAdapter?.data = leftData
            setRightLayout(leftData[position].id.toString())
        }
    }

    fun setRightLayout(type: String) {
        requireHomeResponse("category", type, 99999).subscribe(ExcuteObserverExt({
            Log.d("----$it")
            rightAdapter?.data = it.data
        },{
            Log.d("----")
        },{
            Log.d("----${it.message}")
        }))

    }
    private fun initRightView() {
        rec_right?.adapter = rightAdapter
        rec_right?.layoutManager = LinearLayoutManager(activity)
    }
}
