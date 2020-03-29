package com.moving.kotlin.frame.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager
import androidx.annotation.NonNull


object NetWorkUtil {


    //networkType 0 无网，1 WiFi，2 其他，3 2G，4 3G，5 4G
    val NETWORK_TYPE_NOT_AVAILABLE = 0
    val NETWORK_TYPE_WIFI = 1
    val NETWORK_TYPE_OTHERS = 2
    val NETWORK_TYPE_2G = 3
    val NETWORK_TYPE_3G = 4
    val NETWORK_TYPE_4G = 5

    @JvmStatic
    fun getNetworkType(context: Context): Int {
        val isNetworkOK = isAvailable(context)
        if (isNetworkOK) {
            val networkInfo = getActiveNetworkInfo(context)
            if (networkInfo == null) {
                return NETWORK_TYPE_OTHERS
            } else {
                if (networkInfo!!.getType() == ConnectivityManager.TYPE_WIFI) {
                    return NETWORK_TYPE_WIFI
                } else {
                    val mobileNetworkType = getMobileNetworkType(context)
                    return if ("4G" == mobileNetworkType) {
                        NETWORK_TYPE_4G
                    } else if ("3G" == mobileNetworkType) {
                        NETWORK_TYPE_3G
                    } else if ("2G" == mobileNetworkType) {
                        NETWORK_TYPE_2G
                    } else {
                        NETWORK_TYPE_OTHERS
                    }
                }

            }
        } else {
            return NETWORK_TYPE_NOT_AVAILABLE
        }
    }


    /**
     * 判断网络是否可用
     *
     * 需添加权限 android.permission.ACCESS_NETWORK_STATE
     */

    fun isAvailable(context: Context): Boolean {
        val info = getActiveNetworkInfo(context)
        return info != null && info!!.isAvailable()
    }

    /**
     * 获取活动网路信息
     *
     * @param context 上下文
     * @return NetworkInfo
     */
    fun getActiveNetworkInfo(context: Context): NetworkInfo? {
        val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }


    @NonNull
    fun getMobileNetworkType(context: Context): String {
        val mTelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val networkType = mTelephonyManager.networkType
        when (networkType) {
            TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> return "2G"
            TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP -> return "3G"
            TelephonyManager.NETWORK_TYPE_LTE -> return "4G"
            20 -> return "5G"
            else -> return "unknown"
        }
    }

}