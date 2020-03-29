package com.moving.kotlin.frame.log.handler


import com.google.gson.GsonBuilder
import com.moving.kotlin.frame.log.Log
import com.moving.kotlin.frame.log.LogPrinter
import com.moving.kotlin.frame.log.Parser
import org.json.JSONObject
import java.lang.ref.Reference

class ReferenceLogHandler : BaseLogHandler(), Parser<Reference<*>> {

    override fun handle(obj: Any): Boolean {

        if (obj is Reference<*>) {
            val s = Log.getMethodNames()
            println(String.format(s, parseString(obj)))
            return true
        }
        return false
    }

    override fun parseString(reference: Reference<*>): String {
        val actual = reference.get()
        var msg = reference.javaClass.canonicalName + "<" + actual?.javaClass?.simpleName + ">" + LogPrinter.BR + "║ "
        val isPrimitiveType = LogPrinter.isPrimitiveType(actual)
        if (isPrimitiveType) {
            msg += "{" + actual + "}"
        } else {
            val gson = GsonBuilder().create()
            val jsonObject = JSONObject(gson.toJson(actual))
            var message = jsonObject.toString(LogPrinter.JSON_INDENT)
            message = message.replace("\n".toRegex(), "\n║ ")
            msg += message
        }
        return msg
    }
}