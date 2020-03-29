package com.moving.kotlin.frame.log.handler

import android.net.Uri
import com.moving.kotlin.frame.log.Log
import com.moving.kotlin.frame.log.LogPrinter
import com.moving.kotlin.frame.log.Parser
import org.json.JSONObject

class UriLogHandler : BaseLogHandler(), Parser<Uri> {

    override fun handle(obj: Any): Boolean {

        if (obj is Uri) {

            val s = Log.getMethodNames()
            println(String.format(s, parseString(obj)))
            return true
        }

        return false
    }

    override fun parseString(uri: Uri): String {

        var msg = uri.javaClass.toString() + LogPrinter.BR + "║ "

        val jsonObject = JSONObject()
        jsonObject.put("Scheme", uri.scheme)
        jsonObject.put("Host", uri.host)
        jsonObject.put("Port", uri.port)
        jsonObject.put("Path", uri.path)
        jsonObject.put("Query", uri.query)
        jsonObject.put("Fragment", uri.fragment)

        var message = jsonObject.toString(LogPrinter.JSON_INDENT)
        message = message.replace("\n".toRegex(), "\n║ ")

        return msg + message
    }

}