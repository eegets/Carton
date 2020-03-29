package com.moving.kotlin.frame.log.handler

import com.google.gson.GsonBuilder
import com.moving.kotlin.frame.log.Log
import com.moving.kotlin.frame.log.LogPrinter
import com.moving.kotlin.frame.log.Parser
import org.json.JSONException
import org.json.JSONObject

class MapLogHandler : BaseLogHandler(), Parser<Map<*, *>> {

    override fun handle(obj: Any): Boolean {

        if (obj is Map<*, *>) {
            val s = Log.getMethodNames()
            println(String.format(s, parseString(obj)))
            return true
        }

        return false
    }

    override fun parseString(map: Map<*, *>): String {

        val keys = map.keys
        val values = map.values
        val value = values.firstOrNull()
        val isPrimitiveType = LogPrinter.isPrimitiveType(value)

        var msg = map.javaClass.toString() + LogPrinter.BR + "║ "

        val jsonObject = JSONObject()
        keys.map { it ->
            try {

                if (isPrimitiveType) {
                    jsonObject.put(it.toString(), map.get(it))
                } else {
                    val gson= GsonBuilder().create()
                    jsonObject.put(it.toString(), JSONObject(gson.toJson(map.get(it))))
                }
            } catch (e: JSONException) {
                Log.e("Invalid Json")
            }
        }
        var message = jsonObject.toString(LogPrinter.JSON_INDENT)
        message = message.replace("\n".toRegex(), "\n║ ")
        return msg + message
    }

}