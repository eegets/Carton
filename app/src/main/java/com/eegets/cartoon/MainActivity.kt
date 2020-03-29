package com.eegets.cartoon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.eegets.cartoon.view.fragment.utils.MainTabs
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTabHost
import com.moving.kotlin.frame.base.BaseActivity


class MainActivity : BaseActivity() {

    private val tabHost: FragmentTabHost by lazy {
        findViewById<FragmentTabHost>(R.id.tabHost)
    }

    override fun layout(): Int {
        return R.layout.activity_main
    }

    override fun createView() {
        initFragmentTabHost()
    }

    /**
     * 初始化FragmentTabHost
     */
    private fun initFragmentTabHost() {
        //将tabHost和FragmentLayout关联
        tabHost.setup(applicationContext, supportFragmentManager, R.id.fl_content)
        //将tabHost和FragmentLayout关联
        tabHost.setup(applicationContext, supportFragmentManager, R.id.fl_content)

        //去掉分割线
        if (Build.VERSION.SDK_INT > 10) {
            tabHost.tabWidget.showDividers = 0
        }

        //添加tab和其对应的fragment
        val tabs = MainTabs.values()
        for (i in tabs.indices) {
            val mainTabs = tabs[i]
            val tabSpec = tabHost.newTabSpec(mainTabs.getName())

            val indicator = View.inflate(applicationContext, R.layout.tab_indicator, null)

            val drawable = getDrawable(mainTabs.icon)
            (indicator.findViewById(R.id.tv_indicator) as TextView).let {
                it.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
                it.text = mainTabs.getName()
                tabSpec.setIndicator(indicator)
            }

            tabHost.addTab(tabSpec, mainTabs.cla, null)
        }
    }

}
