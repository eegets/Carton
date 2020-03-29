package com.moving.kotlin.frame.ext

import android.text.Html
import android.widget.TextView

fun fromHtmlExt(textView: TextView, defaultStr: String, defaultColor: String, changeStr: String, changeColor: String){
    val str = "<font color='$defaultColor'>$defaultStr</font><font color='$changeColor'>$changeStr</font>" as String?
    textView.text = Html.fromHtml(str)
}