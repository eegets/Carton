package com.moving.kotlin.frame.log.handler

import com.moving.kotlin.frame.log.Log
import com.moving.kotlin.frame.log.LogPrinter
import com.moving.kotlin.frame.log.Parser
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class StringLogHandler : BaseLogHandler(), Parser<String> {

    override fun handle(obj: Any): Boolean {

        if (obj is String) {

            var json = obj.trim { it <= ' ' }
            val s = Log.getMethodNames()
            println(String.format(s, parseString(json)))
            return true
        }

        return false
    }

    override fun parseString(json: String): String {
        var message: String = ""

        try {
            if (json.startsWith("{")) {
                val jsonObject = JSONObject(json)
                message = jsonObject.toString(LogPrinter.JSON_INDENT)
                message = message.replace("\n".toRegex(), "\n║ ")
            } else if (json.startsWith("[")) {
                val jsonArray = JSONArray(json)
                message = jsonArray.toString(LogPrinter.JSON_INDENT)
                message = message.replace("\n".toRegex(), "\n║ ")
            }
        } catch (e: JSONException) {
            Log.e("Invalid Json: " + json)
            message = ""
        }

        return message
    }

}