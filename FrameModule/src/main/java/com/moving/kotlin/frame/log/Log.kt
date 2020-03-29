package com.moving.kotlin.frame.log

import com.moving.kotlin.frame.log.handler.*
import java.util.*

/**
 * 日志使用责任链方式来处理各种类型log的格式化，如果有特殊类型只需要
 * 自己动手添加一个对应类型的责任链就可以做到解析特殊类型
 */
object Log {

    enum class LogLevel {
        ERROR {
            override val value: Int
                get() = 0
        },
        WARN {
            override val value: Int
                get() = 1
        },
        INFO {
            override val value: Int
                get() = 2
        },
        DEBUG {
            override val value: Int
                get() = 3
        };

        abstract val value: Int
    }

    private var TAG = "KotlinLog"
    private var header: String? = ""
    private val handlers = ArrayList<BaseLogHandler>()
    private var firstHandler: BaseLogHandler

    init {
        handlers.add(StringLogHandler())
        handlers.add(CollectionLogHandler())
        handlers.add(MapLogHandler())
        handlers.add(BundleLogHandler())
        handlers.add(IntentLogHandler())
        handlers.add(UriLogHandler())
        handlers.add(ThrowableLogHandler())
        handlers.add(ReferenceLogHandler())
        handlers.add(ObjectLogHandler())

        val len = handlers.size
        for (i in 0 until len) {
            if (i > 0) {
                handlers[i - 1].setNextHandler(handlers[i])
            }
        }
        firstHandler = handlers[0]
    }

    @JvmStatic
    var logLevel = LogLevel.DEBUG // 日志的等级，可以进行配置，最好在Application中进行全局的配置

    /******************* 配置方法 Start *******************/

    @JvmStatic
    fun init(clazz: Class<*>): Log {
        TAG = clazz.simpleName
        return this
    }

    /**
     * 支持用户自己传tag
     * @param tag
     */
    @JvmStatic
    fun init(tag: String): Log {
        TAG = tag
        return this
    }

    /**
     * header是自定义的内容，可以放App的信息版本号等，方便查找和调试
     * @param tag
     */
    @JvmStatic
    fun header(header: String?): Log {
        Log.header = header
        return this
    }

    /**
     * 自定义Handler来解析Object
     */
    @JvmStatic
    fun addCustomerHandler(handler: BaseLogHandler): Log {
        val size = handlers.size
        return addCustomerHandler(handler, size - 1) // 插入在ObjectHandler之前
    }

    /**
     * 自定义Handler来解析Object，并指定Handler的位置
     */
    @JvmStatic
    fun addCustomerHandler(handler: BaseLogHandler, index: Int): Log {

        handlers.add(index, handler)

        val len = handlers.size

        for (i in 0 until len) if (i > 0) {
            handlers[i - 1].setNextHandler(handlers[i])
        }

        return this
    }

    /******************* 配置方法 End *******************/

    @JvmStatic
    fun e(msg: String?) {
        if (LogLevel.ERROR.value <= logLevel.value) {

            if (msg != null && msg.isNotEmpty()) {

                val s = getMethodNames()

                if (msg.contains("\n")) {
                    android.util.Log.e(TAG, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
                } else {
                    android.util.Log.e(TAG, String.format(s, msg))
                }
            }
        }
    }

    /**
     * @param tag 使用自定义tag
     *
     * @param msg
     */
    @JvmStatic
    fun e(tag: String?, msg: String?) {
        if (LogLevel.ERROR.value <= logLevel.value) {

            if (tag != null && tag.isNotEmpty() && msg != null && msg.isNotEmpty()) {

                val s = getMethodNames()

                if (msg.contains("\n")) {
                    android.util.Log.e(tag, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
                } else {
                    android.util.Log.e(tag, String.format(s, msg))
                }
            }
        }
    }

    @JvmStatic
    fun e(msg: String?, tr: Throwable) {
        if (LogLevel.ERROR.value <= logLevel.value) {

            if (msg != null && msg.isNotEmpty()) {
                android.util.Log.e(TAG, msg, tr)
            }
        }
    }

    @JvmStatic
    fun w(msg: String?) {
        if (LogLevel.WARN.value <= logLevel.value) {

            if (msg != null && msg.isNotEmpty()) {

                val s = getMethodNames()

                if (msg.contains("\n")) {
                    android.util.Log.w(TAG, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
                } else {
                    android.util.Log.w(TAG, String.format(s, msg))
                }
            }
        }
    }

    /**
     * @param tag 使用自定义tag
     *
     * @param msg
     */
    @JvmStatic
    fun w(tag: String?, msg: String?) {
        if (LogLevel.WARN.value <= logLevel.value) {

            if (tag != null && tag.isNotEmpty() && msg != null && msg.isNotEmpty()) {

                val s = getMethodNames()

                if (msg.contains("\n")) {
                    android.util.Log.w(tag, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
                } else {
                    android.util.Log.w(tag, String.format(s, msg))
                }
            }
        }
    }

    @JvmStatic
    fun w(msg: String?, tr: Throwable) {
        if (LogLevel.WARN.value <= logLevel.value) {

            if (msg != null && msg.isNotEmpty()) {
                android.util.Log.w(TAG, msg, tr)
            }
        }
    }

    @JvmStatic
    fun i(msg: String?) {
        if (LogLevel.INFO.value <= logLevel.value) {

            if (msg != null && msg.isNotEmpty()) {

                val s = getMethodNames()

                if (msg.contains("\n")) {
                    android.util.Log.i(TAG, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
                } else {
                    android.util.Log.i(TAG, String.format(s, msg))
                }
            }

        }
    }

    /**
     * @param tag 使用自定义tag
     *
     * @param msg
     */
    @JvmStatic
    fun i(tag: String?, msg: String?) {
        if (LogLevel.INFO.value <= logLevel.value) {

            if (tag != null && tag.isNotEmpty() && msg != null && msg.isNotEmpty()) {

                val s = getMethodNames()

                if (msg.contains("\n")) {
                    android.util.Log.i(tag, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
                } else {
                    android.util.Log.i(tag, String.format(s, msg))
                }
            }
        }
    }

    @JvmStatic
    fun i(msg: String?, tr: Throwable) {
        if (LogLevel.INFO.value <= logLevel.value) {

            if (msg != null && msg.isNotEmpty()) {
                android.util.Log.i(TAG, msg, tr)
            }
        }
    }

    @JvmStatic
    fun d(msg: String?) {
        if (LogLevel.DEBUG.value <= logLevel.value) {

            if (msg != null && msg.isNotEmpty()) {

                val s = getMethodNames()

                if (msg.contains("\n")) {
                    android.util.Log.d(TAG, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
                } else {
                    android.util.Log.d(TAG, String.format(s, msg))
                }
            }
        }
    }

    /**
     * @param tag 使用自定义tag
     *
     * @param msg
     */
    @JvmStatic
    fun d(tag: String?, msg: String?) {
        if (LogLevel.DEBUG.value <= logLevel.value) {

            if (tag != null && tag.isNotEmpty() && msg != null && msg.isNotEmpty()) {

                val s = getMethodNames()

                if (msg.contains("\n")) {
                    android.util.Log.d(tag, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
                } else {
                    android.util.Log.d(tag, String.format(s, msg))
                }
            }
        }
    }

    @JvmStatic
    fun d(msg: String?, tr: Throwable) {
        if (LogLevel.DEBUG.value <= logLevel.value) {

            if (msg != null && msg.isNotEmpty()) {
                android.util.Log.d(TAG, msg, tr)
            }
        }
    }

    /**
     * 将任何对象转换成json字符串进行打印
     */
    @JvmStatic
    fun json(obj: Any?) {

        if (obj == null) {
            d("object is null")
            return
        }

        firstHandler.handleObject(obj)
    }
    @JvmStatic
    fun map(obj: Any?) {

        if (obj == null) {
            d("object is null")
            return
        }

        firstHandler.handleObject(obj)
    }

    @JvmStatic
    fun getMethodNames(): String {
        val sElements = Thread.currentThread().stackTrace

        var stackOffset = LogPrinter.getStackOffset(sElements)

        stackOffset++
        val builder = StringBuilder()

        builder.append("  ").append(LogPrinter.BR).append(LogPrinter.TOP_BORDER).append(LogPrinter.BR)
        if (header != null && header!!.isNotEmpty()) {
            // 添加Header
            builder.append("║ " + "Header: " + header).append(LogPrinter.BR)
                    .append(LogPrinter.MIDDLE_BORDER).append(LogPrinter.BR)
        }
        // 添加当前线程名
        builder.append("║ " + "Thread: " + Thread.currentThread().name).append(LogPrinter.BR)
                .append(LogPrinter.MIDDLE_BORDER).append(LogPrinter.BR)
                // 添加类名、方法名、行数
                .append("║ ")
                .append(sElements[stackOffset].className)
                .append(".")
                .append(sElements[stackOffset].methodName)
                .append(" ")
                .append(" (")
                .append(sElements[stackOffset].fileName)
                .append(":")
                .append(sElements[stackOffset].lineNumber)
                .append(")")
                .append(LogPrinter.BR)
                .append(LogPrinter.MIDDLE_BORDER).append(LogPrinter.BR)
                // 添加打印的日志信息
                .append("║ ").append("%s").append(LogPrinter.BR)
                .append(LogPrinter.BOTTOM_BORDER).append(LogPrinter.BR)

        return builder.toString()
    }
}