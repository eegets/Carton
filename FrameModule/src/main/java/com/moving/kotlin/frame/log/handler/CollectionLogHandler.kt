package com.moving.kotlin.frame.log.handler

import com.google.gson.GsonBuilder
import com.moving.kotlin.frame.log.Log
import com.moving.kotlin.frame.log.LogPrinter
import com.moving.kotlin.frame.log.Parser
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class CollectionLogHandler : BaseLogHandler(), Parser<Collection<*>> {

    override fun handle(obj: Any): Boolean {

        if (obj is Collection<*>) {
            val value = obj.firstOrNull()
            val isPrimitiveType = LogPrinter.isPrimitiveType(value)

            if (isPrimitiveType) {
                val simpleName = obj.javaClass
                var msg = "%s size = %d" + LogPrinter.BR
                msg = String.format(msg, simpleName, obj.size) + "║ "
                val s = Log.getMethodNames()
                println(String.format(s, msg + obj.toString()))
                return true
            }

            val s = Log.getMethodNames()
            println(String.format(s, parseString(obj)))

            return true
        }

        return false
    }

    override fun parseString(collection: Collection<*>): String {

        val jsonArray = JSONArray()

        val simpleName = collection.javaClass

        var msg = "%s size = %d" + LogPrinter.BR
        msg = String.format(msg, simpleName, collection.size) + "║ "

        collection.map {

            it ->

            try {
                val gson=GsonBuilder().create()
                val objStr = gson.toJson(it)
                val jsonObject = JSONObject(objStr)
                jsonArray.put(jsonObject)
            } catch (e: JSONException) {
                Log.e("Invalid Json")
            }
        }

        var message = jsonArray.toString(LogPrinter.JSON_INDENT)
        message = message.replace("\n".toRegex(), "\n║ ")

        return msg + message
    }

}