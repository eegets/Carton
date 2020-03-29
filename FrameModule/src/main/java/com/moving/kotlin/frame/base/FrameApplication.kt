package com.moving.kotlin.frame.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import cn.magicwindow.MWConfiguration
import cn.magicwindow.MagicWindowSDK
import cn.magicwindow.Session
import com.moving.kotlin.frame.BuildConfig
import com.moving.kotlin.frame.log.Log
import java.util.*


open class FrameApplication : MultiDexApplication() {

    companion object {

        //FrameApplication实例
        @get:Synchronized
        var instance: FrameApplication = FrameApplication()
            private set
        //保存Activity的列表,主要用于记录
        private val mList = LinkedList<Activity>()

        /**
         * 重启app
         * @param context
         */
        fun restartApp(context: Context) {
            val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
            intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }


        /**
         * 对外接口,将Activity加入列表内
         */
        fun addToList(act: Activity) {
            FrameApplication.mList.add(act)
        }

        /**
         * 对外接口,将Activity从列表移除
         */
        fun removeFromList(act: Activity) {
            if (FrameApplication.mList.indexOf(act) != -1) {
                FrameApplication.mList.remove(act)
            }
        }


        /**
         * 获取栈顶的activity
         * @return
         */
        fun getLastActivity(): Activity? {
            val size = FrameApplication.mList.size
            return if (size > 0) {
                mList[size - 1]
            } else null
        }

        /**
         * 完全退出app
         */
        fun exitApp() {
            try {
                FrameApplication.mList.indices.reversed()
                        .map { FrameApplication.mList[it] }
                        .forEach { it.finish() }
                Session.onKillProcess()
                System.exit(0)
            } catch (e: Exception) {
                Log.d(e.toString())
                System.exit(1)
            } finally {
                android.os.Process.killProcess(android.os.Process.myPid())
                System.gc()

            }
        }

        //留下栈底的act 其他的都移除掉
        fun returnFirstActivity() {
            FrameApplication.mList.indices.reversed()
                    .filter { it != 0 }
                    .map { FrameApplication.mList[it] }
                    .forEach { it.finish() }
        }

        //关闭栈中匹配的activity
        fun finishActivityByName(className: String) {
            FrameApplication.mList.indices.reversed()
                    .filter { it != 0 }
                    .map { FrameApplication.mList[it] }
                    .filter { it.javaClass.toString() == className }
                    .forEach { it.finish() }
        }
    }

    private var crashHandler = CrashHandler()


    private fun initMLink() {
        val config = MWConfiguration(this)
//        if (BuildConfig.DEBUG) {
//            config.setLogEnable(true)//打开极光魔链Log信息
//        } else {
            config.setLogEnable(false)//关闭极光魔链Log信息
//        }
        MagicWindowSDK.initSDK(config)
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        crashHandler.initCrashHandler(this)
        initMLink()//初始化极光魔链
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {
                Session.onPause(activity);
            }

            override fun onActivityResumed(activity: Activity?) {
                Session.onResume(activity);
            }

            override fun onActivityStarted(activity: Activity?) {

            }

            override fun onActivityDestroyed(activity: Activity?) {
                //删除acitivity
                activity?.let { removeFromList(it) }
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

            }

            override fun onActivityStopped(activity: Activity?) {

            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                // 将当前Activity加入列表
                activity?.let {
                    addToList(it)
//                    crashHandler.initCrashHandler(it)
                }

            }
        })
    }
}