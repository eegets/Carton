package com.moving.kotlin.frame.log.handler

import com.moving.kotlin.frame.log.Log
import com.moving.kotlin.frame.log.Parser
import java.io.PrintWriter
import java.io.StringWriter

class ThrowableLogHandler : BaseLogHandler(), Parser<Throwable> {

    override fun handle(obj: Any): Boolean {

        if (obj is Throwable) {

            val s = Log.getMethodNames()
            System.err.println(String.format(s, parseString(obj)))
            return true
        }

        return false
    }

    override fun parseString(throwable: Throwable): String {
        val sw = StringWriter(256)
        val pw = PrintWriter(sw, false)
        throwable.printStackTrace(pw)
        pw.flush()
        var message = sw.toString()
        message = message.replace("\n".toRegex(), "\n║ ")

        return message
    }

}