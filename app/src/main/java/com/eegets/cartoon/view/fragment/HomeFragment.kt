package com.eegets.cartoon.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.eegets.cartoon.R
import com.eegets.cartoon.api.requireHomeResponse
import com.eegets.cartoon.view.adapter.ListAdapter
import com.moving.kotlin.frame.log.Log
import com.secoo.commonsdk.ext.ExcuteObserverExt
import androidx.recyclerview.widget.GridLayoutManager
import com.daimajia.slider.library.SliderLayout
import com.eegets.cartoon.utils.GridSpacingItemDecoration
import com.eegets.cartoon.view.adapter.ToadyUpdateAdapter
import com.daimajia.slider.library.SliderTypes.TextSliderView




/**
 * 首页
 */

class HomeTabFragment : Fragment() {
    private lateinit var slider: SliderLayout
    private lateinit var recycleView: RecyclerView
    private lateinit var todayRecycle: RecyclerView

    private val listAdapter by lazy {
        ListAdapter(context)
    }
    private val todayUpdateAdapter by lazy {
        ToadyUpdateAdapter(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = View.inflate(context, R.layout.tab_home, null)
        slider =  view.findViewById(R.id.slider)
        recycleView = view.findViewById(R.id.recycleView)
        todayRecycle = view.findViewById(R.id.today_new)

        addNewToady()
        initView()
        addSlideView()
        return view
    }

    fun initView () {

        recycleView?.adapter = listAdapter
        recycleView?.layoutManager = GridLayoutManager(activity, 2)
        recycleView?.addItemDecoration(GridSpacingItemDecoration(2, 28, false))
        requireHomeResponse("recommend").subscribe(ExcuteObserverExt({
            Log.d("----$it")
            listAdapter?.data = it.data
        },{
            Log.d("----")
        },{
            Log.d("----${it.message}")
        }))

    }

    private fun addNewToady() {

        todayRecycle?.adapter = todayUpdateAdapter
        todayRecycle?.layoutManager = GridLayoutManager(activity, 3)
        todayRecycle?.addItemDecoration(GridSpacingItemDecoration(3, 28, false))
        requireHomeResponse("toadyUpdate").subscribe(ExcuteObserverExt({
            Log.d("----$it")
            todayUpdateAdapter?.data = it.data
        },{
            Log.d("----")
        },{
            Log.d("----${it.message}")
        }))

    }

    private fun addSlideView() {
        val textSliderView = TextSliderView(context)
        textSliderView
            //添加图片描述信息
            .description("Game of Thrones")
            //添加图片url，可以是本地的，也可以是网络上的，这个是官方示例
            .image("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2703272461,3300028390&fm=26&gp=0.jpg")
            //最后把TextSliderView添加到sliderShow中进行展示
         slider.addSlider(textSliderView)
    }
}
