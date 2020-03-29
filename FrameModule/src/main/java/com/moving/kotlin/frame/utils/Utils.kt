package com.moving.kotlin.frame.utils

object Utils {
    @JvmStatic
    fun secondToTime(second: Long): String {
        var secondVal = second
        val days = secondVal / 86400
        secondVal %= 86400
        val hours = secondVal / 3600
        secondVal %= 3600
        val minutes = secondVal / 60
        secondVal %= 60
        if (0 < days) {
            return days.toString() + "天" + hours.toString() + "小时" + minutes + "分钟" //+ secondVal.toString() + "秒"
        } else {
            return hours.toString() + "小时" + minutes.toString() + "分钟" //+ secondVal.toString() + "秒"
        }
    }
}