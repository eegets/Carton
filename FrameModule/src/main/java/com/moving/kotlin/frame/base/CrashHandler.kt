package com.moving.kotlin.frame.base

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.os.Looper
import com.moving.kotlin.frame.ext.closeQuietly
import com.moving.kotlin.frame.log.Log
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


class CrashHandler : Thread.UncaughtExceptionHandler {

    private var defaultHandler: Thread.UncaughtExceptionHandler? = null
    private var context: Context? = null
    private val info = HashMap<String, String>()// 用来存储设备信息和异常信息
    private val format = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss",Locale.CHINA)// 用于格式化日期,作为日志文件名的一部分
    private var time: String? = null//发生crash的具体时间


    fun initCrashHandler(context: Context?) {
        this.context = context
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * 异常信息
     * @return true 如果处理了该异常信息;否则返回false.
     */
    private fun handleException(ex: Throwable?): Boolean {
        if (ex == null) {
            return false
        }
        object : Thread() {
            override fun run() {
                Looper.prepare()
                //Toast.makeText(mContext, "出现闪退了正在把日志保存到sdcard log目录下", Toast.LENGTH_SHORT).show();
                Looper.loop()
            }
        }.start()
        // 收集设备参数信息
        collectDeviceInfo(context)
        // 保存日志文件
        saveCrashInfo2File(ex)
        return true
    }

    /**
     * 收集设备参数信息
     *
     * @param context
     */
    private fun collectDeviceInfo(context: Context?) {
        try {
            val pm = context?.packageManager// 获得包管理器
            val pi = pm?.getPackageInfo(context.packageName, PackageManager.GET_ACTIVITIES)// 得到该应用的信息，即主Activity
            if (pi != null) {
                val versionName = if (pi.versionName == null)
                    "null"
                else
                    pi.versionName
                val versionCode = pi.versionCode.toString() + ""
                info["versionName"] = versionName
                info["versionCode"] = versionCode
                val time = format.format(Date())
                info["logTime"] = time
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        val fields = Build::class.java.declaredFields// 反射机制
        for (field in fields) {
            try {
                field.isAccessible = true
                info.put(field.name, field.get("").toString())
                //LogManager.d(field.getName() + ":" + field.get(""));
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        }
    }

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        if (handleException(e)) {
            // 如果自定义的没有处理则让系统默认的异常处理器来处理
            defaultHandler?.uncaughtException(t, e)
        } else {
            try {
                Thread.sleep(1000)// 如果处理了，让程序继续运行1秒再退出，保证文件保存并上传到服务器
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                // 退出程序
                FrameApplication.exitApp()
                android.os.Process.killProcess(android.os.Process.myPid())
                System.exit(1)
            }
        }
    }


    private fun saveCrashInfo2File(ex: Throwable): String {
        val sb = StringBuffer()
        for (entry in info.entries) {
            val key = entry.key
            val value = entry.value
            sb.append("$key=$value\r\n")
        }
        val writer = StringWriter()
        val pw = PrintWriter(writer)
        ex.printStackTrace(pw)
        var cause: Throwable? = ex.cause
        // 循环着把所有的异常信息写入writer中
        while (cause != null) {
            cause.printStackTrace(pw)
            cause = cause.cause
        }
        pw.close()// 记得关闭
        val result = writer.toString()
        sb.append(result)
        Log.e("result : $result")
        // 保存文件
        val timetamp = System.currentTimeMillis()
        time = format.format(Date())
        val fileName = "$time-$timetamp.log"
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            var fos: FileOutputStream? = null
            try {
                val dir = File(Environment.getExternalStorageDirectory().path + "/v210/log")
                Log.i(dir.toString())
                if (!dir.exists()) {
                    dir.mkdir()
                }
                fos = FileOutputStream(File(dir, fileName))
                fos.write(sb.toString().toByteArray())
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                fos?.closeQuietly()
            }
        }
        return fileName
    }
}