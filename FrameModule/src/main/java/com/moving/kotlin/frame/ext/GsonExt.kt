package com.moving.kotlin.frame.ext

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception

val gson by lazy {
    Gson()
}

/**
 * json转换为javaBean
 */
fun<T> jsonFromJavaBean(jsonStr: String, clazz: Class<T>): T? {

    try {
        return gson.fromJson(jsonStr, clazz)
    }catch (e: Exception){
        e.printStackTrace()
    }
    return null
}

/**
 * javaBean转换为json
 */
fun<T> javaBeanFromJson(javabean: T): String? {
    try {
        return gson.toJson(javabean)
    }catch (e: Exception){
        e.printStackTrace()
    }
    return null
}

/**
 * json转换为List<T>
 */
fun<T> jsonFromList(jsonStr: String): List<T>? {
    try {
        return gson.fromJson(jsonStr, object : TypeToken<List<T>>(){

        }.type)
    }catch (e: Exception){

    }
    return null
}

/**
 * json转换Map<String, String>
 */
fun jsonFromMap(jsonStr: String): Map<String, String>? {
    try {
        return gson.fromJson(jsonStr, object : TypeToken<Map<String, String>>(){

        }.type)
    }catch (e: Exception){

    }
    return null
}

/**
 * json转换Map<String, String>
 */
fun mapFromJson(jsonStr: Map<String, Any>): String? {
    try {
        return gson.toJson(jsonStr)
    }catch (e: Exception){

    }
    return null
}

