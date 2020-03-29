package com.moving.kotlin.frame.log.handler

abstract class BaseLogHandler {

    // 责任链的下一个节点，即处理者
    private var nextLogHandler: BaseLogHandler? = null

    // 捕获具体请求并进行处理，或是将请求传递到责任链的下一级别
    fun handleObject(obj: Any) {

        if (obj == null) {
            return
        }

        if (!handle(obj)) {
            // 当前处理者不能胜任，则传递至责任链的下一节点
            if (this.nextLogHandler != null) {
                this.nextLogHandler!!.handleObject(obj)
            }
        }
    }

    // 设置责任链中的下一个处理者
    fun setNextHandler(nextHandler: BaseLogHandler) {
        this.nextLogHandler = nextHandler
    }

    // 定义链中每个处理者具体的处理方式
    protected abstract fun handle(obj: Any): Boolean
}