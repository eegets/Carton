package com.moving.kotlin.frame.log.handler

import com.google.gson.GsonBuilder
import com.moving.kotlin.frame.log.Log
import com.moving.kotlin.frame.log.LogPrinter
import org.json.JSONObject

class ObjectLogHandler : BaseLogHandler() {

    override fun handle(obj: Any): Boolean {
        val s = Log.getMethodNames()
        val msg = obj.javaClass.toString() + LogPrinter.BR + "║ "
        val gson = GsonBuilder().create()
        val objStr = gson.toJson(obj)
        if(objStr.isEmpty() || "null" == objStr){
            return true
        }
        val jsonObject = JSONObject(objStr)
        var message = jsonObject.toString(LogPrinter.JSON_INDENT)
        message = message.replace("\n".toRegex(), "\n║ ")
        println(String.format(s, msg + message))
        return true
    }
}