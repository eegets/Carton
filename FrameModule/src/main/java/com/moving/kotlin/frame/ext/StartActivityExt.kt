package com.moving.kotlin.frame.ext

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import java.io.Serializable

/**
 * startActivity 无参跳转
 */
fun<T> Activity.startActivityExt(clazz: Class<T>){
    Intent().also {
        this?.startActivity(Intent(this, clazz))
    }
}

/**
 * startActivity String
 */
fun<T> Activity.startActivityExt(clazz: Class<T>, key: String, value: String){
    Intent().apply {
        putExtra(key, value)
    }.let {
        it.setClass(this, clazz)
        startActivity(it)
    }
}

/**
 * startActivity Int
 */
fun<T> Activity.startActivityExt(clazz: Class<T>, key: String, value: Int){
    Intent().apply {
        putExtra(key, value)
    }.let {
        it.setClass(this, clazz)
        startActivity(it)
    }
}

/**
 * startActivity Boolean
 */
fun<T> Activity.startActivityExt(clazz: Class<T>, key: String, value: Boolean){
    Intent().apply {
        putExtra(key, value)
    }.let {
        it.setClass(this, clazz)
        startActivity(it)
    }
}

/**
 * startActivity Long
 */
fun<T> Activity.startActivityExt(clazz: Class<T>, key: String, value: Long){
    Intent().apply {
        putExtra(key, value)
    }.let {
        it.setClass(this, clazz)
        startActivity(it)
    }
}

/**
 * startActivity Serializable
 */
fun<T> Activity.startActivityExt(clazz: Class<T>, key: String, value: Serializable){
    Intent().apply {
        putExtra(key, value)
    }.let {
        it.setClass(this, clazz)
        startActivity(it)
    }
}

/**
 * startActivity Bundle
 */
fun<T> Activity.startActivityExt(clazz: Class<T>, bundle: Bundle){
    Intent().apply {
        putExtras(bundle)
    }.let {
        it.setClass(this, clazz)
        startActivity(it)
    }
}

/**
 * startActivity 无参跳转
 */
fun<T> Activity.startActivityForResultExt(clazz: Class<T>, requestCode: Int){
    Intent().also {
        this?.startActivityForResult(Intent(this, clazz), requestCode)
    }
}

/**
 * startActivity String
 */
fun<T> Activity.startActivityForResultExt(clazz: Class<T>,  key: String, value: String, requestCode: Int){
    Intent().apply {
        putExtra(key, value)
    }.let {
        it.setClass(this, clazz)
        startActivityForResult(it, requestCode)
    }
}

/**
 * startActivity Int
 */
fun<T> Activity.startActivityForResultExt(clazz: Class<T>,  key: String, value: Int, requestCode: Int){
    Intent().apply {
        putExtra(key, value)
    }.let {
        it.setClass(this, clazz)
        startActivityForResult(it, requestCode)
    }
}

/**
 * startActivity Boolean
 */
fun<T> Activity.startActivityForResultExt(clazz: Class<T>,  key: String, value: Boolean, requestCode: Int){
    Intent().apply {
        putExtra(key, value)
    }.let {
        it.setClass(this, clazz)
        startActivityForResult(it, requestCode)
    }
}

/**
 * startActivityForResultExt Long
 */
fun<T> Activity.startActivityForResultExt(clazz: Class<T>,  key: String, value: Long, requestCode: Int){
    Intent().apply {
        putExtra(key, value)
    }.let {
        it.setClass(this, clazz)
        startActivityForResult(it, requestCode)
    }
}

/**
 * startActivityForResultExt Serializable
 */
fun<T> Activity.startActivityForResultExt(clazz: Class<T>,  key: String, value: Serializable, requestCode: Int){
    Intent().apply {
        putExtra(key, value)
    }.let {
        it.setClass(this, clazz)
        startActivityForResult(it, requestCode)
    }
}

/**
 * startActivityForResultExt Bundle
 */
fun<T> Activity.startActivityForResultExt(clazz: Class<T>, bundle: Bundle, requestCode: Int){
    Intent().apply {
        putExtras(bundle)
    }.let {
        it.setClass(this, clazz)
        startActivityForResult(it, requestCode)
    }
}



////////////////////////////////Fragment跳转///////////////////////////////////


/**
 * startActivity 无参跳转
 */
fun<T> Fragment.startActivityExt(clazz: Class<T>){
    Intent().also {
        this?.startActivity(Intent(this.activity, clazz))
    }
}

/**
 * startActivity String
 */
fun<T> Fragment.startActivityExt(clazz: Class<T>, key: String, value: String){
    Intent().apply {
        putExtra(key, value)
    }.let {
        this.activity?.let { it1 -> it.setClass(it1, clazz) }
        startActivity(it)
    }
}

/**
 * startActivity Int
 */
fun<T> Fragment.startActivityExt(clazz: Class<T>, key: String, value: Int){
    Intent().apply {
        putExtra(key, value)
    }.let {
        this.activity?.let { it1 -> it.setClass(it1, clazz) }
        startActivity(it)
    }
}

/**
 * startActivity Boolean
 */
fun<T> Fragment.startActivityExt(clazz: Class<T>, key: String, value: Boolean){
    Intent().apply {
        putExtra(key, value)
    }.let {
        this.activity?.let { it1 -> it.setClass(it1, clazz) }
        startActivity(it)
    }
}

/**
 * startActivity Long
 */
fun<T> Fragment.startActivityExt(clazz: Class<T>, key: String, value: Long){
    Intent().apply {
        putExtra(key, value)
    }.let {
        this.activity?.let { it1 -> it.setClass(it1, clazz) }
        startActivity(it)
    }
}

/**
 * startActivity Serializable
 */
fun<T> Fragment.startActivityExt(clazz: Class<T>, key: String, value: Serializable){
    Intent().apply {
        putExtra(key, value)
    }.let {
        this.activity?.let { it1 -> it.setClass(it1, clazz) }
        startActivity(it)
    }
}

/**
 * startActivity Bundle
 */
fun<T> Fragment.startActivityExt(clazz: Class<T>, bundle: Bundle){
    Intent().apply {
        putExtras(bundle)
    }.let {
        this.activity?.let { it1 -> it.setClass(it1, clazz) }
        startActivity(it)
    }
}

/**
 * startActivity 无参跳转
 */
fun<T> Fragment.startActivityForResultExt(clazz: Class<T>, requestCode: Int){
    Intent().also {
        this?.startActivityForResult(Intent(this.activity, clazz), requestCode)
    }
}

/**
 * startActivity String
 */
fun<T> Fragment.startActivityForResultExt(clazz: Class<T>,  key: String, value: String, requestCode: Int){
    Intent().apply {
        putExtra(key, value)
    }.let {
        this.activity?.let { it1 -> it.setClass(it1, clazz) }
        startActivityForResult(it, requestCode)
    }
}

/**
 * startActivity Int
 */
fun<T> Fragment.startActivityForResultExt(clazz: Class<T>,  key: String, value: Int, requestCode: Int){
    Intent().apply {
        putExtra(key, value)
    }.let {
        this.activity?.let { it1 -> it.setClass(it1, clazz) }
        startActivityForResult(it, requestCode)
    }
}

/**
 * startActivity Boolean
 */
fun<T> Fragment.startActivityForResultExt(clazz: Class<T>,  key: String, value: Boolean, requestCode: Int){
    Intent().apply {
        putExtra(key, value)
    }.let {
        this.activity?.let { it1 -> it.setClass(it1, clazz) }
        startActivityForResult(it, requestCode)
    }
}

/**
 * startActivityForResultExt Long
 */
fun<T> Fragment.startActivityForResultExt(clazz: Class<T>,  key: String, value: Long, requestCode: Int){
    Intent().apply {
        putExtra(key, value)
    }.let {
        this.activity?.let { it1 -> it.setClass(it1, clazz) }
        startActivityForResult(it, requestCode)
    }
}

/**
 * startActivityForResultExt Serializable
 */
fun<T> Fragment.startActivityForResultExt(clazz: Class<T>,  key: String, value: Serializable, requestCode: Int){
    Intent().apply {
        putExtra(key, value)
    }.let {
        this.activity?.let { it1 -> it.setClass(it1, clazz) }
        startActivityForResult(it, requestCode)
    }
}

/**
 * startActivityForResultExt Bundle
 */
fun<T> Fragment.startActivityForResultExt(clazz: Class<T>, bundle: Bundle, requestCode: Int){
    Intent().apply {
        putExtras(bundle)
    }.let {
        this.activity?.let { it1 -> it.setClass(it1, clazz) }
        startActivityForResult(it, requestCode)
    }
}

