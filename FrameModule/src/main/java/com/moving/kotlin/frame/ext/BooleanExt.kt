package com.moving.kotlin.frame.ext

/**
 * 对系统boolean进行扩展
 */
infix fun <T> Boolean.then(value: T?) = if (this) value else null

fun <T> Boolean.then(value: T?, default: T) = if (this) value else default

inline fun <T> Boolean.then(function: () -> T, default: T) = if (this) function() else default

inline fun <T> Boolean.then(function: () -> T, default: () -> T) = if (this) function() else default()

inline infix fun <reified T> Boolean.then(function: () -> T) = if (this) function() else null

inline infix fun <reified T, reified Type> Type?.then(block: (Type) -> T) = if (this != null) block(this) else null