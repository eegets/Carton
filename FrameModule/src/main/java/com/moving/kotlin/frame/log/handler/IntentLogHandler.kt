package com.moving.kotlin.frame.log.handler

import android.content.Intent
import android.os.Bundle
import com.google.gson.GsonBuilder
import com.moving.kotlin.frame.log.Log
import com.moving.kotlin.frame.log.LogPrinter
import com.moving.kotlin.frame.log.Parser
import org.json.JSONException
import org.json.JSONObject


class IntentLogHandler: BaseLogHandler(), Parser<Intent> {

    override fun handle(obj: Any): Boolean {

        if (obj is Intent) {

            val s = Log.getMethodNames()
            println(String.format(s, parseString(obj)))
            return true
        }

        return false
    }

    override fun parseString(intent: Intent): String {

        var msg = intent.javaClass.toString() + LogPrinter.BR + "║ "

        val jsonObject = JSONObject()
        jsonObject.put("Scheme", intent.scheme)
        jsonObject.put("Action", intent.action)
        jsonObject.put("DataString", intent.dataString)
        jsonObject.put("Type", intent.type)
        jsonObject.put("Package", intent.`package`)
        jsonObject.put("ComponentInfo", intent.component)
//        jsonObject.put("Flags", getFlags(intent.flags))
        jsonObject.put("Categories", intent.categories)
        if (intent.extras!=null) {
            jsonObject.put("Extras", JSONObject(parseBundleString(intent.extras!!)))
        }

        var message = jsonObject.toString(LogPrinter.JSON_INDENT)
        message = message.replace("\n".toRegex(), "\n║ ")

        return msg + message
    }

    private fun parseBundleString(extras: Bundle): String {

        val jsonObject = JSONObject()
        for (key in extras.keySet()) {

            val isPrimitiveType = LogPrinter.isPrimitiveType(extras.get(key))

            try {

                if (isPrimitiveType) {
                    jsonObject.put(key.toString(), extras.get(key))
                } else {
                    val gson=GsonBuilder().create()
                    jsonObject.put(key.toString(), JSONObject(gson.toJson(extras.get(key))))
                }
            } catch (e: JSONException) {
                Log.e("Invalid Json")
            }

        }

        return jsonObject.toString(LogPrinter.JSON_INDENT)
    }
}