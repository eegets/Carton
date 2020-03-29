package com.moving.kotlin.frame.ext

import com.moving.kotlin.frame.log.Log

fun String?.e() = Log.e(this)

fun String?.w() = Log.w(this)

fun String?.i() = Log.i(this)

fun String?.d() = Log.d(this)

fun Any?.json() = Log.json(this)

