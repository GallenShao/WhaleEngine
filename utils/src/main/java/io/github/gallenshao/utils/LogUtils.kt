package io.github.gallenshao.utils

import android.util.Log

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

object LogUtils {

    val VERBOSE = 0
    val DEBUG = -1
    val INFO = -2
    val WARNING = -3
    val ERROR = -4

    var minLogLevel = INFO

    fun e(tag: String, msg: String) {
        if (minLogLevel >= ERROR) {
            Log.e(tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (minLogLevel >= WARNING) {
            Log.w(tag, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (minLogLevel >= INFO) {
            Log.i(tag, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (minLogLevel >= DEBUG) {
            Log.d(tag, msg)
        }
    }

    fun v(tag: String, msg: String) {
        if (minLogLevel >= VERBOSE) {
            Log.v(tag, msg)
        }
    }

}