package com.moving.kotlin.frame.ext

import android.graphics.Color
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.moving.kotlin.frame.R
import com.moving.kotlin.frame.base.FrameApplication
import com.moving.kotlin.frame.utils.ThreadUtils


fun Toast.setGravityCenter(): Toast {
    setGravity(Gravity.CENTER, 0, 0)
    return this
}

/**
 * 设置Toast字体及背景颜色
 * @param messageColor
 * @param backgroundColor
 * @return
 */
fun Toast.setToastColor(messageColor: Int, backgroundColor: Int) {
    val view = view
    if (view != null) {
        val message = view.findViewById(android.R.id.message) as TextView
        message.setBackgroundColor(backgroundColor)
        message.setTextColor(messageColor)
    }
}

/**
 * 设置Toast字体及背景
 * @param messageColor
 * @param background
 * @return
 */
fun Toast.setBackground(messageColor: Int = Color.WHITE, background: Int = R.drawable.toast_background): Toast {
    val view = view
    if (view != null) {
        val message = view.findViewById(android.R.id.message) as TextView
        view.setBackgroundResource(background)
        message.setTextColor(messageColor)
    }
    return this
}
fun toastShow(message: String){
    ThreadUtils.runMainUIThread(Runnable {
        show(message,true)
    })
}
private fun show(message: String,isCenter:Boolean) {
    var toast = Toast.makeText(FrameApplication.instance, message, Toast.LENGTH_SHORT)
    if (isCenter) {
        toast.setGravity(Gravity.CENTER, 0, 0)
    }
    toast.show();
}
