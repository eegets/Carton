package com.moving.kotlin.frame.log.handler

import android.os.Bundle
import com.google.gson.GsonBuilder
import com.moving.kotlin.frame.log.Log
import com.moving.kotlin.frame.log.LogPrinter
import com.moving.kotlin.frame.log.Parser
import org.json.JSONException
import org.json.JSONObject

class BundleLogHandler : BaseLogHandler(), Parser<Bundle> {

    override fun handle(obj: Any): Boolean {

        if (obj is Bundle) {

            val s = Log.getMethodNames()
            println(String.format(s, parseString(obj)))
            return true
        }

        return false
    }

    override fun parseString(bundle: Bundle): String {

        var msg = bundle.javaClass.toString() + LogPrinter.BR + "║ "

        val jsonObject = JSONObject()
        for (key in bundle.keySet()) {

            val isPrimitiveType = LogPrinter.isPrimitiveType(bundle.get(key))

            try {

                if (isPrimitiveType) {
                    jsonObject.put(key.toString(), bundle.get(key))
                } else {
                    val gson =GsonBuilder().create()
                    jsonObject.put(key.toString(), JSONObject(gson.toJson(bundle.get(key))))
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